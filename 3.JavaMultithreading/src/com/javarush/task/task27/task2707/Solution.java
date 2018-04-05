package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here

        synchronized (o1){
            Thread thread1 = new Thread(() -> solution.someMethodWithSynchronizedBlocks(o1, o2));

            Thread thread2 = new Thread(() -> {
                synchronized (o2){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread1.start();
            thread2.start();
            Thread.sleep(50);

            return !thread2.getState().equals(Thread.State.BLOCKED);


        }




//        Thread thread1 = new Thread(() -> {
//            synchronized (o1){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread1.start();
////        Thread.sleep(100);
//
//        Thread thread2 = new Thread(() -> solution.someMethodWithSynchronizedBlocks(o1, o2));
//
////        thread1.start();
//        thread2.start();
//        thread2.sleep(100);
//
//        System.out.println(thread2.getState().equals(Thread.State.BLOCKED));
//        return thread2.getState().equals(Thread.State.BLOCKED);


//        Thread.sleep(100);

//        System.out.println("thread2 " + thread2.getState().equals(Thread.State.BLOCKED));
//        synchronized (o2){
//            return thread2.getState().equals(Thread.State.BLOCKED);
//        }
//        return !thread2.getState().equals(Thread.State.BLOCKED);

//        synchronized (o2){
//            System.out.println("thread2 " + !thread2.getState().equals(Thread.State.BLOCKED));
//        }


//        return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
