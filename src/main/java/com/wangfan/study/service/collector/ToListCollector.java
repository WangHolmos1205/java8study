package com.wangfan.study.service.collector;

import com.wangfan.study.model.java8.Dish;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


/**
 * Collector接口的定义是: public interface Collector<T, A, R>
 *   其中T、A和R分别是流中元素的类型、用于累积部分结果的对象类型，以及collect操作最终结果的类型。
 *   这里应该收集Dish流，而累加器和结果类型则都是Map<String, List<Dish>>
 *   键是菜肴类型，值则分别是对应菜肴的List:
 */
public class ToListCollector implements Collector<Dish, Map<String, List<Dish>>, Map<String, List<Dish>>> {

    /**
     * 创建累加器
     * @return
     */
    @Override
    public Supplier<Map<String, List<Dish>>> supplier() {
        return HashMap::new;
    }

    /**
     * 添加结果到累加器逻辑
     * @return
     */
    @Override
    public BiConsumer<Map<String, List<Dish>>, Dish> accumulator() {
        return (Map<String, List<Dish>> acc, Dish dish) -> {
                String type = dish.getType();
                if(acc.containsKey(type)) {
                    acc.get(type).add(dish);
                }else {
                    List<Dish> list = new ArrayList<>();
                    list.add(dish);
                    acc.put(type, list);
                }
        };
    }

    /**
     * 并行器方法，并行规约使用
     * @return
     */
    @Override
    public BinaryOperator<Map<String, List<Dish>>> combiner() {
        return (Map<String, List<Dish>> map1,
                Map<String, List<Dish>> map2) -> {
            map1.keySet().forEach(k ->{
                map1.get(k).addAll(map2.get(k));
            });
            return map1;
        };
    }

    /**
     * 累加器最终转换
     * @return
     */
    @Override
    public Function<Map<String, List<Dish>>, Map<String, List<Dish>>> finisher() {
        return Function.identity();
    }

    /**
     * 设置流是否可以并行归约
     * UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
     *
     * CONCURRENT——accumulator函数可以从多个线程同时调用，
     * 且该收集器可以并行归约流。如果收集器没有标为UNORDERED，
     * 那它仅在用于无序数据源时才可以并行归约。
     *
     * IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，
     * 可以跳过。这种情况下，累加器对象将会直接用作归约过程的最终结果。
     * 这也意味着，将累加器A不加检查地转换为结果R是安全的。
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.UNORDERED ,
                Characteristics.CONCURRENT,
                Characteristics.IDENTITY_FINISH));
    }
}
