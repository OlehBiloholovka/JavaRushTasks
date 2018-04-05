package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;

    protected Size size;

//    public static final int FIRST = 1;
//    public static final int SECOND = 2;
//    public static final int THIRD = 3;
//    public static final int FOURTH = 4;
    private BloodGroup bloodGroup;

    private List<Human> children = new ArrayList<>();

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Human child){
        if (child != null) {
            children.add(child);
        }
    }

    public void removeChild(Human child){
        if (child != null && children.contains(child)) {
            children.remove(child);
        }
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    //    public void setBloodGroup(int code) {
//        switch (code) {
//            case 1:
//                bloodGroup = BloodGroup.first();
//                break;
//            case 2:
//                bloodGroup = BloodGroup.second();
//                break;
//            case 3:
//                bloodGroup = BloodGroup.third();
//                break;
//            case 4:
//                bloodGroup = BloodGroup.fourth();
//                break;
//        }
//    }
//
//    public BloodGroup getBloodGroup() {
//        return bloodGroup;
//    }

    public Human(String name, int age) {
//        this.isSoldier = isSoldier;
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void live() {
    }

    public int getId() {
        return id;
    }

    public void printSize() {
        System.out.println(size);
    }

    public String getPosition(){
        return  "Человек";
    }

    public void printData() {
        System.out.printf("%s: %s", getPosition(), name);
    }

    public class Size {
        public int height;
        public int weight;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("Рост: %d Вес: %d", height, weight);
        }
    }
}