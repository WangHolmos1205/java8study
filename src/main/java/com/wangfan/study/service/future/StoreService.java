package com.wangfan.study.service.future;

import java.util.Currency;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public interface StoreService {

    /**
     * java8默认方法 获取商品价格
     * @param name
     * @return
     */
    /**
     * 默认方法是为了让接口的实现类，自动拥有默认实现方法， 这样接口迭代更新加入新的方法后，
     * 以前的失联类能够自动继承
     *
     * @param name
     * @return
     */
    default Double getPrice(String name) {
        try {
            Thread.sleep(200L);
        } catch (Exception e) {
        }
        System.out.println("1D");
        return 1D;
    }

    /**
     * java8默认方法 获取商品信息
     *
     * @param name
     * @return
     */
    default String getMessage(String name) {
        try {
            Thread.sleep(200L);
        } catch (Exception e) {

        }
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
        Condition condition = new Condition() {
            @Override
            public void await() throws InterruptedException {

            }

            @Override
            public void awaitUninterruptibly() {

            }

            @Override
            public long awaitNanos(long nanosTimeout) throws InterruptedException {
                return 0;
            }

            @Override
            public boolean await(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public boolean awaitUntil(Date deadline) throws InterruptedException {
                return false;
            }

            @Override
            public void signal() {

            }

            @Override
            public void signalAll() {

            }
        };
        //ThreadPoolExecutor t = new ThreadPoolExecutor(null);
        ReadWriteLock lock = new ReadWriteLock() {
            @Override
            public Lock readLock() {
                return null;
            }

            @Override
            public Lock writeLock() {
                return null;
            }
        };
        Thread thread = new Thread();
        try {
            thread.join(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Condition condition1;
        System.out.println(name);
        return name;
    }


    public static void main(String[] args) {
        Condition condition =new Condition() {
            @Override
            public void await() throws InterruptedException {

            }

            @Override
            public void awaitUninterruptibly() {

            }

            @Override
            public long awaitNanos(long nanosTimeout) throws InterruptedException {
                return 0;
            }

            @Override
            public boolean await(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public boolean awaitUntil(Date deadline) throws InterruptedException {
                return false;
            }

            @Override
            public void signal() {

            }

            @Override
            public void signalAll() {

            }
        };
    }
}
