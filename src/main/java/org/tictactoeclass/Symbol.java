package org.tictactoeclass;

import java.util.Scanner;

public class Symbol {
    private char symbol1;
    private String playerName;
    private char symbol2;

    Scanner input = new Scanner(System.in);

    public Symbol(char symbol1, char symbol2) {
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
    }

    public Symbol(char symbol1, String playerName) {
        this.symbol1 = symbol1;
        this.playerName = playerName;
    }

    public char SymbolNotDigit() {
        Scanner input = new Scanner(System.in);
        while (Character.isDigit(symbol1)) {
            System.out.print(playerName + ", choose your symbol (non-numeric): ");
            symbol1 = input.nextLine().charAt(0);
        }
        return symbol1;
    }

}
