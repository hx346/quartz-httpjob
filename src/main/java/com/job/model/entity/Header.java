package com.job.model.entity;

import lombok.Data;

/**
 * @author
 * @Description //请求头 $
 */
@Data
public class Header {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 属性值
     */
    private String value;
}
