package com.wangfan.study.service.impl.thread;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ThreadService{

   static class Job implements Runnable{

       CountDownLatch countDownLatch;
       AtomicInteger atomicInteger;
       int name;
       String[] numbers;
       Job(CountDownLatch countDownLatch, int name, String[] numbers, AtomicInteger atomicInteger){
           this.countDownLatch = countDownLatch;
           this.name = name;
           this.numbers = numbers;
           this.atomicInteger = atomicInteger;
       }
       @Override
       public void run() {
           //System.out.println(now);
           while (atomicInteger.get() < 6){
               int now = atomicInteger.get();
               if(now % 2 == name){
                   System.out.println("now:" + now);
                   int number = (now-name)/2;
                   System.out.println(numbers[number]);
                   atomicInteger.addAndGet(1);
               }
           }
           countDownLatch.countDown();
       }
   }

    static class Lock implements Runnable{

       Object lock;
        int name;
        String[] numbers;
        public static int TOTAL = 0;
        Lock(Object lock, int name, String[] numbers){
            this.lock = lock;
            this.name = name;
            this.numbers = numbers;
        }
        @Override
        public void run() {
            while (TOTAL < 6){
                synchronized (lock) {
                    if (TOTAL % 2 == name) {
                        int number = (TOTAL-name)/2;
                        System.out.println(numbers[number]);
                        TOTAL++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch countDownLatchOne = new CountDownLatch(2);

        String[] numbers = new String[]{"1","2","3"};
        String[] bytes = new String[]{"a","b","c"};
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Job numberJob = new Job(countDownLatchOne,1, numbers, atomicInteger);
        Job byteJob = new Job(countDownLatchOne,0, bytes, atomicInteger);
        Lock numberLock = new Lock(countDownLatchOne,0, numbers);
        Lock byteLock = new Lock(countDownLatchOne,1, bytes);
        executor.execute(byteJob);
        executor.execute(numberJob);
        try {
            countDownLatchOne.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}