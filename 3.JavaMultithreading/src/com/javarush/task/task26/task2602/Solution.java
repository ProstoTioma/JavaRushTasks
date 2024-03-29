package com.javarush.task.task26.task2602;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/* 
Был бы ум - будет и успех
*/
public class Solution {
    public static void main(String[] args) {
        Set<Soldier> soldiers = new TreeSet<>();
        soldiers.add(new Soldier("Ivanov", 170));
        soldiers.add(new Soldier("Petrov", 180));
        soldiers.add(new Soldier("Sidorov", 175));

        ArrayList<Soldier> list = new ArrayList<>(soldiers);
        Collections.sort(list);

        for (Soldier soldier : soldiers) {
            System.out.println(soldier.name);
        }
    }

    public static class Soldier implements Comparable<Soldier> {
        private String name;
        private int height;

        public Soldier(String name, int height) {
            this.name = name;
            this.height = height;
        }

        @Override
        public int compareTo(Soldier o) {
            return Integer.compare(o.height, this.height);
        }
    }
}
