package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread thread;
    State state = null;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
        setDaemon(true);
    }

    @Override
    public void run() {

        while (true){

            if (thread.getState() != State.TERMINATED){
                if (thread.getState() != state) {
                    System.out.println(state = thread.getState());
                }
            }else {
                break;
            }
        }
    }
}
