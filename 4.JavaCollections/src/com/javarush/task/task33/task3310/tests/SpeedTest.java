package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.Solution;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {


    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        long time = new Date().getTime();
        ids.addAll(Solution.getIds(shortener, strings));
        return new Date().getTime() - time;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        long time = new Date().getTime();
        strings.addAll(Solution.getStrings(shortener, ids));
        return new Date().getTime() - time;
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids = new HashSet<>();
        Set<String> strings = new HashSet<>();

        long timeForGettingIds1 = getTimeForGettingIds(shortener1, origStrings, ids);
        long timeForGettingIds2 = getTimeForGettingIds(shortener2, origStrings, ids);

        Assert.assertTrue(timeForGettingIds1 > timeForGettingIds2);

        long timeForGettingStrings1 = getTimeForGettingStrings(shortener1, ids, strings);
        long timeForGettingStrings2 = getTimeForGettingStrings(shortener2, ids, strings);

        Assert.assertEquals(timeForGettingStrings1, timeForGettingStrings2, 30);


    }
}
