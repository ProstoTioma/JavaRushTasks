package com.javarush.task.task27.task2712.ad;

import java.util.Collections;

public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;


    public void revalidate() {
        if (hits < 1) throw new UnsupportedOperationException();
        hits--;
    }

    public int getHits() {
        return hits;
    }

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = (hits > 0) ? initialAmount / hits : 0;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public long getAmountPerOneSecond() {
        return amountPerOneDisplaying * 1000 / duration;
    }
}
