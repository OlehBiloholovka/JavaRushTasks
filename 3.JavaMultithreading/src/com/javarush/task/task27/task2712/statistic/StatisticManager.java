package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

//    private Set<Cook> cooks = new HashSet<>();
//    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YYYY");

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    //    7. Из класса StatisticManager удали сет поваров, его геттер и метод register(Cook cook).
//    public void register(Cook cook){
//        cooks.add(cook);
//    }

//    public Set<Cook> getCooks() {
//        return cooks;
//    }

    public Map<Date, Long> getAdvertisementProfit(){
        List<EventDataRow> eventDataRows = statisticStorage.get(EventType.SELECTED_VIDEOS);

        Map<Date, Long> resultMap = new HashMap<>();
        eventDataRows.forEach(eventDataRow -> {
            Date date = removeTime(eventDataRow.getDate());
            long amount = ((VideoSelectedEventDataRow) eventDataRow).getAmount();

            if (!resultMap.containsKey(date)){
                resultMap.put(date, amount);
            } else {
                long totalAmount = resultMap.get(date) + amount;
                resultMap.replace(date, totalAmount);
            }
        });

        return resultMap;
    }

    public Map<Date, Map<String, Integer>> getCookWorkloading() {
        List<EventDataRow> eventDataRows = statisticStorage.get(EventType.COOKED_ORDER);
        Map<Date, Map<String, Integer>> resultMap = new HashMap<>();
        eventDataRows.forEach(eventDataRow -> {
            Date date = removeTime(eventDataRow.getDate());
            String cookName = ((CookedOrderEventDataRow) eventDataRow).getCookName();
            int time = eventDataRow.getTime();
            if (!resultMap.containsKey(date)){
                resultMap.put(date, new TreeMap<>());
            }

            Map<String, Integer> cookMap = resultMap.get(date);
            if (!cookMap.containsKey(cookName)){
                cookMap.put(cookName, time);
            }else {
                int cookTotalTime = cookMap.get(cookName) + time;
                cookMap.replace(cookName, cookTotalTime);
            }
        });
        return resultMap;
    }

    private static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private static class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage;

        private StatisticStorage() {
            storage = new HashMap<>();
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> get(EventType eventType){ return storage.get(eventType);}
    }

}
