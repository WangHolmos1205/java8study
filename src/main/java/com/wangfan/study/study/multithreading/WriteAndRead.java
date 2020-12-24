package com.wangfan.study.study;

import java.util.ArrayList;
import java.util.List;

public class WriteAndRead {
    public static void main(String[] args) {
        String a= "a|sdfwe|342|2342";
        String[] aaa = a.split("|");
        for(String sa:aaa){
            System.out.println(sa);
        }
    }


}
