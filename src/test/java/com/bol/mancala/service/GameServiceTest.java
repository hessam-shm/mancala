package com.bol.mancala.service;

import com.bol.mancala.model.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GameServiceTest {

    @MockBean
    MoveService moveService;
    @InjectMocks
    @Autowired
    GameService gameService;
    @Autowired
    GameState state;

    @BeforeEach
    public void setupState(){
        state = gameService.startGame();
    }

    @Test
    public void moveTest(){
        Mockito.when(moveService.isLegalMove(0,state)).thenReturn(true);
        Assertions.assertEquals(state.getBoard().getPits().get(4),
                gameService.move(0).getBoard().getPits().get(4));
    }

    @Test
    public void illegalMoveStateRemainsIntact(){
        Mockito.when(moveService.isLegalMove(0,state)).thenReturn(false);
        Assertions.assertEquals(state,gameService.move(0));
    }

    @Test
    public void freeTurnMoveTurnRemainsIntact(){
        Mockito.when(moveService.isEligibleToFreeTurn(0,state)).thenReturn(true);
        Assertions.assertEquals(state.getTurn(),gameService.move(0).getTurn());
    }

    @Test
    public void noMoveIfGameEnded(){
        //define a random player as winner to simulate ended game
        state.setWinner(state.getTurn().getName());
        Assertions.assertEquals(state,gameService.move(0));
    }

}
