package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends UniversityPerson {
    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.averageGrade = averageGrade;
    }

    public void live() {
        learn();
    }

    public void learn() {
    }
    public int getCourse() {
        return course;
    }



    public void incAverageGrade(double delta) {
        double avrGr = getAverageGrade();
        setAverageGrade(avrGr += delta);
    }



    public void setCourse(int value) {
        course = value;
    }
    public void setAverageGrade(double value) {
        averageGrade = value;
    }

    @Override
    public String getPosition() {
        return "Студент";
    }

    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }

    public void setEndOfSession(Date date) {
        endOfSession = date;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}