package com.job.handler;

import cn.hutool.json.JSONUtil;
import com.job.model.entity.Header;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author hexin
 */
public class HeaderListTypeHandler extends BaseTypeHandler<List<Header>> {

    /**
     * 将List<Header>对象转换为JSON字符串，并存储到数据库
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Header> parameter, JdbcType jdbcType) throws SQLException {
        // 将List<Header>对象转换为JSON字符串
        String jsonString = JSONUtil.toJsonStr(parameter);
        ps.setString(i, jsonString);
    }

    /**
     * 从ResultSet中获取字段值，并将其转换为List<Header>对象
     */
    @Override
    public List<Header> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从ResultSet中获取字段值
        String jsonString = rs.getString(columnName);
        // 将JSON字符串转换为List<Header>对象
        return JSONUtil.toList(JSONUtil.parseArray(jsonString), Header.class);
    }

    /**
     * 从ResultSet中获取字段值，并将其转换为List<Header>对象
     */
    @Override
    public List<Header> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 从ResultSet中获取字段值
        String jsonString = rs.getString(columnIndex);
        // 将JSON字符串转换为List<Header>对象
        return JSONUtil.toList(JSONUtil.parseArray(jsonString), Header.class);
    }

    /**
     * 从CallableStatement中获取字段值，并将其转换为List<Header>对象
     */
    @Override
    public List<Header> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 从CallableStatement中获取字段值
        String jsonString = cs.getString(columnIndex);
        // 将JSON字符串转换为List<Header>对象
        return JSONUtil.toList(JSONUtil.parseArray(jsonString), Header.class);
    }
}