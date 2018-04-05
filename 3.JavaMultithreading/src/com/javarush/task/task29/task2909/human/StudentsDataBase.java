package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class StudentsDataBase {
    public static List<Student> students = new ArrayList<>();

    public static void addInfoAboutStudent(Student student) {
        students.add(student);
        printInfoAboutStudent(student);

    }

    public static void printInfoAboutStudent(Student student) {
        System.out.printf("Имя: %s Возраст: %d", student.getName(), student.getAge());
    }

    public static void removeStudent(int index) {
        if(index >= 0 && index  < students.size()) {
            students.remove(index);
        }
    }

    public static void findDimaOrSasha() {
        for (Student student : students) {
            if (student.getName().equals("Dima")){
                System.out.println("Студент Dima есть в базе.");
                break;
            }
            if (student.getName().equals("Sasha")){
                System.out.println("Студент Sasha есть в базе.");
                break;
            }
        }


//        boolean found = false;
//        for (int i = 0; i < students.size(); i++) {
//            if (!found) {
//                if (students.get(i).getName().equals("Dima")) {
//                    System.out.println("Студент Dima есть в базе.");
//                    found = true;
//                }
//
//                if (students.get(i).getName().equals("Sasha")) {
//                    System.out.println("Студент Sasha есть в базе.");
//                    found = true;
//                }
//            }
//        }
    }
}