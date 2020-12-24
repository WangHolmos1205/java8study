package com.wangfan.study.service.impl.future;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestRunnable implements Runnable{

    private static TestRunnable testRunnable = new TestRunnable("number");
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock(Boolean.TRUE);
    static Lock lock = new ReentrantLock();
    static Lock lockOne = new ReentrantLock();
    static Lock lockTwo = new ReentrantLock();
    static CountDownLatch countDownLatch = new CountDownLatch(10);
    



    public static void main(String[] args) throws Exception{
//        Thread oneThread = new Thread(new TestRunnable("one"));
//        Thread twoThread = new Thread(new TestRunnable("two"));
//        oneThread.setPriority(Thread.MIN_PRIORITY);
//        twoThread.setPriority(Thread.MAX_PRIORITY);
//
//        oneThread.start();
//        twoThread.start();
        for(int i =0 ; i< 10; i++){

            Thread thread = new Thread(new TestRunnable("x:" + i));
            thread.start();
        }
        countDownLatch.await();
        System.out.println("finish");
        //twoThread.interrupt();
    }

    public volatile static Integer number = 0;
    static TestRunnable hopeLock = new TestRunnable("lock");
    public String name;

    TestRunnable(String name){
        this.name = name;
    }



    @Override
    public void run() {
        useCountDownLatch();
//        System.out.println(number);
//        for(int i=0;i <1; i++){
//            deadLock();
//        }
    }


    private void useLock(){
        lock.lock();
        number = number + 1;
        if (number % 701 == 0) {
            System.out.println(name + ":" + number);
        }
        lock.unlock();
    }

    private void useSys(){
        synchronized (lock) {
                number = number + 1;
                if (number % 701 == 0) {
                    System.out.println(name + ":" + number);
                }
          }
    }

    private void deadLock()  {
        try {
            if(name == "one"){
                lockOne.lockInterruptibly();
                Thread.sleep(100);
                lockTwo.tryLock(5, TimeUnit.SECONDS);
                //lockTwo.lockInterruptibly();
            }else {
                lockTwo.lockInterruptibly();
                Thread.sleep(100);
                //lockOne.lockInterruptibly();
                lockOne.tryLock(5, TimeUnit.SECONDS);

            }
        }catch (Exception e){

            lockOne.unlock();
            lockTwo.unlock();
            e.printStackTrace();
        }
        lockOne.unlock();
        lockTwo.unlock();
    }

    private void useCountDownLatch(){
        Random r = new Random();
        double sleep = r.nextInt(100);
        System.out.println(name+ "-" + sleep);
        try {
            Thread.sleep((long) sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}
