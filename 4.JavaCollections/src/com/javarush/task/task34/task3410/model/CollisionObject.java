package com.javarush.task.task34.task3410.model;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public abstract class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        switch (direction){
            case UP:
                return this.x == gameObject.getX() && this.y - FIELD_CELL_SIZE == gameObject.getY();
            case DOWN:
                return this.x == gameObject.getX() && this.y + FIELD_CELL_SIZE == gameObject.getY();
            case LEFT:
                return this.x - FIELD_CELL_SIZE == gameObject.getX() && this.y == gameObject.getY();
            case RIGHT:
                return this.x + FIELD_CELL_SIZE == gameObject.getX() && this.y == gameObject.getY();
            default:
                return false;
        }
    }
}
