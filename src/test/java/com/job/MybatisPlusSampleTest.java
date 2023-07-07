package com.job;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.job.dao.JobInfoMapper;
import com.job.model.query.JobInfoQuery;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

@MybatisPlusTest
class MybatisPlusSampleTest {

    @Resource
    private JobInfoMapper jobInfoMapper;

    @Test
    void testSelect() {
        jobInfoMapper.selectJobInfo(new JobInfoQuery());
    }
}