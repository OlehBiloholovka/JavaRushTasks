package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private static AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

//    3. В StatisticAdvertisementManager создай два (или один) метода (придумать самостоятельно)
// , которые из хранилища AdvertisementStorage достанут все необходимые
// данные - соответственно список активных и неактивных рекламных роликов.
//Активным роликом считается тот, у которого есть минимум один доступный показ.
//Неактивным роликом считается тот, у которого количество показов равно 0.

    public Set<String> getActiveVideoSet(){
        return advertisementStorage.list().stream()
                .filter(advertisement -> advertisement.getHits() > 0)
                .map(ad -> String.format("%s - %d", ad.getName(), ad.getHits()))
                .collect(Collectors.toCollection(() -> new TreeSet<String>(Comparator.comparing(String::toLowerCase))));
    }

    public Set<String> getArchivedVideoSet(){
        return advertisementStorage.list().stream()
                .filter(advertisement -> advertisement.getHits() == 0)
                .map(Advertisement::getName)
                .collect(Collectors.toCollection(() -> new TreeSet<String>(Comparator.comparing(String::toLowerCase))));
    }

}
