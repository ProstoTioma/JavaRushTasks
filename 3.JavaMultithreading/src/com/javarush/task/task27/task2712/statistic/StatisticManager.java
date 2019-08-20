package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager {
    private final static StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private static class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        StatisticStorage() {
            EventType[] arrayType = EventType.values();

            for (EventType eventType: arrayType) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }
        private void put(EventDataRow data) {

        }
    }
}
