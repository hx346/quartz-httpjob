package com.job.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 任务日志报表实体
 *
 * @author
 */
@TableName("schedule_job_log_report")
@Data
public class JobLogReport {
    @TableId
    private Integer id;

    /**
     * 调度-时间
     */
    private Date day;

    /**
     * 运行中-日志数量
     */
    @TableField("running_count")
    private Integer runningCount;

    /**
     * 执行成功-日志数量
     */
    @TableField("success_count")
    private Integer successCount;

    /**
     * 执行失败-日志数量
     */
    @TableField("fail_count")
    private Integer failCount;

}