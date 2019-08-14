package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            //наш метод(t, e.getCause());
            uncaughtException(t, e.getCause());
            System.out.println(e);
            //вывод e
        }catch (NullPointerException ex){
            //тут стопаем нашу нить
            t.interrupt();
        }
    }

    public static void main(String[] args) throws Exception {
        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
    }

}
