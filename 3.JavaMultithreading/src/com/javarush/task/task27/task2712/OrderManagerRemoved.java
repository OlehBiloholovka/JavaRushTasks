package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class OrderManagerRemoved {
//public class OrderManagerRemoved implements Observer {
//    private LinkedBlockingQueue<Order> orderQueue;
//
//    public OrderManagerRemoved() {
//        orderQueue = new LinkedBlockingQueue<>();
//        Thread thread = new Thread(() -> {
////            while (!Thread.currentThread().isInterrupted()) {
////                try {
////                    for (Cook cook : StatisticManager.getInstance().getCooks()) {
////                        if (!cook.isBusy() && !orderQueue.isEmpty()) {
////                            Order order = orderQueue.poll(10, TimeUnit.MILLISECONDS);
////                            new Thread(() -> cook.startCookingOrder(order)).start();
////                            break;
////                        }
////                    }
////                } catch (InterruptedException e) {
////    //                    e.printStackTrace();
////                }
////            }
//        });
//        thread.setDaemon(true);
//        thread.start();
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//        try {
//            orderQueue.put((Order) arg);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
