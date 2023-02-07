package org.tictactoeclass;

import java.util.Scanner;

public class Symbol {

    private char symbol1;
    private String playerName;
    private char symbol2;

    private Scanner input = new Scanner(System.in);

    /**
     * Creates a new `Symbol` object with two symbols.
     *
     * @param symbol1 The first symbol.
     * @param symbol2 The second symbol.
     */
    public Symbol(char symbol1, char symbol2) {
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
    }

    /**
     * Creates a new `Symbol` object with a symbol and a player name.
     *
     * @param symbol1 The symbol.
     * @param playerName The name of the player who is choosing the symbol.
     */
    public Symbol(char symbol1, String playerName) {
        this.symbol1 = symbol1;
        this.playerName = playerName;
    }

    /**
     * Gets a non-numeric symbol from the player.
     *
     * @return The chosen symbol.
     */
    public char SymbolNotDigit() {
        while (Character.isDigit(symbol1)) {
            System.out.print(playerName + ", choose your symbol (non-numeric): ");
            symbol1 = input.nextLine().charAt(0);
        }
        return symbol1;
    }

}
