package com.javarush.task.task28.task2803;

import java.util.concurrent.ThreadLocalRandom;

/* 
ThreadLocalRandom
*/
public class Solution {
    public static int getRandomIntegerBetweenNumbers(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }

    public static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static long getRandomLongBetween0AndN(long n) {
        return ThreadLocalRandom.current().nextLong(0, n + 1);
    }

    public static void main(String[] args) {
        System.out.println(getRandomIntegerBetweenNumbers(0, 100));
        System.out.println(getRandomDouble());
        System.out.println(getRandomLongBetween0AndN(1_000_000_000L));
    }
}
