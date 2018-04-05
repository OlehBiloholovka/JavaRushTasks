package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

//    public RandomOrderGeneratorTask() {
//        for (int i = 1; i <= 10; i++) {
//            tablets.add(new Tablet(i));
//        }
//    }


    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
//        int count = 0;
        while (!Thread.currentThread().isInterrupted()){
            Tablet tablet = tablets.get((int) (Math.random() * (tablets.size() - 1)));
            tablet.createTestOrder();
//            count++;
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
//            e.printStackTrace();
            }
        }

    }
}
