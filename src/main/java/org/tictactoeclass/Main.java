package org.tictactoeclass;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The main class where the game is started
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        StateManager stateManager = new StateManager();
        //In the beginning the grid is empty
        char[][] position = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        //Scanner to read inputs
        Scanner input = new Scanner(System.in);
        //Instantiate file
        String FILE_NAME = "game_state.json";
        File file = new File(FILE_NAME);
        Board board = new Board(position);
        Player player1 = new Player("Unknown", 'X');
        Player player2 = new Player("Unknown", 'O');
        Bot bot = new Bot("Sawan", '@');
        int turnCount = 0;
        int inputState = 1;

        // Initialize menu Item of Tic Tac Toe
        MenuItem onePlayer = new MenuItem(1, "One Player");
        MenuItem twoPlayer = new MenuItem(2, "Two Player");

        // Initialize the Top-level menu
        Menu menu = new Menu("This is  Tic Tac Toe, Want to play", onePlayer);

        // Add menu items to the Top-level menu
        menu.addMenuItem(twoPlayer);

        // Display the menu title and menu items
        System.out.println(menu.getTitle() + "\n");
        menu.showMenu();

        // Read choice of Player
        int numOfPlayer = 0;
        while (numOfPlayer < 1 || numOfPlayer > 2) {
            System.out.print("\nEnter your choice: ");
            try {
                numOfPlayer = Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please choose a number.");
            }
        }
        ////////////////////
        // check the existence of file
        if (file.exists()) {
            System.out.print("Do you want to resume (press 0) ? or Start new game (press 1)");
            inputState = Integer.parseInt(input.nextLine());
            //load the file to bring the objects
            if (file.exists() && inputState == 0) {
                StateManager.GameState state = stateManager.loadState();
                board = state.getBoard();
                player1 = state.getPlayer1();
                player2 = state.getPlayer2();
                turnCount = state.getTurnCount();
                board.getBoard();

            }
        }
        //Start from beginning, so delete the previous file
        if (!file.exists() || inputState == 1) {
            if (file.exists()) {
                stateManager.deleteFile();
            }
            //Instantiate board
            board = new Board(position);
            //Start game
            System.out.println("Start playing TIC TAC TOE");
            //show board
            board.getBoard();

            if (numOfPlayer == 2) {

                // Get information about player 1
                System.out.print("Player 1: write your name: ");
                String player1Name = input.nextLine();
                System.out.print("Player 1: Enter your symbol: ");
                char player1Symbol = input.nextLine().charAt(0);

                // Check the symbol is non-numeric
                player1Symbol = SymbolNotDigit(player1Symbol, player1Name);

                //Instantiate player 1
                player1 = new Player(player1Name, player1Symbol);
            }
            if (numOfPlayer == 1) {

                System.out.println("Hi, Iam " + bot.getName() + ", my symbol " + bot.getSymbol() +
                        " and Iam ready to play against you");
                player1 = new Player(bot.getName(), bot.getSymbol());
            }
            //Get information about player 2
            System.out.print("Player 2: write your name: ");
            String player2Name = input.nextLine();
            System.out.print("Player 2: Enter your symbol: ");
            char player2Symbol = input.nextLine().charAt(0);
            player2Symbol = SymbolNotDigit(player2Symbol, player2Name);
            //Check the symbol is non-numeric
            playerSymbol(player1.getSymbol(), player2Symbol, player2Name);
            //Instantiate player 2
            player2 = new Player(player2Name, player2Symbol);
//////////////////////////////////////////////////////////////////////////////
        }
        boolean exit = false;

        //Only works if the board is not full, no one exit the game and no one win
        while (!board.isGridFull() && !board.win() && !exit) {
            String playerName = "Unknown";
            int row = 0;
            int column = 0;
            // Bot Action
            if (numOfPlayer == 1 && turnCount % 2 == 0) {
                playerName = bot.getName();
                row = bot.makemove().getRow();
                column = bot.makemove().getColumn();
                while (position[row][column] != ' ') {
                    row = bot.makemove().getRow();
                    column = bot.makemove().getColumn();
                }
                board.setSymbol(bot.getSymbol());
                board.setNewPosition(row,column);
                board.changePosition();

                TimeUnit.SECONDS.sleep(1);
                System.out.println("Sawan choosed "+ (row+1)+" "+ (column+1));


            } else {
                if (turnCount % 2 == 0) {
                    playerName = player1.getName();
                    board.setSymbol(player1.getSymbol());
                } else {
                    playerName = player2.getName();
                    board.setSymbol(player2.getSymbol());
                }

                System.out.println(playerName + ", Make a move, Enter position (row then column): ");
                System.out.print("or press 0 0 if you want to exit: ");

                boolean correctInput = false;
                while (!correctInput && !exit) {
                    try {
                        //the user count the row and column from 1, while the program count it from 0
                        row = Integer.parseInt(input.next()) - 1;
                        column = Integer.parseInt(input.next()) - 1;

                        //exit game and save objects in file
                        if (row == -1 && column == -1) {
                            exit = true;
                            stateManager.saveState(player1, player2, board, turnCount);
                        }
                        //check validity of row and column
                        while ((row < 0 || row > position.length - 1 || column < 0 || column > position.length - 1) && !exit) {
                            System.out.println(playerName + ", Invalid position!, numbers from 1-3  ");
                            System.out.print("or press 0 0 if you want to exit: ");
                            row = Integer.parseInt(input.next()) - 1;
                            column = Integer.parseInt(input.next()) - 1;
                        }
                        //means user enter correct input
                        correctInput = true;

                    } catch (Exception e) {
                        System.out.println(playerName + ", Invalid position!, (e.g: 1 3)");
                    }
                }

                if (!exit) {
                    board.setNewPosition(row, column);

                    boolean correctPosition = false;
                    while (!correctPosition) {
                        //put the symbol on the board
                        correctPosition = board.changePosition();
                    }
                }
            }
                board.getBoard();
                //user win and delete file
                if (board.win()) {
                    System.out.println(playerName + " Great job,you are the winner!");
                    stateManager.deleteFile();
                }

            if (!exit && !board.win()) {
                turnCount++;
                stateManager.saveState(player1, player2, board, turnCount);
            }
        }
        if (board.isGridFull()) {
            System.out.println("It is draw!");
            if (file.exists()) {
                stateManager.deleteFile();
            }
        }
//////////////////////////////////////////////////

    }

    /**
     * method to check if the symbol is number or not and it force the inputUser to choose
     * non-numeric symbol.
     *
     * @param symbol
     * @param playerName
     * @return symbol
     */
    public static char SymbolNotDigit(char symbol, String playerName) {
        Scanner input = new Scanner(System.in);
        while (Character.isDigit(symbol)) {
            System.out.print(playerName + ", choose your symbol (non-numeric): ");
            symbol = input.nextLine().charAt(0);
        }
        return symbol;
    }

    /**
     * Method to choose the symbol, Invalid symbol if the symbols are the same of
     * both player
     *
     * @param symbol1
     * @param symbol2
     * @param player2Name name of player 2
     * @return symbol2 symbol of player 2
     */
    static char playerSymbol(char symbol1, char symbol2, String player2Name) {
        Scanner input = new Scanner(System.in);
        while (symbol1 == symbol2) {
            System.out.print(player2Name + " Invalid Symbol, Please choose different symbol ");
            symbol2 = input.next().charAt(0);
            SymbolNotDigit(symbol2, player2Name);
        }
        return symbol2;
    }
}