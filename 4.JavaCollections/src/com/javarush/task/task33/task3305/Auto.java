package com.javarush.task.task33.task3305;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
@JsonSubTypes({
        @Type(value = Car.class, name = "car"),
        @Type(value = Motorbike.class, name = "motorbike"),
        @Type(value = RaceBike.class, name = "race-bike")
})
public abstract class Auto {
    protected String name;
    protected String owner;
    protected int age;
}