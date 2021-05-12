package com.bol.mancala.controller;

import com.bol.mancala.model.GameState;
import com.bol.mancala.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mancala")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/start")
    public GameState startGame(){
        return gameService.startGame();
    }
    @GetMapping("/restart")
    public GameState restartGame(){
        return gameService.startGame();
    }

    @GetMapping("/move")
    public GameState move(@RequestParam String pitId){
        return gameService.move(Integer.parseInt(pitId));
    }
}
