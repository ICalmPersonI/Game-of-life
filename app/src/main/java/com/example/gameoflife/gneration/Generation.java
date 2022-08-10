package com.example.gameoflife.gneration;


import java.util.Random;

public class Generation {
    private final int height;
    private final int width;
    private Cell[][] newGen;
    private Cell[][] oldGen;

    public Generation(int seed, int height, int width) {
        this.height = height;
        this.width = width;
        this.newGen = new Cell[height][width];
        this.oldGen = firstGeneration(seed);
    }

    public void createNewGeneration() {
        this.newGen = new Cell[height][width];
        for (int i = 0; i < oldGen.length; i++) {
            for (int j = 0; j < oldGen[i].length; j++) {
                int neighbors = countNeighbors(i, j);
                if (oldGen[i][j] == Cell.ALIVE) {
                    newGen[i][j] = neighbors < 2 || neighbors > 3 ? Cell.DEAD : Cell.ALIVE;
                } else {
                    newGen[i][j] = neighbors == 3 ? Cell.ALIVE : Cell.DEAD;
                }
            }
        }
    }

    private Cell[][] firstGeneration(int seed) {
        Cell[][] arr = new Cell[height][width];
        Random random = new Random(seed);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                arr[i][j] = random.nextBoolean() ? Cell.ALIVE : Cell.DEAD;
            }
        }
        return arr;
    }

    private int countNeighbors(int i, int j) {
        int count = 0;
        int row = i - 1 > -1 ? i - 1 : oldGen.length - 1;
        if (oldGen[row][j - 1 > -1 ? j - 1 : oldGen[row].length - 1] == Cell.ALIVE) count++;
        if (oldGen[row][j + 1 < oldGen[row].length ? j + 1 : 0] == Cell.ALIVE) count++;
        if (oldGen[row][j] == Cell.ALIVE) count++;

        row = i + 1 < oldGen.length ? i + 1 : 0;
        if (oldGen[row][j - 1 > -1 ? j - 1 : oldGen[row].length - 1] == Cell.ALIVE) count++;
        if (oldGen[row][j + 1 < oldGen[row].length ? j + 1 : 0] == Cell.ALIVE) count++;
        if (oldGen[row][j] == Cell.ALIVE) count++;

        row = i;
        if (oldGen[i][j - 1 > -1 ? j - 1 : oldGen[row].length - 1] == Cell.ALIVE) count++;
        if (oldGen[i][j + 1 < oldGen[row].length ? j + 1 : 0] == Cell.ALIVE) count++;
        return count;
    }

    public Cell[][] getNewGen() {
        return newGen;
    }

    public Cell[][] getOldGen() {
        return oldGen;
    }

    public void setOldGen(Cell[][] oldGen) {
        this.oldGen = oldGen;
    }

    public void setNewGen(Cell[][] newGen) {
        this.newGen = newGen;
    }

}
