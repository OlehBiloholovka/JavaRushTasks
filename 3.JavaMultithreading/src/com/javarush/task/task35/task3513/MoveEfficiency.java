package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency>{
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency that) {
        final int BETTER = 1;
        final int EQUAL = 0;
        final int WORSE = -1;

        if (this == that) return EQUAL;

        if (this.numberOfEmptyTiles > that.numberOfEmptyTiles) return BETTER;
        if (this.numberOfEmptyTiles < that.numberOfEmptyTiles) return WORSE;

        if (this.numberOfEmptyTiles == that.numberOfEmptyTiles) {
            if (this.score > that.score) return BETTER;
            if (this.score < that.score) return WORSE;
        }

        return EQUAL;
    }
}
