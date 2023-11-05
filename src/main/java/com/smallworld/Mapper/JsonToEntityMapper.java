package com.smallworld.Mapper;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.util.List;

public class JsonToEntityMapper<T> {
    public List<T> deserializeFromJson(String filePath, Class<T> clazz) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            JavaType javaType = typeFactory.constructParametricType(List.class, clazz);
            return objectMapper.readValue(new File(filePath), javaType);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


