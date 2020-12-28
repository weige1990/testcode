package com.my.test.lamda;

import com.alibaba.fastjson.JSON;
import com.my.test.domainBusiness.carrier.domain.CarrierDTO;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class TestJson {

    @Test
    public void testJson1()
    {
        System.out.println(JSON.toJSON(new CarrierDTO(
        null,"41号用户", "num4_carrier", "no41c", 1, 1, "成步堂龙一", "12348918427", "4134907028@qq.com", "佛山市禅城区江湾二路5号之一第3层301室之41", "kio889", "http://dev.cdn.yigongpin.com/Fgn29WfJi6OfahDw-ZtFaGFHT2OA", "御剑怜侍", LocalDateTime.now(),"克劳德",LocalDateTime.now().plusDays(1), 1)
        ));

    }
}