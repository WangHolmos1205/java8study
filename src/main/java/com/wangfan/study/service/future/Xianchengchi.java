package com.wangfan.study.service.future;

import lombok.Data;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Xianchengchi {
    //ExecutorService
    static ExecutorService executorService;

    public static void main(String[] args) {
        create();
        IntStream.range(0,20).parallel().forEach(
                number -> use(number)
        );
    }

    static void create(){
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(20);
        executorService = new ThreadPoolExecutor(2,5, 60,
                TimeUnit.MILLISECONDS,workQueue);
    }

    static void use(Integer number){
        Task task = new Task(number.toString());
        executorService.submit(task);
    }

    @Data
    static
    class Task implements Runnable{
        private String name;
        Task(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("l" + ":" + name);
        }
    }
}
