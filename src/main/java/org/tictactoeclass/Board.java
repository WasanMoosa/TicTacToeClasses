package org.tictactoeclass;

import java.util.Scanner;

/**
 * This is a board class, its responsibilities are:
 * 1)Show the grid board of the game.
 * 2)know if someone win
 * 3)put the symbol in the board
 * 4)notify if the board is full
 */
public class Board {
    public char[][] position = new char[3][3];
    private int row;
    private int column;
    private char symbol;

    public Board(char[][] position) {
        this.position = position;

    }

    public void getBoard() {

        for (int i = 0; i < 3; i++) {

            if (i != 0) {
                System.out.print("\n");
                System.out.print("------------");
            }
            System.out.print("\n");
            for (int r = 0; r < 3; r++) {
                System.out.print(" " + position[i][r] + " |");
            }
        }
        System.out.print("\n");
    }

    public boolean isGridFull() {
        boolean full = true;
        //Game is finish and draw
        for (int i = 0; i < 3; i++) {
            for (int r = 0; r < 3; r++) {
                if (position[r][i] == ' ') {
                    full = false;
                }

            }

        }
        return full;
    }

    public boolean win() {
        boolean win = false;
        // Win conditions
        if (position[0][0] == symbol && position[0][1] == symbol && position[0][2] == symbol
                || position[1][0] == symbol && position[1][1] == symbol && position[1][2] == symbol
                || position[2][0] == symbol && position[2][1] == symbol && position[2][2] == symbol
                || position[0][0] == symbol && position[1][0] == symbol && position[2][0] == symbol
                || position[0][1] == symbol && position[1][1] == symbol && position[2][1] == symbol
                || position[0][2] == symbol && position[1][2] == symbol && position[2][2] == symbol
                || position[0][0] == symbol && position[1][1] == symbol && position[2][2] == symbol
                || position[0][2] == symbol && position[1][1] == symbol && position[2][0] == symbol) {
            win = true;

        }
        return win;
    }

    public char[][] getPosition() {
        return position;
    }

    public boolean changePosition() {
        Scanner input = new Scanner(System.in);
        boolean correctPosition = false;
        if (position[row][column] == ' ') {
            position[row][column] = symbol;
            correctPosition = true;
        } else {
            System.out.print("The position reserved, choose other location: ");
            row = Integer.parseInt(input.next()) - 1;
            column = Integer.parseInt(input.next()) - 1;

        }
        return correctPosition;


    }

    //setter
    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


}