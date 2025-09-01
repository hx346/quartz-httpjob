package com.job.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.job.constant.JobConstant;
import com.job.constant.JobEnums;
import com.job.model.po.JobInfo;
import org.quartz.CronExpression;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import java.text.ParseException;
import java.util.Date;

/**
 * 定时任务相关的工具类，封装了 Quartz 调度中常用的操作。
 * 主要提供获取任务和触发器键、判断任务状态以及计算
 * 下一次执行时间等功能，方便业务代码直接调用。
 */
public class JobUtil {

    /**
     * 根据任务信息生成唯一的 JobKey。
     *
     * @param jobInfo 任务实体
     * @return Quartz 框架使用的 JobKey
     */
    public static JobKey getJobKey(JobInfo jobInfo) {
        String jobKey = JobConstant.JOB_KEY_PREFIX + jobInfo.getId();
        return new JobKey(jobKey);
    }

    /**
     * 根据任务信息生成唯一的 TriggerKey。
     *
     * @param jobInfo 任务实体
     * @return Quartz 框架使用的 TriggerKey
     */
    public static TriggerKey getTriggerKey(JobInfo jobInfo) {
        String triggerKey = JobConstant.TRIGGER_KEY_PREFIX + jobInfo.getId();
        return new TriggerKey(triggerKey);
    }

    /**
     * 判断任务当前是否处于正常运行状态。
     *
     * @param scheduler 任务调度器
     * @param triggerKey 触发器键
     * @return true 表示任务运行中，false 表示非运行状态
     */
    public static boolean isNormal(Scheduler scheduler, TriggerKey triggerKey) {
        try {
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            if (Trigger.TriggerState.NORMAL.equals(triggerState)) {
                return true;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断任务是否已经被暂停。
     *
     * @param scheduler 任务调度器
     * @param triggerKey 触发器键
     * @return true 表示任务被暂停，false 表示任务未暂停
     */
    public static boolean isPaused(Scheduler scheduler, TriggerKey triggerKey) {
        try {
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            if (Trigger.TriggerState.PAUSED.equals(triggerState)) {
                return true;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 计算任务的下一次运行时间。
     *
     * @param jobInfo 任务实体，需包含 cron 表达式
     * @return 下一次执行的时间，若 cron 不合法返回 null
     */
    public static Date getNextExecuteTime(JobInfo jobInfo) {
        if (jobInfo != null && StrUtil.isNotBlank(jobInfo.getCron())) {
            try {
                CronExpression cronExpression = new CronExpression(jobInfo.getCron());
                return cronExpression.getNextValidTimeAfter(DateUtil.date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断该任务是否处于已删除状态。
     *
     * @param jobInfo 任务实体
     * @return true 表示任务已删除，false 表示任务正常
     */
    public static boolean isDeletedJob(JobInfo jobInfo) {
        if (jobInfo != null && !JobEnums.JobStatus.DELETED.status().equals(jobInfo.getStatus())) {
            return false;
        }
        return true;
    }

}
