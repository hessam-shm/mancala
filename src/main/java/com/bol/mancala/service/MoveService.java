package com.bol.mancala.service;

import com.bol.mancala.model.Bank;
import com.bol.mancala.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class MoveService {

    public boolean isEligibleToCapture(int pitIndex, GameState state){
        return (state.getBoard().getPits().get(pitIndex).getPlayer().equals(state.getTurn())
                && state.getBoard().getPits().get(pitIndex).getSeeds() == 1);
    }

    public boolean isLegalMove(int pitIndex, GameState state){
        if(!state.getBoard().getPits().get(pitIndex).getPlayer().equals(state.getTurn())){
            state.setMessage("This pit does not belong to " + state.getTurn());
            return false;
        }
        if(state.getBoard().getPits().get(pitIndex).getSeeds() == 0){
            state.setMessage("This pit is empty");
            return false;
        }

        return true;
    }

    //no need to check the owner, others' banks are skipped in move
    public boolean isEligibleToFreeTurn(int pitIndex, GameState state){
        return state.getBoard().getPits().get(pitIndex) instanceof Bank;
    }
}
