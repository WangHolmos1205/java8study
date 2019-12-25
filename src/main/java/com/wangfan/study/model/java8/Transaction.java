package com.wangfan.study.model.java8;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
