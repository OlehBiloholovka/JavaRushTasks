package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
//        List<Class> classes = new ArrayList<>();

        Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
        for (Class<?> aClass : declaredClasses) {

            boolean hasModifier = aClass.getModifiers() == Modifier.PRIVATE + Modifier.STATIC;
            boolean isEqual = haveInterface(aClass);
            boolean aBool = isEqual && hasModifier;

            if (aBool) {
                try {
                    Constructor constructor = aClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List list = (List) constructor.newInstance();
                    list.get(0);

                } catch (IndexOutOfBoundsException e) {
                    return aClass;
                } catch (Exception e) {
                }

            }
        }
//        classes.forEach(aClass -> System.out.println(aClass.toString()));


        return null;
    }

    private static boolean haveInterface(Class<?> aClass){
        boolean isEqual = false;
        Class<?>[] interfaces = aClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if (anInterface.equals(List.class)){
                isEqual =true;
                break;
            } else {
                Class<?> superclass = aClass.getSuperclass();
                if (!superclass.getSimpleName().equals("Object")){
                    return haveInterface(superclass);
                }
            }
        }
        return isEqual;
    }
}
