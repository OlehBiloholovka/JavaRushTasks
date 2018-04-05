package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    protected int score;
    protected int maxTile;
    private Stack previousStates;
    private Stack previousScores;
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove(){
        if(!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[j][i].value == gameTiles[j-1][i]. value)return true;
            }
        }
        return false;
    }

    private void addTile(){
        if (canMove()) {
            int tileElement = (int) (getEmptyTiles().size() *  Math.random());
            getEmptyTiles().get(tileElement).value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty()){
                    result.add(gameTiles[i][j]);
                }
            }
        }
        return result;
    }

    protected void resetGameTiles(){
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        this.score = 0;
        this.maxTile = 2;
        for (int i = 0; i < this.gameTiles.length; i++) {
            for (int j = 0; j < this.gameTiles[i].length; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();

        previousStates = new Stack();
        previousScores = new Stack();
    }

    private boolean compressTiles(Tile[] tiles){
        boolean isChanged = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < tiles.length - 1; j++) {
                if (tiles[j].value == 0) {
                    if (tiles[j + 1].value == 0) {
                        continue;
                    }

                    tiles[j + 1].value ^= tiles[j].value ^= tiles[j + 1].value;
                    tiles[j].value ^= tiles[j + 1].value;


                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == 0) {
                break;
            }
            if (tiles[i].value == tiles[i + 1].value) {
                score += (tiles[i].value <<= 1);
                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
                tiles[i + 1].value = 0;
                i++;
                isChanged = true;
            }
        }
        compressTiles(tiles);
        return isChanged;
    }

    protected void left(){
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged){
            addTile();
        }
        isSaveNeeded = true;
    }

    protected void right(){
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    protected void up(){
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    protected void down(){
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    protected void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    protected void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::down));
        priorityQueue.peek().getMove().move();
    }

    private void rotate(){
        Tile tmp;
        for (int i = 0; i < FIELD_WIDTH / 2; i++) {
            for (int j = i; j < FIELD_WIDTH - 1 - i; j++) {
                tmp = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[FIELD_WIDTH-j-1][i];
                gameTiles[FIELD_WIDTH-j-1][i] = gameTiles[FIELD_WIDTH-i-1][FIELD_WIDTH-j-1];
                gameTiles[FIELD_WIDTH-i-1][FIELD_WIDTH-j-1] = gameTiles[j][FIELD_WIDTH-i-1];
                gameTiles[j][FIELD_WIDTH-i-1] = tmp;
            }
        }
    }

    private void saveState(Tile[][] gameTiles) {
        Tile[][] prevTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
//        int prevScore = score;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                prevTiles[i][j] = new Tile(gameTiles[i][j].value);
            }
        }

        previousStates.push(prevTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }

    }

    public boolean hasBoardChanged(){
        int weight = Arrays.stream(gameTiles).flatMap(Arrays::stream).mapToInt(tile -> tile.value).sum();
        int previousWeight = Arrays.stream((Tile[][])previousStates.peek()).flatMap(Arrays::stream).mapToInt(tile -> tile.value).sum();
        return weight != previousWeight;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        } else {
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();
        return moveEfficiency;
    }
}
