package com.wangfan.study.service.java8;

import com.wangfan.study.model.java8.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Java8Service {


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
}
