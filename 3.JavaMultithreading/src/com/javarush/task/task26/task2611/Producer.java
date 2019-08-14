package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
    private ConcurrentHashMap<String, String> map;
    private final AtomicInteger counter = new AtomicInteger();

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {

        try
        {
            int counter = 1;
            while (true) {
                if (map != null) {
                    map.put(String.valueOf(counter), "Some text for " + counter);
                    counter++;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e)
        {
            String str_msg = "[%s] thread was terminated";
            System.out.println(String.format(str_msg, Thread.currentThread().getName()));
        }
    }
    }
