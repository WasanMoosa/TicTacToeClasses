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

    /**
     * Generates a random move for the bot to make in a game.
     *
     * @return a Bot instance representing the move to make.
     */
    public Bot makemove() {
        // Create a new Random instance to generate random numbers
        Random random = new Random();

        // Generate random numbers from 0 to 2 to represent row and column indices
        randomRow = random.nextInt(3);
        randomColumn = random.nextInt(3);

        // Store the generated indices as an array
        int[] movePlace = {randomRow, randomColumn};

        // Return the current Bot instance to allow method chaining
        return this;
    }

    /**
     * Gets row
     *
     * @return row of the bot.
     */
    public int getRow() {
        return randomRow;
    }

    /**
     * Gets column
     *
     * @return column of the bot.
     */
    public int getColumn() {

        return randomColumn;
    }

    private int randomRow;
    private int randomColumn;
}
