package com.wangfan.study.model.java8;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dish {

    /**
     * 名称
     */
    String name;

    /**
     * 类型 肉:"meat", 鱼:"fish", 菜:"vegetable"
     */
    String type;
    /**
     * 卡路里
     */
    int calories;

    public Dish(String name, String type, int calories){
        this.name = name;
        this.type = type;
        this.calories = calories;
    }
}
