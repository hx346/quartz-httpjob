package com.job.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.model.entity.Header;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.util.List;

public class StringToListHeaderConverter implements Converter<String, List<Header>> {

    @Override
    public List<Header> convert(String source) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 使用 Jackson ObjectMapper 将字符串反序列化为 List<Header>
            return mapper.readValue(source, new TypeReference<List<Header>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}