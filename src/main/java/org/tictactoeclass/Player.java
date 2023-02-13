package org.tictactoeclass;

import java.util.Scanner;

/**
 * Player class representing a player in a Tic-Tac-Toe game.
 *
 * @author wasan
 */
public class Player {
    private String name;
    private char symbol;

    /**
     * Constructs a new Player object.
     *
     * @param name   The name of the player.
     * @param symbol The symbol that represents the player's moves on the board.
     */
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the symbol that represents the player's moves on the board.
     *
     * @return The symbol that represents the player's moves on the board.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol that represents the player's moves on the board.
     *
     * @param symbol The symbol that represents the player's moves on the board.
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}

