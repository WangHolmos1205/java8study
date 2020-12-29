package com.wangfan.study.service.future;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class MainMessage {

    static volatile CountDownLatch countDownLatchone = new CountDownLatch(2);
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws Exception {

        useThread(2000);
        useThread(5000);
        useThread(4000);
        readWriteLock.writeLock().lock();
        System.out.println("finish");
        readWriteLock.writeLock().unlock();
    }

    private static void useThread(Integer time){
        new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time);
            readWriteLock.writeLock().unlock();
        }).start();
    }

    private static void countDown() throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(1);
        IntStream.range(0,10).forEach(
                number -> {
                    // useCountDown()
                }
        );
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1");
            countDownLatch.countDown();
            //useCountDown();
        }).run();
        new Thread(() -> {
            System.out.println("t2");
            countDownLatch.countDown();
            //useCountDown();
        }).start();
        countDownLatch.await();
        System.out.println("finish");
    }
    public static void useCountDown(){
        //System.out.println(countDownLatch.getCount());
        //countDownLatch.countDown();
    }
}
