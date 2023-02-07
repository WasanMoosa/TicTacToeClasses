package org.tictactoeclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StateManager {
    private final String FILE_NAME = "game_state.json";
    private static Gson gson = new Gson();

    public void saveState(Player player1, Player player2, Board board, int turnCount) {
        GameState gameState = new GameState(player1, player2, board, turnCount);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(gameState, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameState loadState() {
        GameState gameState = null;
        Scanner sc = new Scanner(System.in);
        File file = new File(FILE_NAME);
        if (file.exists()) {

            try (FileReader reader = new FileReader(FILE_NAME)) {
                gameState = gson.fromJson(reader, GameState.class);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return gameState;
    }

    public void deleteFile() {
        // delete File
        File file = new File(FILE_NAME);
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.close();
            file.delete();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public class GameState {
        private Player player1;
        private Player player2;
        private Board board;
        private int turnCount;

        public GameState(Player player1, Player player2, Board board, int turnCount) {
            this.player1 = player1;
            this.player2 = player2;
            this.board = board;
            this.turnCount = turnCount;
        }

        public Player getPlayer1() {
            return player1;
        }

        public Player getPlayer2() {
            return player2;
        }

        public Board getBoard() {
            return board;
        }

        public int getTurnCount() {
            return turnCount;
        }
    }

}


