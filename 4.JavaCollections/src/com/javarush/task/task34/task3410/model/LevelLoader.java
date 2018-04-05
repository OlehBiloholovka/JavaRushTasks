package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level){
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        while (level > 60) {
            level = level % 60;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(levels.toFile()))) {
            int x, x0, y, y0;

            x = x0 = FIELD_CELL_SIZE / 2;
            y = y0 = FIELD_CELL_SIZE / 2;

            boolean thisLevel = false;
            boolean start = false;

            while (bufferedReader.ready()){
                String readLine = bufferedReader.readLine();
                if (readLine.equals(String.format("Maze: %d", level))) {
                    thisLevel = true;
                    continue;
                }
                if (thisLevel && !start && readLine.equals("")) {

                    start = true;
                    continue;

                }
                if (start){
                    if (readLine.equals("")) {
                        break;
                    } else {
                        char[] chars = readLine.toCharArray();
                        for (char aChar : chars) {
                            switch (aChar){
                                case 'X':
                                    walls.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxes.add(new Box(x, y));
                                    break;
                                case '.':
                                    homes.add(new Home(x, y));
                                    break;
                                case '&':
                                    boxes.add(new Box(x, y));
                                    homes.add(new Home(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                            }
                            x += FIELD_CELL_SIZE;
                        }
                        y += FIELD_CELL_SIZE;
                        x = x0;
                    }
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        walls.add(new Wall(2 * FIELD_CELL_SIZE/2, 8 * FIELD_CELL_SIZE/2));
//        walls.add(new Wall(2 * FIELD_CELL_SIZE/2, 6 * FIELD_CELL_SIZE/2));
//        walls.add(new Wall(3 * FIELD_CELL_SIZE/2, 4 * FIELD_CELL_SIZE/2));
//        walls.add(new Wall(4 * FIELD_CELL_SIZE/2, 2 * FIELD_CELL_SIZE/2));
//        walls.add(new Wall(8 * FIELD_CELL_SIZE/2, 2 * FIELD_CELL_SIZE/2));
//        walls.add(new Wall(6 * FIELD_CELL_SIZE/2, 10 * FIELD_CELL_SIZE/2));
//
//
//        boxes.add(new Box(6 * FIELD_CELL_SIZE/2, 2 * FIELD_CELL_SIZE/2));
//
//
//        homes.add(new Home(4 * FIELD_CELL_SIZE/2, 8 * FIELD_CELL_SIZE/2));
//
//
//        player = new Player(10 * FIELD_CELL_SIZE / 2, 6 * FIELD_CELL_SIZE / 2);

        return new GameObjects(walls, boxes, homes, player);
    }
}
