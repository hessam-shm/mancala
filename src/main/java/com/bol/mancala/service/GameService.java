package com.bol.mancala.service;

import com.bol.mancala.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public GameState startGame() {
        boardService.initializeBoard();
        state.setBoard(boardService.getBoard());
        state.setTurn(boardService.getBoard().getPlayers().get(0));
        state.setWinner(null);
        state.setMessage("Game starts");
        return state;
    }

    public GameState move(int pitIndex) {
        if (state.getWinner() != null || !moveService.isLegalMove(pitIndex, state)) {
            return state;
        }

        SeedHolder landingPit = boardService.updateBoard(pitIndex, state.getTurn());
        state.setBoard(boardService.getBoard());
        state.setMessage("");
        Player previousPlayer = state.getTurn();
        state.setTurn(takeTurn(state.getTurn()));

        if (moveService.isEligibleToFreeTurn(landingPit.getIndex(), state)) {
            state.setTurn(previousPlayer);
            state.setMessage(landingPit.getPlayer() + " can move again");
            //return state;
        }

        if (moveService.isEligibleToCapture(landingPit.getIndex(), previousPlayer, state)) {
            capture(landingPit.getIndex(), previousPlayer);
            state.setBoard(boardService.getBoard());
        }

        if (isGameEnded()) {
            state.setMessage("Game over");
            state.setWinner(defineWinner());
            state.setTurn(null);
            //return state;
        }

        return state;
    }

    //in two player games the pit opposite of your pit is captured
    //in more than two players to avoid ambiguity the correspondent pits will be captured
    private void capture(int pitIndex, Player player) {
        boardService.capture(pitIndex, player);
        if (boardService.getNumberOfPlayers() <= 2) {
            boardService.capture(state.getBoard().getPits().size() - 2 - pitIndex, player);
        } else {
            int nextIndexToCapure = pitIndex + boardService.getnumberOfPits() + 1;
            while (nextIndexToCapure < state.getBoard().getPits().size()) {
                capture(nextIndexToCapure, player);
                nextIndexToCapure += boardService.getnumberOfPits() + 1;
            }
        }
    }

    private String defineWinner() {

        Map<Player, Integer> playerPoints = new HashMap<>();
        state.getBoard().getPlayers().forEach(
                p -> playerPoints.put(p, state.getBoard().getPits().stream()
                        .filter(pit -> pit.getPlayer().equals(p))
                        .mapToInt(SeedHolder::getSeeds).sum())
        );

        int maxPoints = playerPoints.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        String winners = "";
        for (Map.Entry<Player, Integer> player : playerPoints.entrySet()) {
            if (player.getValue().equals(maxPoints)) {
                winners += player.getKey().getName() + " ";
            }
        }
        return winners.trim();
    }

    public GameState getGameState() {
        return state;
    }

    private boolean isGameEnded() {
        if (boardService.getBoard().getPits().stream()
                .filter(p -> p instanceof Pit).map(p -> (Pit) p)
                .filter(p -> p.getPlayer().equals(state.getTurn()))
                .allMatch(Pit::emptySeed))
            return true;
        return false;
    }

    private Player takeTurn(Player player) {
        int nextIndex = state.getBoard().getPlayers().indexOf(player) + 1;
        if (nextIndex == state.getBoard().getPlayers().size())
            nextIndex = 0;
        return state.getBoard().getPlayers().get(nextIndex);
    }
}
