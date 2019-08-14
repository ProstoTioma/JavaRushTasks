package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;
    String winner;
    public static void main(String[] args) {
        List<Horse> horseList = new ArrayList<>();
        Horse horse1 = new Horse("Тёма", 4, 0);
        Horse horse2 = new Horse("Мама", 4, 0);
        Horse horse3 = new Horse("Папа", 4, 0);
        horseList.add(horse1);
        horseList.add(horse2);
        horseList.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horseList);
        game = hippodrome;
        game.run();
        game.printWinner();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void move() {
        for(Horse horse : horses) horse.move();
    }
    public void run() {
        for(int i = 0; i < 50; i++) {
            move();
            print();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void print() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for(Horse horse : horses) horse.print();


    }
    public Horse getWinner() {
        double maxDistance = 0;
        for(Horse horse : horses) {
            if(horse.distance > maxDistance) maxDistance = horse.distance;
        }
        for(Horse horse : horses) {
            if(horse.distance == maxDistance) return horse;
        }
        return null;
    }
    public void printWinner() {
        Hippodrome hippodrome = new Hippodrome(horses);
        System.out.println("Winner is " + hippodrome.getWinner().name + "!");
    }
}
