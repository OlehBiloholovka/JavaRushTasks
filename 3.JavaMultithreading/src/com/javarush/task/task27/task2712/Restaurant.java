package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Restaurant {
    private final static int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        List<Tablet> tablets = new ArrayList<>();
////
////        Tablet tablet9 = new Tablet(9);
////        tablets.add(tablet9);
////        Cook cookRobot = new Cook("Robot");
////        cookRobot.addObserver(new Waiter());
////        tablet9.addObserver(cookRobot);
////        tablet9.createOrder();
////
////        Tablet tablet1 = new Tablet(1);
////        tablets.add(tablet1);
////        Cook cookAmigo = new Cook("Amigo");
////        cookAmigo.addObserver(new Waiter());
////        tablet1.addObserver(cookAmigo);
////        tablet1.createOrder();
////
////        Tablet tablet2 = new Tablet(2);
////        tablets.add(tablet2);
//////        Cook cookRobot = new Cook("Robot");
//////        cookRobot.addObserver(new Waiter());
////        tablet2.addObserver(cookRobot);
////        tablet2.createOrder();
////
////        Tablet tablet10 = new Tablet(10);
////        tablets.add(tablet10);
//////        Cook cookAmigo = new Cook("Amigo");
//////        cookAmigo.addObserver(new Waiter());
////        tablet10.addObserver(cookAmigo);
////        tablet10.createOrder();
////
////
////        Tablet tablet3 = new Tablet(3);
////        tablets.add(tablet3);
//////        Cook cookRobot = new Cook("Robot");
//////        cookRobot.addObserver(new Waiter());
////        tablet3.addObserver(cookRobot);
////        tablet3.createOrder();
////
////        Tablet tablet5 = new Tablet(5);
////        tablets.add(tablet5);
//////        Cook cookAmigo = new Cook("Amigo");
//////        cookAmigo.addObserver(new Waiter());
////        tablet5.addObserver(cookAmigo);
////        tablet5.createOrder();



        Waiter waiter = new Waiter();
        Cook cookAmigo = new Cook("Amigo");
        cookAmigo.setQueue(orderQueue);
        Cook cookRobot = new Cook("Robot");
        cookRobot.setQueue(orderQueue);
//        StatisticManager.getInstance().register(cookAmigo);
//        StatisticManager.getInstance().register(cookRobot);

        Thread thread1 = new Thread(cookAmigo);
        thread1.setDaemon(true);
        thread1.start();

        Thread thread2 = new Thread(cookRobot);
        thread2.setDaemon(true);
        thread2.start();


        cookAmigo.addObserver(waiter);
        cookRobot.addObserver(waiter);

//        OrderManagerRemoved orderManager = new OrderManagerRemoved();

        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
//            tablet.addObserver(orderManager);
//            tablet.addObserver(orderManager);
//            tablet.addObserver(i % 2 == 0 ? cookAmigo : cookRobot);
            tablets.add(tablet);
        }

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        6. В методе main создай и запусти трэды на основании тасок Cook.

//        new Thread(cookAmigo).start();
//        new Thread(cookRobot).start();



                Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
//            Thread.interrupt();
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();


//        AdvertisementStorage.getInstance().list().forEach(advertisement -> {
//            System.out.println(String.format("%s - AmountPerOneDisplaying:%d, Duration:%d, Hits:%d"
//                    , advertisement.getName()
//                    , advertisement.getAmountPerOneDisplaying()
//                    , advertisement.getDuration()
//                    , advertisement.getHits()));
//        });



    }
}
