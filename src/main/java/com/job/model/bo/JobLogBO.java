package com.job.model.bo;

import com.job.model.po.JobLog;
import lombok.Data;

/**
 * @author  
 * @date 2020/3/25 17:46
 **/
@Data
public class JobLogBO extends JobLog {

    /**
     * 分组名
     */
    public String jobGroupName;

    /**
     * 任务标题
     */
    public String title;

}
