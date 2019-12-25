package com.wangfan.study.service.impl.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FindMessage {

    public static void main(String[] args) {
        CatStoreServiceImpl catStoreService = new CatStoreServiceImpl();
        SlowStoreServiceImpl slowStoreService = new SlowStoreServiceImpl();
        List<String> messages = getList(15);
        long start = System.nanoTime();
        bad(messages,catStoreService,slowStoreService);
        //useParallel(messages,catStoreService,slowStoreService);

        //useSpliterator(messages,catStoreService,slowStoreService);
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
        long startTwo = System.nanoTime();
        messages.stream().parallel().forEach(
                message ->{
                    //userFuture(message,catStoreService,slowStoreService);
                    userAllFuture(message, catStoreService, slowStoreService);
                });
        long retrievalTimeTwo = ((System.nanoTime() - startTwo) / 1_000_000);
        System.out.println("Price returned after " + retrievalTimeTwo + " msecs");


    }

    /**
     * 最差的串行方法
     * @param messages
     * @param catStoreService
     * @param slowStoreService
     */
    private static void bad(List<String> messages,
                     CatStoreServiceImpl catStoreService,
                     SlowStoreServiceImpl slowStoreService){
        messages.forEach(message ->{
            doMessage(message,
                    catStoreService,
                    slowStoreService);
        });
    }

    /**
     * 并行处理
     * @param messages
     * @param catStoreService
     * @param slowStoreService
     */
    private static void useParallel(List<String> messages,
                            CatStoreServiceImpl catStoreService,
                            SlowStoreServiceImpl slowStoreService){
        messages.stream()
                // 任务并行处理
                .parallel()
                .forEach(message ->{
                    doMessage(message,
                            catStoreService,
                            slowStoreService);
            });
    }

    /**
     * 使用迭代遍历
     * @param messages
     * @param catStoreService
     * @param slowStoreService
     */
    private static void useSpliterator(List<String> messages,
                                       CatStoreServiceImpl catStoreService,
                                       SlowStoreServiceImpl slowStoreService){
        // 并行遍历迭代
        Spliterator spliterator = messages.spliterator();
        Stream<String> stream = StreamSupport.stream(spliterator, true);
        stream.forEach(message ->{
            doMessage(message,
                    catStoreService,
                    slowStoreService);
        });
    }

    /**
     * 使用CompletableFuture并行处理问题
     * @param message
     * @param catStoreService
     * @param slowStoreService
     */
    private static void userFuture(String message,
                                   CatStoreServiceImpl catStoreService,
                                   SlowStoreServiceImpl slowStoreService){
            CompletableFuture catFuture = doFuture(catStoreService::getMessage, message);
            CompletableFuture slowFuture = doFuture(slowStoreService::getMessage, message);
            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(catFuture, slowFuture);
            try {
                combinedFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
    }

    private static void userAllFuture(String message,
                                   CatStoreServiceImpl catStoreService,
                                   SlowStoreServiceImpl slowStoreService){
        CompletableFuture catFuture = doFuture(catStoreService::getMessage, message);
        CompletableFuture slowFuture = doFuture(slowStoreService::getMessage, message);
    }

    private static List<String> getList(Integer number){
        List<String> list = new ArrayList();
        for(int i=1; i<=number; i++){
            list.add(String.format("第%d件商品", i));
        }
        return list;
    }

    /**
     * 数据查询底层方法，类似单元测试中的准备测试
     * @param name
     * @param catStoreService
     * @param slowStoreService
     */
    private static void doMessage(String name,
                                   CatStoreServiceImpl catStoreService,
                                   SlowStoreServiceImpl slowStoreService){
        catStoreService.getMessage(name);
        //catStoreService.getPrice(name);
        slowStoreService.getMessage(name);
        //slowStoreService.getPrice(name);
    }

    private static CompletableFuture doFuture(Consumer<String> consumer, String message){
        return CompletableFuture.runAsync(() ->{
            consumer.accept(message);
        });
    }

}
