package com.fwd.recruitment.tictactoe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fwd.recruitment.tictactoe.model.GameState;
import com.fwd.recruitment.tictactoe.service.TicTacToeService;

@ExtendWith(MockitoExtension.class)
  class GameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicTacToeService ticTacToeService;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
      void testStartGame() throws Exception {
        // Mock the service method call
        GameState mockGameState = new GameState(new char[3][3], false, 0, "Game Started!");
        when(ticTacToeService.initializeGame(3)).thenReturn(mockGameState);

        // Perform the test for the startGame method
        mockMvc.perform(post("/api/game/start")
                .param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Game Started!"));

        verify(ticTacToeService, times(1)).initializeGame(3);
    }

    @Test
      void testPlayerMove() throws Exception {
        // Mock the service method call
        GameState mockGameState = new GameState(new char[3][3], false, 0, "Game in progress");
        when(ticTacToeService.processMove(0, 0)).thenReturn(mockGameState);

        // Perform the test for the playerMove method
        mockMvc.perform(post("/api/game/move")
                .param("row", "0")
                .param("col", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Game in progress"));

        verify(ticTacToeService, times(1)).processMove(0, 0);
    }
}
