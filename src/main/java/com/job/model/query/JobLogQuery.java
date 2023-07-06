package com.job.model.query;

import com.job.model.vo.PageVO;
import lombok.Data;

/**
 * @author  
 * @date 2020/3/25 16:10
 **/
@Data
public class JobLogQuery extends PageVO {

    /**
     * http任务id
     */
    private Integer jobInfoId;

}
