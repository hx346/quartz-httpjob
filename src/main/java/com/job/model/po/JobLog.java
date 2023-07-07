package com.job.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author
 */
@TableName("schedule_job_log")
@Data
public class JobLog {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 任务id，关联schedule_job_detail
     */
    @TableField("job_info_id")
    private Integer jobInfoId;

    /**
     * 执行状态:0-执行失败 1-执行成功
     */
    @TableField("execute_status")
    private Integer executeStatus;

    /**
     * 执行参数
     */
    @TableField("execute_params")
    private String executeParams;

    /**
     * 执行失败原因
     */
    @TableField("execute_fail_msg")
    private String executeFailMsg;

    /**
     * 执行时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 任务执行消耗时间 单位：毫秒
     */
    @TableField("consume_time")
    private Long consumeTime;

}