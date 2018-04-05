package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

//8. Сделай класс Cook - таском (Runnable).
// Перенеси логику из трэда внутри конструктора OrderManagerRemoved в метод run класса Cook.
public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;
    private boolean stopped = false;
    private boolean caught = false;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

//    @Override
//    public void update(Observable o, Object arg) {
//        Tablet tablet = (Tablet) o;
//        Order order = (Order) arg;
//        StatisticManager.getInstance().register(
//                new CookedOrderEventDataRow(
//                        tablet.toString(),
//                        this.name,
//                        order.getTotalCookingTime() * 60,
//                        order.getDishes()));
//        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %dmin", order, order.getTotalCookingTime()));
//        setChanged();
//        notifyObservers(order);
//    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public void startCookingOrder(Order order){
        busy = true;

//        System.out.println("ORDER: " + order);

//        Tablet tablet = (Tablet) o;
        Tablet tablet = order.getTablet();
//        Order order = (Order) arg;
//        order.

        StatisticManager.getInstance().register(
                new CookedOrderEventDataRow(
                        tablet.toString(),
                        this.name,
                        order.getTotalCookingTime() * 60,
                        order.getDishes()));
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %dmin", order, order.getTotalCookingTime()));
        try {
            Thread.sleep(10 * order.getTotalCookingTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(order);

        busy = false;
    }

    @Override
    public void run() {
        while (!stopped){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            }
            if (queue.peek()!=null){

                if (!this.isBusy()) {
                    try{
                        //final Cook cookFinal = cook;
                        Order order = queue.take();
                        if (order!=null){
                            //Thread tr = new Thread(()->
                            startCookingOrder(order);
                            //tr.start();
                        }
                    }
                    catch (InterruptedException e){caught = true;}
                }
            }
            if (caught&&queue.isEmpty()) stopped=true;
        }
        while (!Thread.currentThread().isInterrupted() && !queue.isEmpty()) {
            if (!this.isBusy()) {
                try {
                    Order order = queue.poll(10, TimeUnit.MILLISECONDS);
                    if (order != null) {
                        startCookingOrder(order);
                    }
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
//            try {
//                if (!this.isBusy() && !queue.isEmpty()) {
//                    Order order = queue.poll(10, TimeUnit.MILLISECONDS);
//                    if (order != null) {
//                        startCookingOrder(order);
////                        new Thread(() -> this.startCookingOrder(order)).start();
////                        break;
//                    }
//
//                }
//            } catch (InterruptedException e) {
//                //                    e.printStackTrace();
//            }
        }

//        Thread thread = new Thread(() -> {
//            while (!Thread.currentThread().isInterrupted()) {
//                try {
//                    if (!this.isBusy() && !queue.isEmpty()) {
//                        Order order = queue.poll(10, TimeUnit.MILLISECONDS);
//                        if (order != null) {
//                            new Thread(() -> this.startCookingOrder(order)).start();
//                            break;
//                        }
//                    }
//                } catch (InterruptedException e) {
//    //                    e.printStackTrace();
//                }
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();



    }
}
