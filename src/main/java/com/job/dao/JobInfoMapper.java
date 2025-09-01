package com.job.dao;

import com.job.model.bo.JobInfoBO;
import com.job.model.po.JobInfo;
import com.job.model.query.JobInfoQuery;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 任务信息表的 Mapper 接口，负责对 {@link JobInfo} 的数据库操作。
 * 提供自定义的查询方法以支持分页和条件过滤。
 */
public interface JobInfoMapper extends Mapper<JobInfo> {

    /**
     * 查询jobInfo
     * @param query
     * @return
     */
    List<JobInfoBO> selectJobInfo(@Param(value = "query") JobInfoQuery query);

}