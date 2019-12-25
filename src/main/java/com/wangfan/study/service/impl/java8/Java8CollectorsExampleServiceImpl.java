package com.wangfan.study.service.impl.java8;

import com.wangfan.study.model.java8.Dish;
import com.wangfan.study.service.collector.ToListCollector;
import com.wangfan.study.service.java8.Java8Service;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class Java8CollectorsExampleServiceImpl implements Java8Service {


    /**
     * toList 把流中所有项目收集到一个 List
     */
    void useToList(){
        List<String> templeteList = getStringList();
        List answer = templeteList.stream().filter(
               Objects::nonNull
        ).collect(Collectors.toList());
    }

    /**
     * toSet 把流中所有项目收集到一个 Set，删除重复项
     */
    void useToSet(){
        List<String> templeteList = getStringList();
        Set answer = templeteList.stream().filter(
                Objects::nonNull
        ).collect(Collectors.toSet());
    }

    /**
     * toCollection 把流中所有项目收集到给定的供应源创建的集合
     */
    void useToCollection(){
        List<String> templeteList = getStringList();
        List answer = templeteList.stream().filter(
                Objects::nonNull
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * counting 计算流中元素的个数
     */
    void useCountTing(){
        List<String> templeteList = getStringList();
        long answer = templeteList.stream().filter(
                Objects::nonNull
        ).count();
        long answerTwo = templeteList.stream().filter(
                Objects::nonNull
        ).collect(Collectors.counting());
    }

    /**
     * summingInt 对流中项目的一个整数属性求和
     */
    void useSummingInt(){
        List<Integer> templeteList = getIntegerList();
        int answer = templeteList.stream()
                .collect(Collectors.summingInt(Integer::intValue));
        int answerTwo = templeteList.stream().mapToInt(Integer::intValue).sum();

        List<Dish> dishTempleteList = getDishList();
        int caloriesSum = dishTempleteList.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
    }

    /**
     * averagingInt 计算流中项目 Integer 属性的平均值
     *
     * summarizingInt 收集关于流中项目 Integer 属性的统计值，
     * 例如最大、最小、总和与平均值
     */
    void useAveragingInt(){
        List<Integer> templeteList = getIntegerList();
        double answer = templeteList.stream().
                collect(Collectors.averagingInt(Integer::intValue));

        IntSummaryStatistics menuStatistics = templeteList.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));
        this.systemInfo(menuStatistics.getAverage());
        this.systemInfo(menuStatistics.getCount());
        this.systemInfo(menuStatistics.getMax());
        this.systemInfo(menuStatistics.getMin());
        this.systemInfo(menuStatistics.getSum());
    }

    /**
     * joining 连接对流中每个项目调用 toString 方法所生成的字符串
     */
    void userJoining(){
        List<Dish> templeteList = getDishList();
        String answer = templeteList.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(","));
        this.systemInfo(answer);
    }

    /**
     * maxBy 一个包裹了流中按照给定比较器选出的最大元素的 Optional，
     * 或如果流为空则为 Optional.empty()
     *
     * minBy 一个包裹了流中按照给定比较器选出的最小元素的 Optional，
     * 或如果流为空则为 Optional.empty()
     */
    void userMaxAndMin(){
        List<Dish> templeteList = getDishList();
        Optional max = templeteList.stream().collect(Collectors
                .maxBy(Comparator.comparing(Dish::getCalories)));
        Optional maxTwo = templeteList.stream()
                .max(Comparator.comparing(Dish::getCalories));
        Optional min = templeteList.stream().collect(Collectors
                .minBy(Comparator.comparing(Dish::getCalories)));
        Optional minTwo = templeteList.stream()
                .min(Comparator.comparing(Dish::getCalories));
    }

    void useReducing(){
        List<Dish> templeteList = getDishList();
        templeteList.stream().filter(dish ->
                "meat".equals(dish.getType()))
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        templeteList.stream().filter(dish ->
                "meat".equals(dish.getType())).map(Dish::getCalories)
                .reduce(0, Integer::sum);
    }

    /**
     * 根据项目的一个属性的值对流中的项目作问组，并将属性值作 为结果 Map 的键
     */
    void useGroupBy(){
        List<Dish> templeteList = getDishList();
        long start = System.nanoTime();
        // 根据菜肴类型分组
        Map<String, List<Dish>> map = templeteList.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        this.systemInfo(retrievalTime);

        long startTwo = System.nanoTime();
        //  自定义构造器
//        Map<String, List<Dish>> mapTwo = templeteList.stream()
//                .collect(
//                        () -> new HashMap<String, List<Dish>>(16) {},
//                        (acc, dish) -> {
//                            String type = dish.getType();
//                            if(acc.containsKey(type)) {
//                                acc.get(type).add(dish);
//                            }else {
//                                List list = new ArrayList();
//                                list.add(dish);
//                                acc.put(type, list);
//                            }
//                        },
//                        (map1, map2) -> {
//                            map1.keySet().forEach(k ->{
//                                map1.get(k).addAll(map2.get(k));
//                            });
//                        });
        long retrievalTimeTwo = ((System.nanoTime() - startTwo) / 1_000_000);
        this.systemInfo(retrievalTimeTwo);

        long startThree = System.nanoTime();
        // 封装好的自定义构造器
        Map<String, List<Dish>> mapThress = templeteList.stream()
                // 并行规约
                //.parallel()
                .collect(new ToListCollector());
        long retrievalTimeThree = ((System.nanoTime() - startThree) / 1_000_000);
        this.systemInfo(retrievalTimeThree);

    }

    /**
     * partitioningBy 根据对流中每个项目应用谓词的结果来对项目进行分区
     */
    void usePartitioningBy(){
        List<Dish> templeteList = getDishList();
        // 根据卡路里是否大于100分组
        Map<Boolean, List<Dish>> map = templeteList.stream()
                .collect(Collectors.partitioningBy(
                        dish ->dish.getCalories() > 100));
        map.forEach(
                (k,v) ->  {
                    this.systemInfo(k);
                    this.systemInfo(v);
                }
        );
    }

    public static void main(String[] args) {
        Java8CollectorsExampleServiceImpl java8CollectorsExampleServiceImpl = new Java8CollectorsExampleServiceImpl();
        java8CollectorsExampleServiceImpl.useGroupBy();
    }
}
