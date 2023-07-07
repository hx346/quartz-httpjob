package com.job.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author
 */
@TableName("schedule_job_info")
@Data
public class JobInfo {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求方式 GET POST
     */
    private String method;

    /**
     * 标题
     */
    private String title;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 请求参数
     */
    private String params;
    /**
     * 请求头列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, String> headers;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分组id，关联schedule_job_group
     */
    @TableField("job_group_id")
    private Integer jobGroupId;

    /**
     * 任务状态 -1 已删除；0 已暂停； 1 运行中
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

}