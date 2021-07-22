package com.my.test.thread.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jws.Oneway;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadUtils {

    private static ThreadLocal userTest=new TransmittableThreadLocal();

    @Bean
    public ThreadPoolExecutor testThreadPools(){
        return new ThreadPoolExecutor(2,4,1000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));
    }


    public static ThreadLocal getUserTest()
    {
        return  userTest;

    }



}
