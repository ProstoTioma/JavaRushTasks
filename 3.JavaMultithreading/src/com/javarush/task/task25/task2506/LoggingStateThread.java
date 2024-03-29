package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread logThread;
    public LoggingStateThread(Thread thread) {
        logThread = thread;
        setDaemon(true);
    }

    @Override
    public void run() {
        State state, lastState = null;
        do {
            state = logThread.getState();
            if (state != lastState) {
                System.out.println(state);
                lastState = state;
            }
        } while (state != State.TERMINATED);
    }
}
