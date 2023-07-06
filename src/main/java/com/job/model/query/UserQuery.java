package com.job.model.query;

import com.job.model.vo.PageVO;
import lombok.Data;

/**
 * @author  
 * @date 2020/3/25 16:10
 **/
@Data
public class UserQuery extends PageVO {

    /**
     * 用户名
     */
    private String username;

}
