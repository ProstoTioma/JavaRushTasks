package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.*;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        new Client().run();
    }

    protected String getServerAddress() {
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Error!");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Error!");
                return;
            }
        }
        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            while (clientConnected) {
                String exit = ConsoleHelper.readString();
                if(shouldSendTextFromConsole()) sendTextMessage(exit);
                if (exit.equals("exit")) break;
            }
        }
        else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");

    }


    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " has joined the chat.");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " left the chat.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {

            while (true) {
               Message answer = connection.receive();
               if(answer.getType() == MessageType.NAME_REQUEST) {
                   String userName = getUserName();
                   connection.send(new Message(MessageType.USER_NAME, userName));
               } else if(answer.getType() == MessageType.NAME_ACCEPTED) {
                   notifyConnectionStatusChanged(true);
                   break;
               } else throw new IOException("Unexpected MessageType");
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message answer = connection.receive();
                if(answer.getType() == MessageType.TEXT) processIncomingMessage(answer.getData());
                else if(answer.getType() == MessageType.USER_ADDED) informAboutAddingNewUser(answer.getData());
                else if(answer.getType() == MessageType.USER_REMOVED) informAboutDeletingNewUser(answer.getData());
                else throw new IOException("Unexpected MessageType");
            }
        }

        @Override
        public void run() {
            String address = getServerAddress();
            int port = getServerPort();
            
            try {
               Socket socket = new Socket(address, port);
               connection = new Connection(socket);
               clientHandshake();
               clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
