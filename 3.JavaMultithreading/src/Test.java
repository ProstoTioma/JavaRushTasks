import com.javarush.task.task32.task3209.Controller;
import com.javarush.task.task32.task3209.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException, IOException, NoSuchMethodException, InvocationTargetException {
//        int randomNumber = (int) (Math.random() * 100);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int countOfTry = 0;
//
//        while (countOfTry != 7) {
//            System.out.println("Угадывай:");
//            int number = Integer.parseInt(reader.readLine());
//            if (randomNumber == number) {
//                System.out.println(String.format("Ты угадал!!! Загаданное число: %d", randomNumber));
//                break;
//            } else if(number < randomNumber) {
//                System.out.println("Больше!");
//            } else System.out.println("Меньше!");
//            if(countOfTry == 6) System.out.println("Не угадал! Загаданное число было: " + randomNumber);
//            countOfTry++;
//        }
//        Class clss = LelaAndMashaPackage.Test.class;
//        LelaAndMashaPackage.Test test = new LelaAndMashaPackage.Test();
//        Method[] methods = clss.getDeclaredMethods();
//        for(Method method : methods) {
//            System.out.println(method.getName());
//            System.out.println(Modifier.toString(method.getModifiers()));
//            System.out.println(method.getReturnType());
//            method.setAccessible(true);
//            method.invoke(test);
//        }
//        Field[] fields = clss.getDeclaredFields();
//        for(Field field : fields) {
//            field.setAccessible(true);
//            System.out.println(field.getName() + " " +  field.get(test));
//            field.set(test, 5);
//            System.out.println(field.get(test));
//        }
//
//        Class clss2 = Math.class;
//        Math math = (Math) clss2.newInstance();
        long countOfNumbers = 1000;
        long randomNumber = (long) (Math.random() * countOfNumbers);
        final int countOfTry = 11;

        while (true) {
            long[] countOfTryAndSearchNumber = binarySearch(countOfNumbers, randomNumber, countOfTry);
            long searchNumber = countOfTryAndSearchNumber[1];
            long tryCount = countOfTryAndSearchNumber[0];
            if (randomNumber == searchNumber) {
                System.out.println(String.format("Ты угадал!!! Загаданное число: %d, количевство попыток: %d", randomNumber, tryCount));
                break;
            } else {
                System.out.println("Не угадал! Загаданное число было: " + randomNumber + "\nТвое предположение - " + searchNumber);
                break;
            }
        }
    }

    public static long[] binarySearch(long countOfNumbers, long searchNumber, int countOfTry) {
        long low = 0;
        long high = countOfNumbers;
        int tryCount = 1;
        long[] countOfTryAndSearchNumber = new long[2];
        while (low <= high && tryCount != countOfTry) {
            long mid = (low + high) / 2;
            if(mid == searchNumber) {
                countOfTryAndSearchNumber[0] = tryCount;
                countOfTryAndSearchNumber[1] = mid;
                return countOfTryAndSearchNumber;
            }
            else if(mid > searchNumber) {
                System.out.println("Меньше! Текущее число: " + mid);
                high = mid - 1;
            }
            else {
                System.out.println("Больше! Текущее число: " + mid);
                low = mid + 1;
            }
            if(++tryCount == countOfTry) {
                countOfTryAndSearchNumber[0] = tryCount;
                countOfTryAndSearchNumber[1] = mid;
            }
        }

        return countOfTryAndSearchNumber;
    }
}
