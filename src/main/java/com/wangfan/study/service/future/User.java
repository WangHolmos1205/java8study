package com.wangfan.study.service.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class User {

    static CountDownLatch countDownLatch = new CountDownLatch(10);
    static final Semaphore sp = new Semaphore(10, true);
    static List<Integer> numberList = new ArrayList<>();



    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        sp.acquireUninterruptibly();
        IntStream.range(1,11).parallel().forEach(
                number -> {
                    numberList.add(number);
                    use(number);
                }
        );
        Thread.sleep(1000);
        sp.release();
        System.out.println("finish");
    }



    static void use(Integer number){
        new Thread(
                () -> {
                    int a = number;
                    System.out.println("get:" + a);
                    Boolean flag = Boolean.TRUE;
                    //System.out.println("answer:" + number);
                    //countDownLatch.countDown();
                    try {
                        sp.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("number:" + number);
                    countDownLatch.countDown();
                    sp.release();
//                    sp.acquireUninterruptibly();
//                    sp.release();
//                    while (flag) {
//                        if(answerNunber == countDownLatch.getCount()){
//                            flag = Boolean.FALSE;
//                            //System.out.println("answer:" + answerNunber);
//                            countDownLatch.countDown();
//                        }
//                    }
                }).start();
    }
}
