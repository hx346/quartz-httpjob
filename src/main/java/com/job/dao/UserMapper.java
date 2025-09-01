package com.job.dao;


import com.job.model.po.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户信息表的 Mapper 接口，提供用户相关的数据库操作。
 */
public interface UserMapper extends Mapper<User> {
}