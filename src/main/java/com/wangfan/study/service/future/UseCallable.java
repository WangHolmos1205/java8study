package com.wangfan.study.service.future;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UseCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future future1 = new FutureTask(callable);
        System.out.println(future1.get());

    }

    private String useFutherAndCall() throws Exception{
        Future<String> future = new FutureTask(callable);
        return future.get();
    }

    static volatile AtomicInteger atomicInteger = new AtomicInteger(1);

    static Callable<Integer> callable = new Callable() {
        private int name;

        @Override
        public Integer call() throws Exception {
            name = atomicInteger.get();
            atomicInteger.addAndGet(12);
            return atomicInteger.get();
        }
    };
}
