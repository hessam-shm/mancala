package com.bol.service;

import com.bol.model.Bank;
import com.bol.model.GameState;
import com.bol.model.Pit;
import com.bol.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

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
        state.setMessage("Game starts");
        return state;
    }

    public GameState move(int pitId, String player){

    }

    public Player defineWinner(){
        return boardService.getBoard().getPits().stream()
                .filter(p -> p instanceof Bank).map(p -> (Bank)p)
                .max(Comparator.comparingInt(p -> p.getSeeds()))
                .get().getPlayer();
    }

    public GameState getGameState(){
        return state;
    }

    private boolean isGameEnded(){
        if(boardService.getNumberOfPlayers() <= 2){
            for(Player player: boardService.getBoard().getPlayers()){
                if(boardService.getBoard().getPits().stream()
                        .filter(p -> p instanceof Pit).map(p -> (Pit)p)
                        .filter(p -> p.getPlayer() == player)
                        .allMatch(Pit::isEmpty))
                    return true;
            }
            return false;
        } else {
            return boardService.getBoard().getPits().stream()
                    .filter(p -> p instanceof Pit).map(p -> (Pit)p)
                    .allMatch(Pit::isEmpty);
        }
    }
}
