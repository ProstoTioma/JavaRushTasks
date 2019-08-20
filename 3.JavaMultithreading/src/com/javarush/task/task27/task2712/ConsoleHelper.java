package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        writeMessage("Введите блюдо: ");
        String nameOfDish;
        while (!(nameOfDish = readString()).equals("exit")) {
            boolean isFound = false;
            for (int i = 0; i < Dish.values().length; i++) {
                if (Dish.values()[i].name().equals(nameOfDish)) {
                    dishes.add(Dish.valueOf(nameOfDish));
                    writeMessage("Хорошо, дальше.");
                    isFound = true;
                }
                if(i == Dish.values().length - 1 && !isFound) writeMessage("Такого блюда нет!");
            }
        }
        return dishes;
    }

}
