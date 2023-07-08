package com.job.job;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.job.constant.JobConstant;
import com.job.constant.JobEnums;
import com.job.dao.JobLogMapper;
import com.job.dao.JobLogReportMapper;
import com.job.model.entity.Header;
import com.job.model.po.JobInfo;
import com.job.model.po.JobLog;
import com.job.model.po.JobLogReport;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * htt任务处理类
 *
 * @author
 * @date 2020/3/23 15:42
 * @DisallowConcurrentExecution 禁止并发执行多个相同定义的JobDetail
 **/
@Slf4j
@DisallowConcurrentExecution
@Component
public class HttpJob extends QuartzJobBean {

    /**
     * 任务日志Mapper
     */
    @Resource
    private JobLogMapper jobLogMapper;

    /**
     * 任务日志报表Mapper
     */
    @Resource
    private JobLogReportMapper jobLogReportMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        // 开启计时器
        TimeInterval timer = DateUtil.timer();

        // 创建任务执行记录
        JobLog jobLog = new JobLog();
        jobLog.setCreateTime(DateUtil.date());

        // 更新当天的任务日志报表
        int reportId = this.getJobLogReportByDay(DateUtil.beginOfDay(DateUtil.date()));
        jobLogReportMapper.increaseRunningCount(reportId);

        // 获取任务执行的数据
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String data = jobDataMap.getString(JobConstant.JOB_INFO_IN_JOB_DATA_MAP_KEY);
        log.info("jobInfo from jobDataMap = {}", data);
        JobInfo jobInfo = JSONUtil.toBean(data, JobInfo.class);

        // 执行http请求
        try {
            String executeResult = sendHttpRequest(jobInfo);
            jobLog.setExecuteStatus(JobEnums.JobLogStatus.SUCCESS.status());
            jobLogReportMapper.increaseSuccessCount(reportId);
            log.info("HttpJob title = {} execute success, HttpResponse : {}", jobInfo.getTitle(), executeResult);
        } catch (Exception e) {
            // http请求失败
            jobLog.setExecuteStatus(JobEnums.JobLogStatus.FAILURE.status());
            jobLog.setExecuteFailMsg(e.getMessage());
            jobLogReportMapper.increaseFailCount(reportId);
            log.error("HttpJob title = {} execute fail, HttpResponse : {}", jobInfo.getTitle(), e);
        }

        //计算任务执行花费时间(毫秒)
        long consumeTime = timer.interval();
        jobLog.setJobInfoId(jobInfo.getId());
        jobLog.setExecuteParams(jobInfo.getParams());
        jobLog.setConsumeTime(consumeTime);
        jobLogMapper.insert(jobLog);
    }

    /**
     * 获取当天的任务日志报表id
     *
     * @param day
     * @return
     */
    private int getJobLogReportByDay(Date day) {
        JobLogReport search = new JobLogReport();
        search.setDay(day);
        JobLogReport jobLogReport = jobLogReportMapper.selectOne(new QueryWrapper<>(search));
        if (jobLogReport == null) {
            jobLogReportMapper.insert(search);
            return search.getId();
        }
        return jobLogReport.getId();
    }

    /**
     * 根据jobInfo发送http请求
     *
     * @param jobInfo
     * @return
     */
    private String sendHttpRequest(JobInfo jobInfo) {
        String response = null;
        String url = jobInfo.getUrl().trim();
        String method = jobInfo.getMethod().trim();
        String params = jobInfo.getParams();
        Map<String, String> headers = jobInfo.getHeaders() != null ?
                jobInfo.getHeaders().stream()
                        .collect(Collectors.toMap(Header::getFieldName, Header::getValue))
                : new HashMap<>();
        log.info("url = {}, method = {}, params = {}", url, method, params);
        if (method.toUpperCase().trim().equals(HttpMethod.GET.name())) {
            // get 请求
            if (StrUtil.isNotBlank(params)) {
                response = HttpRequest.get(url).form(params).addHeaders(headers).execute().body();
            } else {
                response = HttpRequest.get(url).addHeaders(headers).execute().body();
            }
        } else if (method.toUpperCase().trim().equals(HttpMethod.POST.name())) {
            // post 请求
            if (StrUtil.isNotBlank(params)) {
                response = HttpRequest.post(url).form(params).addHeaders(headers).execute().body();
            } else {
                response = HttpRequest.post(url).addHeaders(headers).execute().body();
            }
        }

        return response;
    }

}
