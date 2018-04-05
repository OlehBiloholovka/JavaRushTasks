package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
//3. В Producer и Consumer реализуй метод run так
// , чтобы вызов метода interrupt прерывал работу consumer и producer трэдов.

        try {
            for (int i = 1; i <= 9; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);
                this.queue.offer(new ShareItem("ShareItem-" + i, i));


                Thread.sleep(100);

                if (queue.hasWaitingConsumer()){
                    System.out.format("Consumer в ожидании!\n");
                }
            }
        } catch (InterruptedException e) {
//                e.printStackTrace();
        }
    }
}
