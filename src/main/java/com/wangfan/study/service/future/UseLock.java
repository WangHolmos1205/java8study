package com.wangfan.study.service.future;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class UseLock {

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws Exception{

    }

    private static void one (){
//        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.set(0);
//        IntStream.range(0,5).parallel().forEach(
//                a -> {
//                    //userReadLock(a,atomicInteger);
//                    try {
//                        useWriteLock(a,atomicInteger);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//        );
    }

    public static void userReadLock(Integer name, AtomicInteger atomicInteger){
        readWriteLock.readLock().tryLock();
        atomicInteger.addAndGet(12);
        System.out.println(name + ":" + atomicInteger);
        readWriteLock.readLock().unlock();
    }

    public static void useWriteLock(Integer name, AtomicInteger atomicInteger) throws InterruptedException {
        readWriteLock.writeLock().lock();
        atomicInteger.addAndGet(12);
        System.out.println(name + ":" + atomicInteger);
        readWriteLock.writeLock().unlock();
    }
}
