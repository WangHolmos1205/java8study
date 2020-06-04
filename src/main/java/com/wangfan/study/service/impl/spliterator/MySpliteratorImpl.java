package com.wangfan.study.service.impl.spliterator;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

@Service
public class MySpliteratorImpl implements Spliterator<List> {

    private final int number = 5;


    @Override
    public boolean tryAdvance(Consumer<? super List> action) {
        return false;
    }

    @Override
    public Spliterator<List> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    /**
     * ORDERED   元素有既定的顺序(例如List)，因此Spliterator在遍历和划分时也会遵循这一顺序
     * DISTINCT  对于任意一对遍历过的元素x和y，x.equals(y)返回false
     * SORTED    遍历的元素按照一个预定义的顺序排序
     * SIZED     该Spliterator由一个已知大小的源建立(例如Set)，因此estimatedSize()返回的是准确值
     * NONNULL   保证遍历的元素不会为null
     * IMMUTABL  Spliterator的数据源不能修改。这意味着在遍历时不能添加、删除或修改任何元素
     * CONCURRE  该Spliterator的数据源可以被其他线程同时修改而无需同步
     * SUBSIZED  该Spliterator和所有从它拆分出来的Spliterator都是SIZED
     * @return
     */
    @Override
    public int characteristics() {
        return 0;
    }
}