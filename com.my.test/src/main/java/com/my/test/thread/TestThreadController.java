package com.my.test.thread;

import com.my.test.domainBusiness.test.domain.Result;
import com.my.test.thread.util.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/testThread")
@RestController
@Slf4j
public class TestThreadController {
@Autowired
@Qualifier(value = "testThreadPools")
private ThreadPoolExecutor testThreadPools;


//private static volatile String flag;


    @GetMapping("/testThreadExtend")
    public Result testThreadExtend()
    {


        String flag=  UUID.randomUUID().toString();
        for (int i=0;i<=1000;i++) {
            createThreads(flag);
        }



        return new Result();
    }


    private void createThreads(){

       String flag=UUID.randomUUID().toString();
        log.info("接口:生成uuid:{}",flag);

        ThreadLocal userTest = ThreadUtils.getUserTest();
        userTest.set(flag);

        testThreadPools.submit(()->{
            if(flag.equals(userTest.get()))
            {
                log.info("一致,外层信息:{},里层信息{}",flag,userTest.get());

            }
            else
            {
                log.error("不一致,外层信息:{},里层信息{}",flag,userTest.get());
            }


        });

    }
    private void createThreads(String flag){


        ThreadLocal userTest = ThreadUtils.getUserTest();
        userTest.set(flag);

        testThreadPools.submit(()->{
            if(flag.equals(userTest.get()))
            {
                log.info("一致,外层信息:{},里层信息{}",flag,userTest.get());

            }
            else
            {
                log.error("不一致,外层信息:{},里层信息{}",flag,userTest.get());
            }


        });

    }
}
