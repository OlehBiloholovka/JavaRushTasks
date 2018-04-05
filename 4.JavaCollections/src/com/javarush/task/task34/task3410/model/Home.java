package com.javarush.task.task34.task3410.model;

import java.awt.*;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        this.width = 2;
        this.height = 2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawOval(x + FIELD_CELL_SIZE/2, y + FIELD_CELL_SIZE/2, width, height);
    }
}
