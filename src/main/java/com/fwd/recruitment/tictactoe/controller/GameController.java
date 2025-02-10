package com.fwd.recruitment.tictactoe.controller;
 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fwd.recruitment.tictactoe.model.GameState;
import com.fwd.recruitment.tictactoe.service.TicTacToeService;


@RestController
@RequestMapping("/api/game")
@CrossOrigin
public class GameController {
    
    @GetMapping("/hello")
    public String say(@RequestParam String name) {
        return "Hello "+name;
    }
    
    private final TicTacToeService ticTacToeService;

    public GameController(TicTacToeService ticTacToeService) {
        this.ticTacToeService = ticTacToeService;
    }

    @PostMapping("/start")
    public GameState startGame(@RequestParam int size) {
        return ticTacToeService.initializeGame(size);
    }

    @PostMapping("/move")
    public GameState playerMove(@RequestParam int row, @RequestParam int col) {
        return ticTacToeService.processMove(row, col);
    }
}
