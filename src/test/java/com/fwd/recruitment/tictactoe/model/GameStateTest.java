package com.fwd.recruitment.tictactoe.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameStateTest {

    private char[][] board;
    private GameState gameState;

    @BeforeEach
    void setUp() {
        // Create a sample board for testing
        board = new char[][] {
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {' ', 'X', ' '}
        };
        gameState = new GameState(board, false, 0, "Game in progress");
    }

    @Test
    void testConstructorAndGetters() {
        // Test that the board is correctly converted from char[][] to String[][]
        String[][] expectedBoard = {
                {"X", "O", "X"},
                {"O", "X", "O"},
                {" ", "X", " "}
        };

        assertArrayEquals(expectedBoard, gameState.getBoard(), "The board should match the expected String[][]");

        // Test other fields
        assertFalse(gameState.isGameOver(), "The game should not be over");
        assertEquals("Game in progress", gameState.getMessage(), "The message should be 'Game in progress'");
        assertEquals(0, gameState.getWinner(), "The winner should be 0 (no winner yet)");
    }

    @Test
    void testGameOverState() {
        // Create a new GameState instance for a game over situation
        gameState = new GameState(board, true, 1, "You win!");

        assertTrue(gameState.isGameOver(), "The game should be over");
        assertEquals("You win!", gameState.getMessage(), "The message should be 'You win!'");
        assertEquals(1, gameState.getWinner(), "The winner should be player 1 (X)");
    }

    @Test
    void testEmptyBoard() {
        // Test for an empty board scenario
        char[][] emptyBoard = new char[3][3];
        for (int i = 0; i < emptyBoard.length; i++) {
            for (int j = 0; j < emptyBoard[i].length; j++) {
                emptyBoard[i][j] = ' ';  // Fill with empty spaces
            }
        }

        gameState = new GameState(emptyBoard, false, 0, "Game in progress");

        String[][] expectedEmptyBoard = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };

        assertArrayEquals(expectedEmptyBoard, gameState.getBoard(), "The board should be all empty spaces");
    }

    @Test
    void testFullBoard() {
        // Test for a full board (no empty spaces left)
        char[][] fullBoard = {
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'O', 'X', 'O'}
        };

        gameState = new GameState(fullBoard, true, 1, "Game over!");

        String[][] expectedFullBoard = {
                {"X", "O", "X"},
                {"O", "X", "O"},
                {"O", "X", "O"}
        };

        assertArrayEquals(expectedFullBoard, gameState.getBoard(), "The board should have all cells filled");
    }

    @Test
    void testMessageForInvalidBoard() {
        // Test for an invalid move scenario (e.g., trying to play on an already filled spot)
        char[][] invalidBoard = {
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'O', 'X', 'O'}
        };

        gameState = new GameState(invalidBoard, true, 2, "AI wins!");

        assertTrue(gameState.isGameOver(), "The game should be over");
        assertEquals("AI wins!", gameState.getMessage(), "The message should be 'AI wins!'");
    }

    // Testing edge case: checking for the smallest possible board (1x1)
    @Test
    void testSmallBoard() {
        char[][] smallBoard = {
                {'X'}
        };
        
        gameState = new GameState(smallBoard, true, 1, "You win!");
        
        String[][] expectedSmallBoard = {
                {"X"}
        };

        assertArrayEquals(expectedSmallBoard, gameState.getBoard(), "The board should be a 1x1 grid");
        assertTrue(gameState.isGameOver(), "The game should be over");
        assertEquals("You win!", gameState.getMessage(), "The message should be 'You win!'");
    }
}
