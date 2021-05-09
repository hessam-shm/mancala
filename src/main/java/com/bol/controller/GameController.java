package com.bol.controller;

import com.bol.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/start")
    public void startGame(){
        gameService.startGame();
    }
    @GetMapping("/restart")
    public void restartGame(){
        gameService.startGame();
    }

    @GetMapping("/move")
    public void move(@RequestParam String pitIndex){
        gameService.move(Integer.parseInt(pitIndex));
    }
}
