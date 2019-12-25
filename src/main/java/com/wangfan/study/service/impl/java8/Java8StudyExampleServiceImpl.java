package com.wangfan.study.service.impl.java8;

import com.wangfan.study.service.java8.Java8Service;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 流操作:无状态和有状态
 * 你已经看到了很多的流操作。乍一看流操作简直是灵丹妙药，而且只要在从集合生成流的 时候把Stream换成parallelStream就可以实现并行。
 * 当然，对于许多应用来说确实是这样，就像前面的那些例子。你可以把一张菜单变成流， 用filter选出某一类的菜肴，然后对得到的流做map来对卡路里求和，
 * 最后reduce得到菜单 的总热量。这个流计算甚至可以并行进行。
 * 但这些操作的特性并不相同。它们需要操作的内部 状态还是有些问题的。
 * 诸如map或filter等操作会从输入流中获取每一个元素，并在输出流中得到0或1个结果。
 * 这些操作一般都是无状态的:它们没有内部状态(假设用户提供的Lambda或方法引用没有内 部可变状态)。
 * 但诸如reduce、sum、max等操作需要内部状态来累积结果。在上面的情况下，内部状态 很小。
 * 在我们的例子里就是一个int或double。不管流中有多少元素要处理，内部状态都是
 * 有界的。 相反，诸如sort或distinct等操作一开始都和filter和map差不多——都是接受一个
 * 流，再生成一个流(中间操作)，但有一个关键的区别。从流中排序和删除重复项时都需要知 道先前的历史。
 * 例如，排序要求所有元素都放入缓冲区后才能给输出流加入一个项目，这一操 作的存储要求是无界的。
 * 要是流比较大或是无限的，就可能会有问题(把质数流倒序会做什么 呢?它应当返回最大的质数，但数学告诉我们它不存在)。我们把这些操作叫作有状态操作。
 *
 */

@Service
public class Java8StudyExampleServiceImpl implements Java8Service {


    /**
     * filter 中间类型 Redicate<T> T->boolean
     * 根据条件过滤数据
     */
    void useFilter(){
        List<String> filterList = getStringList();
        List answer = filterList.stream().filter(
                Objects::nonNull
        ).collect(Collectors.toList());
        answer.forEach(
                System.out::println
        );
    }

    /**
     * distinct 中间类型（有状态 无界）
     * 去重
     */
    void useDistinct(){
        List<String> templeteList = getStringList();
        List answer =templeteList.stream().distinct().collect(Collectors.toList());
        answer.forEach(
                System.out::println
        );
    }

    /**
     * skip 中间(有状态-有界) long
     * 跳过若干个元素
     */
    void useSkip(){
        List<String> templeteList = getStringList();
        List answer =templeteList.stream().skip(2).collect(Collectors.toList());
        answer.forEach(
                System.out::println
        );
    }

    /**
     * limit 中间(有状态有界)
     * 截取若干个元素
     */
    void useLimit(){
        List<String> templeteList = getStringList();
        List answer =templeteList.stream().limit(2).collect(Collectors.toList());
        answer.forEach(
                System.out::println
        );
    }

    /**
     * map 中间 Stream<R> Function<T, R> T->R
     * flatMap 中间 Stream<R> Function<T, Stream<R>> T->Stream<R>
     */
    void useMap(){
        List<String> templeteList = getStringList();
        // flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接 起来成为一个流。
        List answer =templeteList.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        answer.forEach(
                System.out::println
        );
    }

    /**
     * sorted 中间 Stream<T> Comparator<T> (T, T) -> int
     * 排序
     */
    void useSorted(){
        List<Integer> templeteList = getIntegerList();
        List answer = templeteList.stream()
                .sorted()
                .collect(Collectors.toList());
        answer.forEach(
                System.out::println
        );
    }

    /**
     * match 终端 boolean Predicate<T> T->boolean
     * 满足条件
     */
    void useMatch(){
        List<String> templeteList = getStringList();
        // 存在满足条件
        System.out.println(templeteList.stream()
                .anyMatch(String::isEmpty));
        // 全都满足条件
        System.out.println(templeteList.stream()
                .allMatch(String::isEmpty));
        // 全都不满足条件
        System.out.println(templeteList.stream()
                .noneMatch(String::isEmpty));
    }

    /**
     * findAny findFirst 终端 Optional<T>
     * findAny比findFist效率高，非排序场景使用
     */
    void useFindAny(){
        List<String> templeteList = getStringList();
        Optional<String> optionAny = templeteList.stream()
                .filter(String::isEmpty)
                .findAny();
        Optional<String> optionFirst = templeteList.stream()
                .filter(String::isEmpty)
                .findAny();
        this.systemInfo(optionAny.isPresent());
        this.systemInfo(optionFirst.isPresent());
    }

    /**
     * forEach 终端 Optional<T>
     * 循环
     */
    void useForEach(){
        List<String> templeteList = getStringList();
        templeteList.forEach(
                System.out::println
        );
    }

    /**
     * collect 终端 void Consumer<T> T-> void
     */
    void useCollect(){
        List<String> templeteList = getStringList();
        Map answer = templeteList.stream().map(
                word -> word.split("")
        ).flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                ));
        answer.forEach((k,v)->{
            System.out.println(k + ":" + v);
                });
    }

    /**
     * reduce 终端(有状态-有界) Optional<T> BinaryOperator<T> (T,T) -> T
     */
    void useReduce(){
        List<Integer> templeteList = getIntegerList();
        Integer answer = templeteList.stream().reduce(0,(a, b) ->{
            if(a < b && b < 5){
                return b;
            }else {
                return a;
            }
        });
        Optional otherAnswer = templeteList.stream().max(Integer::compare);
        System.out.println(answer);
        otherAnswer.ifPresent(
                System.out::println
        );
    }

    /**
     * count 终端 long
     */
    void useCount(){
        List<Integer> templeteList = getIntegerList();
        long number = templeteList.stream().count();
        System.out.println(number);

    }

    public static void main(String[] args) {
        Java8StudyExampleServiceImpl java8StudyExampleService = new Java8StudyExampleServiceImpl();
        java8StudyExampleService.useCount();
    }
}
