package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses = new ArrayList<>();
    public static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses){
        this.horses = horses;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move(){
        horses.forEach(Horse::move);
    }

    public void print(){
        horses.forEach(Horse::print);
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner(){

        Horse winner = null;
        double winnerDistance = Double.MIN_VALUE;

        for (Horse horse : horses) {
            if (horse.getDistance() > winnerDistance) {
                winner = horse;
                winnerDistance = winner.getDistance();
            }
        }

        return winner;
    }

    public void printWinner(){
        System.out.printf("Winner is %s!", getWinner().getName());
    }

    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("First", 3,0));
        horses.add(new Horse("Second", 3,0));
        horses.add(new Horse("Third", 3,0));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
