package com.my.test.redis;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.my.test.TestStarter;
import com.my.test.domainBusiness.sale.domain.OrderDetailExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest(classes = TestStarter.class)
@Slf4j
public class RedisBasicTest extends AbstractTestNGSpringContextTests {

    private static String testKeyIncr = "test-incr:1";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisTemplate<String,Object> objectRedisTemplate;

    @Test(groups = {"testIncrWhichKeyNotExists"})
    /**
     *
     */
    public void testIncrWhichKeyNotExists() {
        Object data = provideTestKey1()[0];
        log.info("测试得到redis 自增的数为:{}", redisTemplate.opsForValue().
                increment(data.toString()).toString());

    }



    /**
     * 清除
     */
//    @AfterMethod(groups = {"testIncrWhichKeyNotExists"})
    @Test
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


    public OrderDetailExcel newOrderExcel() {
        OrderDetailExcel orderDetailExcel = new OrderDetailExcel();


        orderDetailExcel.setAddress("abc");
        orderDetailExcel.setActualPaidTime(LocalDateTime.now());
        orderDetailExcel.setAllowInvoice(1);
        orderDetailExcel.setBusinessLine("MRO");
        orderDetailExcel.setCancelReason("不想要");
        orderDetailExcel.setBrand("3M");
        orderDetailExcel.setConsignee("YWX003");
        orderDetailExcel.setCustomerName(UUID.randomUUID().toString());
        orderDetailExcel.setDetailPayPrice(new BigDecimal(1000 * (Math.random())).setScale(4, RoundingMode.HALF_UP));
        orderDetailExcel.setOrderNo(UUID.randomUUID().toString());


        return orderDetailExcel;
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
                set(jsonTest,JSON.toJSONString(data));
        log.info("测试得到redis key 值:{} ,插入json字符串:{}",jsonTest ,jsonString);

    }

    @Test(groups = {"testIncrWhichKeyNotExists"})

    public void testInsertObjectWithObjectRedisTemplate() {
        Object data = newOrderExcel();

        final String jsonTest = "json_object_test";

        objectRedisTemplate.opsForValue().
                set(jsonTest,data);
        log.info("测试得到redis key 值:{} ,插入Object 转成json字符串:{}",jsonTest , JSON.toJSONString(data));

    }


    @Test(groups = {"testIncrWhichKeyNotExists"})

    public void testInsertLocalDateTimeWithObjectRedisTemplate() {
        Object data = newOrderExcel();

        final String jsonTest = "json_localdatetime_test";

        objectRedisTemplate.opsForValue().
                set(jsonTest,data);
        log.info("测试得到redis key 值:{} ,插入localdatetime 转成json字符串:{}",jsonTest , JSON.toJSONString(data));

    }
}
