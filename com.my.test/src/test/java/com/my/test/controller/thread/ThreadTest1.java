package com.my.test.controller.thread;

import com.my.test.controller.thread.domain.RelateRunnable;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadTest1 {

    /**
     * 1启动多个多线程任务,这些任务划分为一组
     * 2这一组任务中,其中一个任务出错,其他任务要尽快全部取消
     */
    @Test
    public void testCanceTogether() {
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


        for (RelateRunnable task : tasks) {

            task.run();

        }

    }
}
