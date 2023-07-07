package com.job.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

public class StringToMapConverter implements Converter<String, Map<String, String>> {

    @Override
    public Map<String, String> convert(String source) {
        Map<String, String> map = new HashMap<>(); // 创建一个新的HashMap对象来存储键值对

        if (source != null && !source.isEmpty()) { // 检查源字符串不为空且非空字符串
            String[] keyValuePairs = source.split(","); // 使用逗号将字符串拆分成键值对数组
            for (String pair : keyValuePairs) { // 遍历键值对数组
                String[] entry = pair.split(":"); // 使用冒号将键值对拆分成键和值的数组
                if (entry.length == 2) { // 检查键值对是否包含键和值，长度为2
                    map.put(entry[0].trim(), entry[1].trim()); // 将键和值存储到Map对象中，使用trim()方法去除空格
                }
            }
        }

        return map; // 返回转换后的Map对象
    }
}