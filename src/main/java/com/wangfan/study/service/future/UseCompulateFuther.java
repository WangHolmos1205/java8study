package com.wangfan.study.service.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class UseCompulateFuther {

    public static void main(String[] args) throws Exception{
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(12);


        CompletableFuture one = useCom("1", atomicInteger);
        CompletableFuture two = useCom("2", atomicInteger);
//        CompletableFuture third = useCom("3", atomicInteger);
//        CompletableFuture four = useCom("4", atomicInteger);
//        CompletableFuture five = useCom("5", atomicInteger);
//        CompletableFuture six = useCom("6", atomicInteger);
        //one.thenApply(a -> "a");

        //CompletableFuture answer = CompletableFuture.allOf(one,two);
        //CompletableFuture completableFuture = userComTwo(one);
        useApplyToEither(one,two);
    }

    private static void useApplyToEither(CompletableFuture one, CompletableFuture two) throws Exception{
        CompletableFuture<String> next = one.applyToEither(two, new Function<String, String>() {
            @Override
            public String apply(String aaa) {
                return "next" + aaa;
            }
        });
        System.out.println(next.get());
    }


    private static CompletableFuture userComTwo( CompletableFuture startCom){
        CompletableFuture completableFuture = CompletableFuture
                .supplyAsync(() -> {
            System.out.println(123);
            return 15;
        });
        completableFuture.thenApply(a ->{
           System.out.println("aaa");
           System.out.println("xxx:" + a);
           return "12";
        }).runAfterBoth(CompletableFuture.runAsync(() ->{

              System.out.println("next");
    }), () -> System.out.println("123"));
//                .runAfterBoth(startCom,() ->{
//            System.out.println("one");
//        });
        //completableFuture.runAfterBoth(startCom,() -> System.out.println("finish"));
        return  completableFuture;
    }

    private static CompletableFuture useCom(String aaa, AtomicInteger atomicInteger){
        return CompletableFuture.supplyAsync(() ->{
            atomicInteger.addAndGet(1);
            System.out.println(aaa+ ":" + atomicInteger.get());
            return aaa;
        });
    }
}
