package com.wangfan.study.service.impl.java8;

import com.wangfan.study.model.java8.Trader;
import com.wangfan.study.model.java8.Transaction;
import com.wangfan.study.service.java8.Java8Service;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class Java8ServiceImpl implements Java8Service {

    /**
     * java8常用练习范例
     * @param args
     */
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //(1) 找出2011年发生的所有交易，并按交易额排序(从低到高)。
        transactions
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(
                        i -> System.out.println("1:" + i.getValue())
                );
        //(2) 交易员都在哪些不同的城市工作过?
        transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(
                        i -> System.out.println("2:" +i)
                );
        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(i -> "Cambridge" == i.getCity())
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(
                        i -> System.out.println("3:" + i.getName())
                );
        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .forEach(
                        i -> System.out.println("4:" + i)
                );
        //(5) 有没有交易员是在米兰工作的?
        Boolean five = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(i -> "Milan" == i.getCity());
        System.out.println("5:" + five);
        //(6) 打印生活在剑桥的交易员的所有交易额。
        transactions.stream()
                .filter(
                        i -> "Cambridge" == i.getTrader().getCity())
                .forEach(i ->
                        System.out.println("6:" + i.getValue())
                );

        //(7) 所有交易中，最高的交易额是多少?
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(
                        i -> System.out.println("7:" + i)
                );
        //(8) 找到交易额最小的交易。
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(
                        i -> System.out.println("8:" + i)
                );
        transactions.stream()
                .sorted(Comparator
                        .comparing(Transaction::getValue))
                .findFirst();
        System.out.println("success");
        //
        transactions.stream().map(Transaction::getTrader).forEach( i-> {
                    System.out.println(i.getName());
                }
        );

    }
}