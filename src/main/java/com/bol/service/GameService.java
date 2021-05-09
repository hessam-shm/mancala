package com.bol.service;

import com.bol.model.GameState;
import com.bol.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private BoardService boardService;
    @Autowired
    private GameState state;

    public GameState startGame(){
        boardService.initializeBoard();
        state.setBoard(boardService.getBoard());
        state.setTurn(boardService.getBoard().getPlayers().get(0));
        state.setWinner(null);
        return state;
    }

    public GameState move(int pitId, String player){

    }

    public Player defineWinner(){

    }

    private boolean isGameEnded(){

    }
}
