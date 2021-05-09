package com.bol.service;

import com.bol.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

    @Autowired
    private BoardService boardService;
    @Autowired
    private MoveService moveService;
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

    public GameState move(int pitIndex, Player player){
        if(state.getWinner() != null || !moveService.isLegalMove(pitIndex,state)){
            return state;
        }

        SeedHolder landingPit = boardService.updateBoard(pitIndex,player);
        state.setBoard(boardService.getBoard());
        state.setTurn(takeTurn(player));

        if(moveService.isEligibleToFreeTurn(landingPit.getIndex(), state)){
            state.setTurn(player);
            state.setMessage(player + " can move again");
            return state;
        }

        if(isGameEnded()){
            state.setMessage("Game over");
            state.setWinner(defineWinner());
        }

        if(moveService.isEligibleToCapture(landingPit.getIndex(),state)){
            capture(pitIndex,player);
            state.setBoard(boardService.getBoard());
        }

        return state;
    }

    //in two player games the pit opposite of your pit is captured
    //in more than two players to avoid ambiguity the correspondent pits will be captured
    private void capture(int pitIndex, Player player){
        boardService.capture(pitIndex,player);
        if(boardService.getNumberOfPlayers() <= 2){
            boardService.capture(state.getBoard().getPits().size() - 2 - pitIndex, player);
        } else {
            int nextIndexToCapure = pitIndex + boardService.getnumberOfPits() + 1;
            while(nextIndexToCapure < state.getBoard().getPits().size()){
                capture(nextIndexToCapure,player);
                nextIndexToCapure += boardService.getnumberOfPits() + 1;
            }
        }
    }

    public Player defineWinner(){

        Map<Player, Integer> playerPoints = new HashMap<>();
        state.getBoard().getPlayers().forEach(
                p -> playerPoints.put(p,state.getBoard().getPits().stream()
                        .filter(pit -> pit.getPlayer().equals(p))
                        .mapToInt(SeedHolder::getSeeds).sum())
        );

        return playerPoints.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
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

    private Player takeTurn(Player player){
        int nextIndex = state.getBoard().getPlayers().indexOf(player) + 1;
        if(nextIndex == state.getBoard().getPlayers().size())
            nextIndex = 0;
        return state.getBoard().getPlayers().get(nextIndex);
    }
}
