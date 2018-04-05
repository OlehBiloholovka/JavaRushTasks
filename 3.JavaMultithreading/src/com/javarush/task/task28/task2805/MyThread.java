package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static AtomicInteger threadPriorityIncrement = new AtomicInteger(1);
    private AtomicInteger threadPriority = new AtomicInteger(1);

    private void setThreadPriority(){
        if (threadPriorityIncrement.get() > 10){
            threadPriorityIncrement.set(1);
        }
        threadPriority.set(threadPriorityIncrement.getAndIncrement());
        this.setPriority(threadPriority.get());
    }

    public MyThread() {
        setThreadPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        setThreadPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setThreadPriority();
    }

    public MyThread(String name) {
        super(name);
        setThreadPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setThreadPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setThreadPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setThreadPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setThreadPriority();
    }
}
