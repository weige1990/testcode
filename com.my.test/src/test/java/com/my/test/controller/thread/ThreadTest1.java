package com.my.test.controller.thread;

import com.my.test.controller.thread.domain.RelateRunnable;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeList;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ThreadTest1 {

    /**
     * 1启动多个多线程任务,这些任务划分为一组
     * 2这一组任务中,其中一个任务出错,其他任务要尽快全部取消
     */
    @Test
    public void testCancelTogetherOne() {


        for (RelateRunnable task : runtaskAsync()) {

            new Thread(task).start();

        }

    }

    @Test
    public void testCancelTogether2() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 1000, TimeUnit.MILLISECONDS
                , new ArrayBlockingQueue(4)
                , new ThreadPoolExecutor.AbortPolicy());
        List<RelateRunnable> relateRunnables = runtaskAsync();

        List<Future> allFutures = new LinkedList<>();

        for (RelateRunnable relateRunnable : relateRunnables) {
            Future<?> future = threadPoolExecutor.submit((Callable<? extends Object>) relateRunnable);
            allFutures.add(future);
            try {
//                Thread.currentThread().sleep(1000);
                if (future.get() instanceof RuntimeException) {

                    for (Future futureTemp : allFutures) {

                        futureTemp.cancel(true);
                    }
//                    threadPoolExecutor.awaitTermination(0,TimeUnit.MILLISECONDS);

                }
            } catch (Exception e) {
                log.error("", e);
            }
        }


    }

    private List<RelateRunnable> runtaskAsync() {

        int count = 0;
        int taskSize = 6;

        RelateRunnable thisRunTemp;
        LinkedList<RelateRunnable> runs = new LinkedList<>();
        List<RelateRunnable> tasks = new ArrayList<>(taskSize);


        while (count < taskSize) {
            count += 1;
            if (count == 4) {
                thisRunTemp = new RelateRunnable("error", runs);
            } else {
                thisRunTemp = new RelateRunnable(count + "", runs);
            }
            tasks.add(thisRunTemp);
        }

        return tasks;

    }

    @Test
    public void testFinally() {
        System.out.println(returnNum());
    }


    private int returnNum() {
        try {
            throw new RuntimeException();
//            return 2;
        } catch (Exception e) {
            return 3;

        } finally {
            return 1;
        }
    }

}
