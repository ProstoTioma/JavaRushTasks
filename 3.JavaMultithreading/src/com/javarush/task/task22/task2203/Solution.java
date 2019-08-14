package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        int match = string.length() - string.replaceAll("\t","").length();
        if(match < 2 ) throw new TooShortStringException();
        String[] parse = string.split("\t");
        if(parse.length <= 1) throw new TooShortStringException();
        return parse[1];
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
