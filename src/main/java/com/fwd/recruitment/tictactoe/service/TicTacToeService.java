package com.fwd.recruitment.tictactoe.service;

import org.springframework.stereotype.Service;

import com.fwd.recruitment.tictactoe.model.GameState;

@Service
public class TicTacToeService {

    private char[][] board;
    private int boardSize;
    private static final char PLAYER = 'X';
    private static final char AI = 'O';

    public GameState initializeGame(int size) {
        boardSize = size;
        board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
        return new GameState(board, false, 0, "Game Started!");
    }

    public GameState processMove(int row, int col) {
        if (board[row][col] != ' ') {
            return new GameState(board, false, 0, "Invalid move!");
        }

        board[row][col] = PLAYER;
        if (checkWin(PLAYER)) {
            return new GameState(board, true, 1, "You win!");
        }

        // Check for a draw (full board and no winner)
        if (isBoardFull()) {
            return new GameState(board, true, 0, "Game Over! It's a draw.");
        }

        aiMove(); // AI's move
        if (checkWin(AI)) {
            return new GameState(board, true, 2, "AI wins!");
        }

        return new GameState(board, false, 0, "Game in progress");
    }

    // Method to check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false; // Empty cell found, board is not full
                }
            }
        }
        return true; // No empty cells, board is full
    }

    private void aiMove() {
        int[] winningMove = findWinningMove(AI);
        if (winningMove.length > 0) {
            // AI wins by making the winning move
            board[winningMove[0]][winningMove[1]] = AI;
            return;
        }

        int[] blockMove = findWinningMove(PLAYER);
        if (blockMove.length > 0) {
            // Block player's winning move
            board[blockMove[0]][blockMove[1]] = AI;
            return;
        }

        // If no win or block move, choose a random move
        makeRandomMove();
    }

    private void makeRandomMove() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = AI;
                    return;
                }
            }
        }
    }

    private int[] findWinningMove(char player) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = player;
                    if (checkWin(player)) {
                        board[i][j] = ' ';
                        return new int[]{i, j}; // Found a winning move
                    }
                    board[i][j] = ' ';
                }
            }
        }
        return new int[]{}; // No winning move found
    }

    private boolean checkWin(char player) {
        // Check all rows and columns
        for (int i = 0; i < boardSize; i++) {
            if (checkLine(player, i, 0, 0, 1) || checkLine(player, 0, i, 1, 0)) {
                return true;
            }
        }
        // Check diagonals
        return checkLine(player, 0, 0, 1, 1) || checkLine(player, 0, boardSize - 1, 1, -1);
    }

    private boolean checkLine(char player, int x, int y, int dx, int dy) {
        for (int i = 0; i < boardSize; i++) {
            if (board[x + i * dx][y + i * dy] != player) {
                return false;
            }
        }
        return true;
    }
}
