package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);

            if (!message.isEmpty() && message.contains(": ")){
                String[] array = message.split(": ");
                String name = array[0];
                String text = array[1];

                ConcurrentMap<String, SimpleDateFormat> messageMap = new ConcurrentHashMap<>();
                messageMap.put("дата", new SimpleDateFormat("d.MM.YYYY"));
                messageMap.put("день", new SimpleDateFormat("d"));
                messageMap.put("месяц", new SimpleDateFormat("MMMM"));
                messageMap.put("год", new SimpleDateFormat("YYYY"));
                messageMap.put("время", new SimpleDateFormat("H:mm:ss"));
                messageMap.put("час", new SimpleDateFormat("H"));
                messageMap.put("минуты", new SimpleDateFormat("m"));
                messageMap.put("секунды", new SimpleDateFormat("s"));

                if (messageMap.containsKey(text)){
                    sendTextMessage(String.format("Информация для %s: %s", name, messageMap.get(text).format(Calendar.getInstance().getTime())));
                }

            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
