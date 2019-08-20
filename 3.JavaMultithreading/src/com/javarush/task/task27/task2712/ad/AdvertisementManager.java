package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementManager {

    private int timeSeconds;

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        List<Advertisement> newList = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0)
                newList.add(advertisement);
        }
        newList.sort((o1, o2) -> {
            int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
            if (result != 0)
                return -result;

            long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
            long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

            return Long.compare(oneSecondCost1, oneSecondCost2);
        });
        storage.list().clear();
        storage.list().addAll(newList);

        int timeLeft = timeSeconds;
        for (Advertisement advertisement : storage.list()) {
            if (timeLeft < advertisement.getDuration() || advertisement.getHits() <= 0) {
                continue;
            }
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

            timeLeft -= advertisement.getDuration();
            advertisement.revalidate();
        }

        if (timeLeft == timeSeconds) {
            throw new NoVideoAvailableException();
        }
    }
}