package com.javarush.task.task23.task2303;


/*
Запрети создание экземпляров класса
*/
public class Solution {

    public static abstract class Listener {
        public final void onMouseDown(int x, int y) {
            //do something on mouse down event
        }

        public final void onMouseUp(int x, int y) {
            //do something on mouse up event
        }


    }

    public static void main(String[] args) {

    }
}
