package com.my.test.cglib.domain;

import org.springframework.boot.autoconfigure.batch.BatchProperties;

public class BaseJob implements JobToDo {
    @Override
    public void work() {

        System.out.println("后端开发写后端代码");

    }
}
