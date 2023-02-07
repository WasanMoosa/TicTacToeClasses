package org.tictactoeclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The StateManager class is responsible for saving, loading, and deleting the state of a tic-tac-toe game.
 * The state of the game consists of player1, player2, board, and turnCount.
 */
public class StateManager {
    // The file name where the game state will be stored.
    private final String FILE_NAME = "game_state.json";

    // Gson object to serialize and deserialize game state objects.
    private static Gson gson = new Gson();

    /**
     * Saves the state of the game to a JSON file.
     *
     * @param player1   The first player in the game.
     * @param player2   The second player in the game.
     * @param board     The board of the game.
     * @param turnCount The number of turns taken in the game.
     */
    public void saveState(Player player1, Player player2, Board board, int turnCount) {
        // Create a GameState object with the current state of the game.
        GameState gameState = new GameState(player1, player2, board, turnCount);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            // Write the GameState object to the file as a JSON string.
            gson.toJson(gameState, writer);
        } catch (IOException e) {
            // Print the error message if the file cannot be written.
            e.printStackTrace();
        }
    }

    /**
     * Loads the state of the game from the JSON file.
     *
     * @return The GameState object representing the state of the game.
     */
    public GameState loadState() {
        GameState gameState = null;
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (FileReader reader = new FileReader(FILE_NAME)) {
                // Read the JSON string from the file and convert it to a GameState object.
                gameState = gson.fromJson(reader, GameState.class);
            } catch (IOException e) {
                // Print the error message if the file cannot be read.
                e.printStackTrace();
            }
        }
        return gameState;
    }

    /**
     * Deletes the JSON file storing the game state.
     */
    public void deleteFile() {
        File file = new File(FILE_NAME);
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.close();
            file.delete();
        } catch (IOException e) {
            // Print the error message if the file cannot be deleted.
            e.printStackTrace();
        }
    }

    /**
     * The GameState class represents the objects of a tic-tac-toe game that need to be saved .
     */
    public class GameState {
        private Player player1;
        private Player player2;
        private Board board;
        private int turnCount;

        /**
         * Constructor that initializes the state of the game.
         *
         * @param player1   The first player in the game.
         * @param player2   The second player in the game.
         * @param board     The board on which the game is played.
         * @param turnCount The number of turns that have been taken in the game to know which player should play.
         */
        public GameState(Player player1, Player player2, Board board, int turnCount) {
            this.player1 = player1;
            this.player2 = player2;
            this.board = board;
            this.turnCount = turnCount;
        }

        /**
         * Get the first player of the game.
         *
         * @return player1 instance of the first player.
         */
        public Player getPlayer1() {
            return player1;
        }

        /**
         * Get the second player of the game.
         *
         * @return player2 instance of the second player.
         */
        public Player getPlayer2() {
            return player2;
        }

        /**
         * Get the current state of the game board.
         *
         * @return board instance of the game board.
         */
        public Board getBoard() {
            return board;
        }

        /**
         * Get the current turn count of the game to know which player should play.
         *
         * @return turnCount integer representing the turn count.
         */
        public int getTurnCount() {
            return turnCount;
        }
    }

}
