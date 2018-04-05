package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.io.File;
import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get(this.getClass().getResource(".." + File.separator +"res" + File.separator + "levels.txt").getPath()));

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);

    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel++;
        restart();
    }

    public void move(Direction direction){
        Player player = gameObjects.getPlayer();
        if (!checkWallCollision(player, direction) && !checkBoxCollisionAndMoveIfAvaliable(direction)) {
            moveMovable(player, direction);
            checkCompletion();
        }


    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction){
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                if (checkWallCollision(box, direction)) {
                    return true;
                } else {
                    for (Box b : gameObjects.getBoxes()) {
                        if (box.isCollision(b, direction)){
                            return true;
                        }
                    }
                    moveMovable(box, direction);
                }
            }
        }
        return false;
    }

    private void moveMovable(Movable movable, Direction direction){
        switch (direction){
            case UP:
                movable.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                movable.move(0, FIELD_CELL_SIZE);
                break;
            case LEFT:
                movable.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                movable.move(FIELD_CELL_SIZE, 0);
                break;
        }
    }

    public void checkCompletion(){
        boolean isAtHome = true;
        for (Box box : gameObjects.getBoxes()) {
            boolean thisAtHome = false;
            for (Home home : gameObjects.getHomes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    thisAtHome = true;
                    break;
                }
            }
            if (!thisAtHome) {
                isAtHome = false;
                break;
            }
        }
        if (isAtHome) eventListener.levelCompleted(currentLevel);
    }
}
