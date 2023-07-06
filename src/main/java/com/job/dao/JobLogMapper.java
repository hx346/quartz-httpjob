package com.job.dao;

import com.job.model.bo.JobLogBO;
import com.job.model.po.JobLog;
import com.job.model.query.JobLogQuery;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author  
 */
public interface JobLogMapper extends Mapper<JobLog> {

    /**
     * 查找任务运行日志
     * @param jobLogQuery
     * @return
     */
    List<JobLogBO> selectJobLog(@Param(value = "query") JobLogQuery jobLogQuery);

}