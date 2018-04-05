package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University{
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;



    public University(String name, int age) {
//        super(name, age, 0);
        this.name = name;
        this.age = age;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        return students.stream().filter(student -> student.getAverageGrade() == averageGrade).findFirst().orElse(null);
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        double maxAverageGrade = Double.MIN_VALUE;
        Student result = null;
        for (Student student : students) {
            if (student.getAverageGrade() >= maxAverageGrade) {
                result = student;
                maxAverageGrade = result.getAverageGrade();
            }
        }

        return result;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        double minAverageGrade = Double.MAX_VALUE;
        Student result = null;
        for (Student student : students) {
            if (student.getAverageGrade() <= minAverageGrade) {
                result = student;
                minAverageGrade = result.getAverageGrade();
            }
        }

        return result;
    }

    public void expel(Student student){
        if (student != null && students.contains(student)){
            students.remove(student);
        }
    }
}