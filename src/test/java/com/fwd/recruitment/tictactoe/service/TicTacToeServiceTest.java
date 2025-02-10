package com.fwd.recruitment.tictactoe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fwd.recruitment.tictactoe.model.GameState;

class TicTacToeServiceTest {

    private TicTacToeService ticTacToeService;

    @BeforeEach
    void setUp() {
        ticTacToeService = new TicTacToeService();
    }

    @Test
    void testInitializeGame() {
        int size = 3;  // 3x3 board
        GameState gameState = ticTacToeService.initializeGame(size);

        assertNotNull(gameState);
        assertEquals(size, gameState.getBoard().length, "Board size should match");
        assertEquals(size, gameState.getBoard()[0].length, "Board width should match");
        assertFalse(gameState.isGameOver(), "Game should not be over after initialization");
        assertEquals("Game Started!", gameState.getMessage(), "Initial message should be 'Game Started!'");
    }

    @Test
    void testPlayerMoveValid() {
        int size = 3;
        ticTacToeService.initializeGame(size);

        // Make a valid move
        GameState gameState = ticTacToeService.processMove(0, 0);

        assertNotNull(gameState);
        assertEquals("Game in progress", gameState.getMessage(), "Message should indicate the game is in progress");
        assertEquals('X', gameState.getBoard()[0][0].charAt(0), "Player move should be marked with 'X'");
    }

    @Test
    void testPlayerMoveInvalid() {
        int size = 3;
        ticTacToeService.initializeGame(size);

        // First move valid
        ticTacToeService.processMove(0, 0);
        // Second move on the same spot (invalid)
        GameState gameState = ticTacToeService.processMove(0, 0);

        assertNotNull(gameState);
        assertEquals("Invalid move!", gameState.getMessage(), "Message should indicate invalid move");
    }

    @Test
    void testAIPlaysAfterPlayer() {
        int size = 3;
        ticTacToeService.initializeGame(size);

        // Player makes a move at (0, 0)
        ticTacToeService.processMove(0, 0);

        // AI makes its move
        GameState gameState = ticTacToeService.processMove(1, 1);  // AI will take a turn

        assertNotNull(gameState);
        assertEquals('X', gameState.getBoard()[1][1].charAt(0), "AI move should be marked with 'O'");
    }
 
}
