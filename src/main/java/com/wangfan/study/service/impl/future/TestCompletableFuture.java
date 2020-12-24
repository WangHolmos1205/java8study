package com.wangfan.study.service.impl.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestCompletableFuture {
    public static void main(String[] args) {
        try {
            useFuture("xxxx:");
            thenApply();
        }catch (Exception e){

        }
    }


    private static void useFuture(String message) throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            String aaa = message + "234";
            return aaa;
        });
        System.out.println(completableFuture.get());
    }

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1="+result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t*5;
                System.out.println("result2="+result);
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }
}
