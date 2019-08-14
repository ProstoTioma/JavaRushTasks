package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader file1 = new FileReader(args[0]);
        FileWriter file2 = new FileWriter(args[1]);
//        FileReader file1 = new FileReader(new BufferedReader(new InputStreamReader(System.in)).readLine());
//        FileWriter file2 = new FileWriter(new BufferedReader(new InputStreamReader(System.in)).readLine());
        Pattern pattern = Pattern.compile("\\b\\S*\\d+\\S*\\b");
        StringBuilder bigS = new StringBuilder();
        while(file1.ready()) {
            bigS.append((char) file1.read());
        }
        Matcher matcher = pattern.matcher(bigS);
        while (matcher.find()) file2.write(matcher.group() + " ");
        file1.close();
        file2.close();
    }
}
