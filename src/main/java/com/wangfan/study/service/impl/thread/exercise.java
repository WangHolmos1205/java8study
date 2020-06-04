package com.wangfan.study.service.impl.thread;

import com.fasterxml.jackson.databind.util.LinkedNode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.*;

public class exercise {

    /**
     * String反向输出
     */
    static public void stringSystem(){
        List<Model> list = new ArrayList<>();
        String message ="abcdefghijklmn";
        for(int i=0;i< message.length();i++){
            String now = message.substring(i,i+1);
            Model model = new Model(i, now);
            list.add(model);
            //System.out.println(now);
        }
        list.stream().sorted(Comparator.comparing(Model::getSize).reversed()).forEach(
                model ->{System.out.println(model.getMessage());}
        );

        StringBuffer stringBuffer = new StringBuffer(message);
        stringBuffer.reverse();
        System.out.println(stringBuffer.toString());

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("234", "");
        concurrentHashMap.get("");
    }

    /**
     * 找到一个字符串里面最长的回文串
     */
    static private void findStringModel(String xxx){
        int bigLength = 0;
        int firstSize = 0;
        System.out.println(xxx.length());
        char[] aa =xxx.toCharArray();
        for(int i=0; i< xxx.length(); i++){
            int m = i;
            int length = 1;
            if(i-bigLength>=0
                    && i+1+bigLength < xxx.length()
                    && aa[i-bigLength] == aa[i+1 + bigLength]) {
                while (m >= 0
                        && i + length < xxx.length()
                        && aa[m] == aa[i + length]) {
                    --m;
                    ++length;
                }
                ++m;
                --length;
                if (length > bigLength) {
                    bigLength = length;
                    firstSize = m;
                    i = m+2*bigLength;
                }
            }
        }
        System.out.println("bigLength" + bigLength);
        System.out.println("firstSize" + firstSize);
        System.out.println(xxx.substring(firstSize, firstSize + 2* bigLength));
    }

    /**
     * 判断链表是否循环
     */
    static private void linkFind(){
        ListNode listNode = new ListNode(1);
        for(int i=2;i<5; i++){
            listNode.add(i);
        }
        listNode.createCycle(listNode);
        Boolean flag = Boolean.FALSE;

        ListNode faster = listNode.next;
        ListNode slow = listNode.next;
        while (faster.next != null && faster.next.next != null){
            slow = slow.next;
            faster = faster.next.next;
            if(faster.val == slow.val){
                flag = true;
                break;
            }
        }
        if(flag){

        }
        System.out.println(flag);
    }

    /**
     * 单链表倒叙
     */
    static private void linkReverse(){
        LinkedNode linkedNode = new LinkedNode(1, null);
        LinkedNode nextLink = linkedNode;
        for(int i=2;i<10;i++) {
            while (nextLink.next() != null){
                nextLink = nextLink.next();
            }
                nextLink.linkNext(new LinkedNode(i, null));

            }
        System.out.println(linkedNode.next());
        LinkedNode linkedNodeByReverse = reverse(linkedNode);
        System.out.println(linkedNodeByReverse);
    }


    /**
     *
     */
    static private LinkedNode reverse(LinkedNode linkedNode){
        if(linkedNode ==null || linkedNode.next() == null ){
            return linkedNode;
        }
        LinkedNode hope = reverse(linkedNode.next());
        if(LinkedNode.contains(hope, linkedNode.value())){
            return hope;
        }
        LinkedNode nextHope = hope;
        while (nextHope.next() != null) {
            nextHope = nextHope.next();
        }
        nextHope.linkNext(new LinkedNode(linkedNode.value(), null));

        return hope;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
        public void add(int newval){
            ListNode newNode = new ListNode(newval);
            if(this.next == null){
                this.next = newNode;
            }else {
                this.next.add(newval);
            }
        }

        public void createCycle(ListNode listNode){
            if(this.next == null) {
                this.next = listNode;
            }else {
                this.next.createCycle(listNode);
            }
        }
    }

    /**
     * 反转一个句子中的单词
     * @param words
     */
    private static void reverseWord(String words){
        StringBuffer stringBuffer = new StringBuffer();
        String[] strings = words.split(" ");
        for(int i=strings.length-1; i>0;i--){
            stringBuffer.append(strings[i]);
            stringBuffer.append(" ");
        }
        String reverseWords = stringBuffer.toString();
        System.out.println(reverseWords);
    }

    /**
     * int数组变更为左边奇数，右边偶数，不用额外空间
     */
    private static void changeIntWord(int[] intWords){
        int start = 0;
        int end = intWords.length-1;
        // 当计算左右计算完毕后，结束计算
        while (start < end){
            if(!isOddNumber(intWords[start])){
                if(isOddNumber(intWords[end])){
                    int change = intWords[start];
                    intWords[start] = intWords[end];
                    intWords[end] = change;
                }
                end--;
            }
            start++;
        }
        Arrays.stream(intWords).forEach(
                System.out::println
        );
    }
    private static Boolean isOddNumber(int number){
        return number%2 !=0;
    }

    @Getter
    @Setter
    static class Model{
        int size;
        String message;
        Model(int size, String message){
            this.size = size;
            this.message = message;
        }
    }

    public static void main(String[] args) {
        //stringSystem();
        String xxx = "sdfsdvfsfdssdfsgbasdssasdsdadasfddgcddsavcasddsacv";
        String aaa = "asddsa";
        String zz = "vcasddsacv";
        //findStringModel(xxx);

        //linkFind();
        String words = "a b c d f e ef es es asd";
        //reverseWord(words);
        int[] intWords = {1,2,3,4,5,6,7,8,9,10,11,12,21,23,25,27,29};
        //changeIntWord(intWords);
        linkReverse();
    }
}
