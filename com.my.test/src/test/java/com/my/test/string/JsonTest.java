package com.my.test.string;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.my.test.field.domain.PrivateEntity;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;

public class JsonTest {
    @Test
    public void testTransform() throws JsonProcessingException {
        String json = "[{" +
                "\"name\":\"小明\"," +
                "\"age\":18}," +
                "{\"name\":\"小红\"," +
                "\"age\":19}]";

        String json2 = "{" +
                "\"name\":\"小明\"," +
                "\"age\":18}";


        ObjectMapper objectMapper = new ObjectMapper();

        PrivateEntity privateEntity = parseObject(json2, PrivateEntity.class);


        System.out.println(privateEntity);
    }

    @Test
    public void testTransform1() throws JsonProcessingException {
        String json = "[{" +
                "\"name\":\"小明\"," +
                "\"age\":18}," +
                "{\"name\":\"小红\"," +
                "\"age\":19}]";


        ObjectMapper objectMapper = new ObjectMapper();
        List<PrivateEntity> privateEntities = objectMapper.readValue(json, new TypeReference<List<PrivateEntity>>() {
        });


        HttpEntity header1 = new HttpEntity(privateEntities, new HttpHeaders());
        HttpEntity header2 = new HttpEntity(JSON.toJSON(privateEntities), new HttpHeaders());


        System.out.println(header1);
        System.out.println(header2);
    }


    @Test
    public void testTransform3() throws JsonProcessingException {
        String json = toJsonStr();


        ObjectMapper objectMapper = new ObjectMapper();
        List<PrivateEntity> privateEntities = objectMapper.readValue(json, new TypeReference<List<PrivateEntity>>() {
        });


        Object parseObj = JSON.parse(json);


        System.out.println(parseObj);
    }


    private String toJsonStr() {
        String json = "[{" +
                "\"name\":\"小明\"," +
                "\"age\":18}," +
                "{\"name\":\"小红\"," +
                "\"age\":19}]";

        return json;
    }


    public static <T> T parseObject(String json, Class<T> clz) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, new TypeReference<T>() {
        });
    }


}
