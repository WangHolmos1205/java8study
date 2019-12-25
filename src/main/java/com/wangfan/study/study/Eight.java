package com.wangfan.study.study;

import java.util.ArrayList;
import java.util.List;

public class Eight {

    static int number=0;

    public static List<Integer> getNoPrizePeople(List<Integer> allTeam, ArrayList<Integer> prizePeople, int m){

        if(prizePeople.size() > m){
            number++;
            return null;
        }
        for(int i=0;i< allTeam.size();i++){
            ArrayList<Integer> newResult = (ArrayList<Integer>) prizePeople.clone();
            newResult.add(allTeam.get(i));
            List restTeams = new ArrayList(allTeam.subList(i+1,allTeam.size()));
            getNoPrizePeople(restTeams, newResult, m);
        }
        return allTeam;
    }

    public static void main(String[] args) {
        List<Integer> all = new ArrayList<Integer>();
        for(int i=1;i<101;i++){
            all.add(i);
        }
        getNoPrizePeople(all, new ArrayList<>(), 1);
        System.out.println(number);
    }
}
