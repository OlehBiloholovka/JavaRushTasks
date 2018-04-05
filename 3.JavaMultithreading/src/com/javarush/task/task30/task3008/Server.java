package com.javarush.task.task30.task3008;

import com.javarush.task.task30.task3008.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        connectionMap.forEach((s, connection) -> {
            try {
                connection.send(message);
            } catch (IOException e) {
                e.printStackTrace();
                ConsoleHelper.writeMessage(String.format("Message wasn`t send to %s", s));
            }
        });
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            connection.send(new Message(MessageType.NAME_REQUEST));
            Message message = connection.receive();

            if (message.getType() == MessageType.USER_NAME && !message.getData().isEmpty() && !connectionMap.containsKey(message.getData())) {
                connectionMap.put(message.getData(), connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return message.getData();
            } else {
                return serverHandshake(connection);
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {

            for (Map.Entry<String, Connection> stringConnectionEntry : connectionMap.entrySet()) {
                if (!stringConnectionEntry.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, stringConnectionEntry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {

                    sendBroadcastMessage(new Message(MessageType.TEXT, String.format("%s: %s", userName, message.getData())));

                } else {
                    ConsoleHelper.writeMessage("Error.");
                }
            }
        }

        @Override
        public void run() {


            try (Connection connection = new Connection(socket)) {

                String userName = serverHandshake(connection);
                sendListOfUsers(connection, userName);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                serverMainLoop(connection, userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));

                ConsoleHelper.writeMessage("Connection to remote address is closed.");
            } catch (IOException e) {
                ConsoleHelper.writeMessage("An error occurred while exchanging data with a remote address.");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("An error occurred while exchanging data with a remote address.");
            }
        }
    }


    public static void main(String[] args) {

        ConsoleHelper.writeMessage("Input server port: ");
        int port = ConsoleHelper.readInt();
        try(ServerSocket serverSocket = new ServerSocket(port)) {

            while (true){
                Socket socket = serverSocket.accept();

                System.out.println("Server is running!");
                new Handler(socket).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
