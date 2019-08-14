package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread taskThread;
    @Override
    public void run() {
        while(!taskThread.isInterrupted()){
            try {
                System.out.println(taskThread.getName());
                Thread.sleep(100);
            } catch(InterruptedException e){
                taskThread.interrupt();
            }
        }

    }

    @Override
    public void start(String threadName) {
        taskThread = new Thread(this, threadName);
        taskThread.start();
    }

    @Override
    public void stop() {
        taskThread.interrupt();
    }
}
