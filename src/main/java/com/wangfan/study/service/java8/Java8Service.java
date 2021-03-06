package com.wangfan.study.service.java8;

import com.wangfan.study.model.java8.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public interface Java8Service {

    /**
     * 默认方法是为了让接口的实现类，自动拥有默认实现方法， 这样接口迭代更新加入新的方法后，
     * 以前的失联类能够自动继承
     *
     * @param
     * @return
     */
    default List<String> getStringList(){
        List<String> templeteList = new ArrayList<>();
        templeteList.add("");
        templeteList.add("String");
        templeteList.add("String");
        templeteList.add("match");
        return templeteList;
    }

    default List<Integer> getIntegerList(){
        List<Integer> templeteList = new ArrayList<>();
        templeteList.add(8);
        templeteList.add(3);
        templeteList.add(9);
        templeteList.add(0);
        templeteList.add(5);
        templeteList.add(8);
        return templeteList;
    }

    default List<Dish> getDishList(){
        Dish dishOne = new Dish("蒸鱼", "fish", 20);
        Dish dishTwo = new Dish("烤鱼", "fish", 100);
        Dish dishThree = new Dish("红烧鱼", "fish", 300);

        Dish dishFour = new Dish("回锅肉", "meat", 100);
        Dish dishFive = new Dish("烤肉", "meat", 200);
        Dish dishSix = new Dish("红烧肉", "meat", 400);

        Dish dishSeven = new Dish("炒白菜", "vegetable", 5);
        Dish dishEight = new Dish("水煮海带", "vegetable", 15);
        Dish dishNine = new Dish("炒豆干", "vegetable", 90);

        return Arrays.asList(dishOne, dishTwo, dishThree, dishFour, dishFive,
                dishSix, dishSeven, dishEight, dishNine);
    }

    default void systemInfo(Object object){
        System.out.println(object.toString());
    }


    static void main(String[] args) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(12);
        FutureTask future = new FutureTask(new Cal());
        future.run();
        System.out.println(future.get());

        ThreadLocal threadLocal = new ThreadLocal();
        Run run = new Run();
        Random random = new Random();
        Thread thread = new Thread(()-> {

            threadLocal.set(random.nextInt(10));
            System.out.println(threadLocal.get());
            //run.run();
            //System.out.println("xxx");
        });
        IntStream.range(0,10).parallel().forEach(
               a ->{
                   threadLocal.set(a);
                   System.out.println(threadLocal.get());
               }
        );

//        for(int i=0;i<3;i++) {
//            thread.run();
//        }
        //System.out.println("xxxx");
    }

    class Cal implements Callable{


        public Integer add(AtomicInteger atomicInteger) throws Exception {
            return atomicInteger.addAndGet(11);
        }

        @Override
        public Object call() throws Exception {
            return "right";
        }
    }

    class Run implements Runnable{

        @Override
        public void run() {
            System.out.println("t1");
        }
    }


}
