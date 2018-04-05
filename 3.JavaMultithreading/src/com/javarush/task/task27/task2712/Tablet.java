package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

//public class Tablet extends Observable {
public class Tablet{
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){
        Order order = null;
        try {
            order = new Order(this);
            processOrder(order);
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
//        catch (NoVideoAvailableException e) {
//            logger.log(Level.INFO, "No video is available for the order " + order);
//            return order;
//        }
    }

    public void createTestOrder(){
//        д) вместо создания объекта Order в методе
// reateTestOrder() класса Tablet, создавай объект класса TestOrder.
//Весь другой функционал метода createTestOrder оставь прежним

        try {
            processOrder(new TestOrder(this));
        }catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void processOrder(Order order){
        try {
            if (order != null && !order.isEmpty()) {
                ConsoleHelper.writeMessage(order.toString());
//                setChanged();
//                notifyObservers(order);
                queue.offer(order);
                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            }
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
