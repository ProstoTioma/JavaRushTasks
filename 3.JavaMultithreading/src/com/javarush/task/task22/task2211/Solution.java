package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1]);

        while (fis.available() != 0) {
            Charset windows1251 = Charset.forName("Windows-1251");
            Charset utf = Charset.forName("UTF-8");

            byte[] buffer = new byte[1000];
            fis.read(buffer);
            String s = new String(buffer, windows1251);
            buffer = s.getBytes(utf);
            fos.write(buffer);
        }
        fis.close();
        fos.close();
    }
}
