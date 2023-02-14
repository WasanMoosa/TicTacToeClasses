package org.tictactoeclass;

import java.util.Random;

public class Bot extends Player {
    /**
     * Constructs a new Player object.
     *
     * @param name   The name of the Bot.
     * @param symbol The symbol that represents the Bot's moves on the board.
     */
    public Bot(String name, char symbol) {
        super(name, symbol);
    }

    public Bot makemove() {
        Random random = new Random();

        // Generate random numbers from 1 - 3
        randomRow = random.nextInt(3);
        randomColumn = random.nextInt(3);
        int[] movePlace = {randomRow, randomColumn};
        return this;
    }

    public int getRow() {
        return randomRow;
    }
    public int getColumn() {
        return randomColumn;
    }

    private int randomRow;
    private int randomColumn;
}
