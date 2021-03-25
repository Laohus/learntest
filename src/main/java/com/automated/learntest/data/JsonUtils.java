package com.automated.learntest.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtils {

    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转化为json字符串
     * @param data
     * @return
     */
    public static String ObjectToJson(Object data){
        try{
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串转化为对象
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType){
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串数据转化为pojo对象list
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
