package com.bol.service;

import com.bol.model.Bank;
import com.bol.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class MoveService {

    public boolean isEligibleToCapture(int pitIndex, GameState state){
        return (state.getBoard().getPits().get(pitIndex).getPlayer().equals(state.getTurn())
                && state.getBoard().getPits().get(pitIndex).getSeeds() == 1);
    }

    public boolean isLegalMove(int pitIndex, GameState state){
        return (state.getBoard().getPits().get(pitIndex).getPlayer().equals(state.getTurn())
                && state.getBoard().getPits().get(pitIndex).getSeeds() != 0);
    }

    //no need to check the owner, others' banks are skipped in move
    public boolean isEligibleToFreeTurn(int pitIndex, GameState state){
        return state.getBoard().getPits().get(pitIndex) instanceof Bank;
    }
}
