package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem(){

        File[] files = new File(packageName).listFiles();

        ClassLoaderFromPath classLoaderFromPath = new ClassLoaderFromPath();

        for (File file : files) {
            Class<?> aClass = classLoaderFromPath.load(file.toPath());
            if (aClass != null) hiddenClasses.add(aClass);
        }

    }

    public static class ClassLoaderFromPath extends ClassLoader {
        public Class<?> load(Path path) {
            try {
                if (path.getFileName().toString().lastIndexOf(".class") == -1)
                    return null;
                byte[] bytes = Files.readAllBytes(path);
                return defineClass(null, bytes, 0, bytes.length); //here main magic
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> hiddenClass : hiddenClasses) {
            if (hiddenClass.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor constructor = hiddenClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }
}

