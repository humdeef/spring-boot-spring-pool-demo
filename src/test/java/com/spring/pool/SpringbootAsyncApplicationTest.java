package com.spring.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.spring.pool.task.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncApplicationTests {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {
        Future<Long> future = asyncTask.doTask1(1);
        try {
            future.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("已经超时了！");
            e.printStackTrace();
        }
        logger.info("All tasks finished.");
    }


    @Test
    public void AsyncTaskTest1() throws InterruptedException, ExecutionException {

        for (int i = 0; i < 5000; i++) {
            asyncTask.doTask1(1);
        }

        logger.info("All tasks finished.");
    }
}