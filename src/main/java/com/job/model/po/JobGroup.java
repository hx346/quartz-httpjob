package com.job.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author
 */
@TableName("schedule_job_group")
@Data
public class JobGroup {
    /**
     * 主键
     *
     * @GeneratedValue insert操作后会把主键id映射到实体id上
     */
    @TableId
    private Integer id;

    /**
     * 分组名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}