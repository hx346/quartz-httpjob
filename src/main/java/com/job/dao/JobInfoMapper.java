package com.job.dao;

import com.job.config.MyBaseMapper;
import com.job.model.bo.JobInfoBO;
import com.job.model.po.JobInfo;
import com.job.model.query.JobInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */
public interface JobInfoMapper extends MyBaseMapper<JobInfo> {

    /**
     * 查询jobInfo
     *
     * @param query
     * @return
     */
    List<JobInfoBO> selectJobInfo(@Param(value = "query") JobInfoQuery query);

}