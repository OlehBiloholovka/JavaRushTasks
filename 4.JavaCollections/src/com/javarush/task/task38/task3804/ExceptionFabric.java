package com.javarush.task.task38.task3804;

public class ExceptionFabric {
    public static Throwable make(Enum anEnum){
        String message = null;
        if (anEnum != null) {
            message = anEnum.name().toLowerCase().replace("_", " ");
            message = anEnum.name().substring(0,1).concat(message.substring(1));
        }


        if (anEnum != null && anEnum.getClass().equals(ExceptionApplicationMessage.class)){
            return new Exception(message);
        }else if (anEnum != null && anEnum.getClass().equals(ExceptionDBMessage.class)) {
            return new RuntimeException(message);
        }else if (anEnum != null && anEnum.getClass().equals(ExceptionUserMessage.class)) {
            return new Error(message);
        }else {
            return new IllegalArgumentException();
        }
    }
}
