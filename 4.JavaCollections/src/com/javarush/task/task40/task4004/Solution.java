package com.javarush.task.task40.task4004;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        //напишите тут ваш код
        Polygon polygonJava = new Polygon();
        polygon.forEach(p -> polygonJava.addPoint(p.x, p.y));

        return polygonJava.contains(point.x, point.y);

//        int count = 0;
//        for (int i = 0; i < polygon.size(); i++) {
//            Point point1 = polygon.get(i);
//            Point point2 = i + 1 < polygon.size() ? polygon.get(i + 1) : polygon.get(0);
//
//            if (isY(point, point1, point2) && isX(point, point1, point2)) count++;
//        }
//        return count % 2 != 0;
    }

//    private static boolean isY(Point point, Point point1, Point point2){
//        return point1.y > point2.y
//                ? point.y <= point1.y && point.y >= point2.y
//                : point.y <= point2.y && point.y >= point1.y;
//    }
//
//    private static double getEdgeX(Point point, Point point1, Point point2){
//        int lengthEdgeY = Math.abs(point1.y - point2.y);
//        int lengthEdgeX = Math.abs(point1.x - point2.x);
//        int lengthY = Math.abs(point1.y - point.y);
//        double proportion = lengthY / lengthEdgeY;
//        double lengthX = lengthEdgeX * proportion;
//        return point1.x + lengthX;
//
//    }
//
//    private static boolean isX(Point point, Point point1, Point point2) {
//        if (point1.y == point2.y) return point.y == point1.y;
//        return point.x <= getEdgeX(point, point1, point2);
//    }

}

