package com.job.controller;

import com.job.constant.JobConstant;
import com.job.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  
 * @date 2020/3/24 15:08
 **/
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
    /**
     * @description get测试接口
     * @author hexin
     * @throws
     * @return ResultVO
     * @time 2023-7-6 10:00
     */
    @RequestMapping("/get")
    public ResultVO testUrl() {

        String res = "1";
        if (JobConstant.SUCCESS_CODE.equals(res)) {
            return ResultVO.success("测试成功");
        } else {
            return ResultVO.failure(res);
        }
    }
}
