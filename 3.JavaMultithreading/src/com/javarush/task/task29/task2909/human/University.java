package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    String name;
    int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }


    public Student getStudentWithAverageGrade(double value) {
        for(Student student : students) if(student.getAverageGrade() == value)
        return student;
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxGrade = 0;
        for(Student student : students) if(student.getAverageGrade() > maxGrade) maxGrade = student.getAverageGrade();

        for(Student student : students) if(student.getAverageGrade() == maxGrade) return student;
        return null;
    }

    public Student getStudentWithMinAverageGrade() {
        double minGrade = 100;
        for(Student student : students) if(student.getAverageGrade() < minGrade) minGrade = student.getAverageGrade();
        for(Student student : students) if(student.getAverageGrade() == minGrade) return student;
        return null;
    }
    public void expel(Student student) {
        students.remove(student);
    }
}