package com.javarush.task.task22.task2208;

import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder where = new StringBuilder();
        for (String key:params.keySet()) {
            if (params.get(key) != null) {
                where.append(key + " = '"+ params.get(key)+"' and ");
            }
        }
        if (where.length()>0)  return where.substring(0, where.length() -5);
        return "";
    }
}
