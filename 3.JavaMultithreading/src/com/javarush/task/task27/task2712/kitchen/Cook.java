package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!arg.toString().equals(""))
            ConsoleHelper.writeMessage("Start cooking - " + arg + ", cooking time " + ((Order) arg).getTotalCookingTime() + "min");
        StatisticManager statisticManager = StatisticManager.getInstance();
        statisticManager.register(new CookedOrderEventDataRow(null, name, ((Order) arg).
                getTotalCookingTime() * 60, ((Order) arg).getDishes()));
        setChanged();
        notifyObservers(arg);
    }
}
