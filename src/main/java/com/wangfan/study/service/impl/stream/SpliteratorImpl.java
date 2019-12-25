package com.wangfan.study.service.impl.stream;

import com.wangfan.study.service.java8.Java8Service;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class SpliteratorImpl implements Java8Service {

    /**
     * 映射到数值流
     * @return
     */
    int useMapToInt(){
        List<Integer> templeteList = getIntegerList();
        return templeteList.stream().mapToInt(
                Integer::intValue
        ).sum();
    }

    /**
     * 转换回对象流
     * @return
     */
    Stream<Integer> useBoxed(){
        List<Integer> templeteList = getIntegerList();
        return templeteList.stream().mapToInt(
                Integer::intValue
        ).boxed();
    }

    void getPythagoreanTriples (){

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int)Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.forEach( t->
                System.out.println(t[0] + "," + t[1] +
                        "," +t[2] )
        );
    }


    public static void main(String[] args) {
        SpliteratorImpl spliterator = new SpliteratorImpl();
        spliterator.getPythagoreanTriples();
    }

}