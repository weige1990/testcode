package com.my.test.redis;

import com.alibaba.fastjson.JSON;
import com.my.test.TestStarter;
import com.my.test.domainBusiness.sale.domain.ODetailExcel;
import com.my.test.domainBusiness.test.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest(classes = TestStarter.class)
@Slf4j
public class RedisBasicTest extends AbstractTestNGSpringContextTests {

    private static String testKeyIncr = "test_incr:1";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisTemplate<String, Object> objectRedisTemplate;

    @Test(groups = {"testIncrWhichKeyNotExists", "autoDeletekey"})
    /**
     *
     */
    public void testIncrWhichKeyNotExists() {
        Object data = provideTestKey1()[0];
        log.info("测试得到redis 自增的数为:{}", redisTemplate.opsForValue().
                increment(data.toString()).toString());

        deleteKey(data.toString());

    }


    /**
     * 清除
     */
//    @AfterMethod(groups = {"testIncrWhichKeyNotExists"})
//    @Test
    public void deleteKeyAfterTIWNE() {
        Object data = provideTestKey1()[0];
        redisTemplate.delete(data.toString());
        log.info("测试删除 自增key值为:{}", data.toString());

    }


    public Object[] provideTestKey1() {
        Object[] data = new Object[1];
        data[0] = testKeyIncr;
        return data;
    }


    public ODetailExcel newOrderExcel() {
        ODetailExcel oDetailExcel = new ODetailExcel();





        return oDetailExcel;
    }

    /**
     * 测试Sting类型json字符串插入
     */
    @Test
    public void testInsertJsonStrWithStrRedisTemplate() {
        Object data = newOrderExcel();
        String jsonString = JSON.toJSONString(data);
        final String jsonTest = "json_test";

        redisTemplate.opsForValue().
                set(jsonTest, JSON.toJSONString(data));
        log.info("测试得到redis key 值:{} ,插入json字符串:{}", jsonTest, jsonString);
        deleteKey(jsonTest);

    }

    @Test(groups = {"testIncrWhichKeyNotExists"})

    public void testInsertObjectWithObjectRedisTemplate() {
        Object data = newOrderExcel();

        final String jsonTest = "json_object_test";

        objectRedisTemplate.opsForValue().
                set(jsonTest, data);
        log.info("测试得到redis key 值:{} ,插入Object 转成json字符串:{}", jsonTest, JSON.toJSONString(data));
        deleteKey(jsonTest);

    }


    @Test(groups = {"testIncrWhichKeyNotExists"})

    public void testInsertLocalDateTimeWithObjectRedisTemplate() {
        User user = new User();
        user.setBirthday(LocalDateTime.now());
        final String jsonTest = "json_localdatetime_test";
        objectRedisTemplate.opsForValue().
                set(jsonTest, user);
        log.info("测试得到redis key 值:{} ,插入localdatetime 转成json字符串:{}", jsonTest, JSON.toJSONString(user));
        deleteKey(jsonTest);

    }


    private void deleteKey(String key) {
       redisTemplate.delete(key);
    }
}
