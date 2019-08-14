package com.javarush.task.task19.task1922;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("А");
        words.add("Б");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader file = new FileReader(reader.readLine());
        reader.close();
        StringBuilder bigS = new StringBuilder();

        while (file.ready()) {
            bigS.append((char) file.read());
        }
        file.close();
        String[] splitFile = bigS.toString().replaceAll("\r\n", "\n").split("\n");

        for(String s : splitFile) {
            int count = 0;
            String[] words2 = s.split(" ");
            for(String word : words2) {
                if(words.contains(word)) count++;
            }
            if(count == 2) System.out.println(s);
        }

    }
}
