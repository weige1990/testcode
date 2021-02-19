package com.my.test.controller.thread.domain;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;

public class RelateRunnable implements Callable ,Runnable {


    private RelateRunnable preRun;//上一个任务
    private RelateRunnable nextRun;//下一个任务
    private String name;
    private volatile Thread ownRunThread;
    private volatile LinkedList<RelateRunnable> runs = new LinkedList<>();//第一个任务


    public RelateRunnable(String name, LinkedList<RelateRunnable> runs) {
        this.name = name;
        this.runs = runs;
        runs.add(this);
    }

    public String getName() {
        return name;
    }


    public Thread getOwnRunThread() {
        return ownRunThread;
    }


    @Override
    public Object call() {
        RuntimeException runtimeException = new RuntimeException("名字为错误停止所有线程!");
        try {
            ownRunThread = Thread.currentThread();
            if ("error".equals(name)) {
                throw runtimeException;
            }


            System.out.println(name);

            return this;


        } catch (Exception e) {
            try {

                Iterator<RelateRunnable> iterator = runs.iterator();

                RelateRunnable relateRunTemp = null;

                while (iterator.hasNext()) {
                    relateRunTemp = iterator.next();

                    if (relateRunTemp != null) {
                        relateRunTemp.
                                getOwnRunThread().
                                stop();//直接结束
                    }


                }
            } catch (Exception outterException) {

                outterException.printStackTrace();

            }

            return runtimeException;

        }
    }

    @Override
    public void run() {
        call();
    }
}
