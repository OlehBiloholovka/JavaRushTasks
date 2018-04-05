package com.javarush.task.task36.task3607;


import java.util.concurrent.DelayQueue;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
//        final Set<String> classNames = new HashSet<>();
//
//        final URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
//        final URL[] jarsPath = classLoader.getURLs();
//
//        for (final URL jarPath : jarsPath) {
//            if (jarPath.toString().endsWith("rt.jar")) {
//                try {
//                    final JarFile jarFile = new JarFile(jarPath.getPath().toString());
//                    final Enumeration<JarEntry> en = jarFile.entries();
//                    while (en.hasMoreElements()) {
//                        final String fileName = en.nextElement().getName();
//                        if (fileName.startsWith("java/util/concurrent") && fileName.endsWith(".class") && !fileName.contains("$")) {
//                            classNames.add(fileName);
//                        }
//                    }
//                } catch (final IOException ignored) {
//                }
//            }
//        }
//
//        for (String className : classNames) {
//            try {
//                final String fileName = className.substring(0, className.length() - 6).replace('/', '.');
//                final Class aClass = Class.forName(fileName);
//
//                if (Queue.class.isAssignableFrom(aClass) && aClass.getEnclosingClass() == null){
//
//                    if (Arrays.stream(aClass.getDeclaredMethods())
//                            .filter(method -> method.getName().contains("peekExpired"))
//                            .flatMap(method -> Arrays.stream(aClass.getDeclaredFields()))
//                            .anyMatch(field -> field.getType().equals(Thread.class))){
//                        return aClass;
//                    }
//                }
//
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
        return DelayQueue.class;
    }

}