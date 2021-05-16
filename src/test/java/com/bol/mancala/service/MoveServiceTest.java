package com.bol.mancala.service;

import com.bol.mancala.model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MoveServiceTest {

    @MockBean
    GameState state;
    @MockBean
    Board board;
    @InjectMocks
    @Autowired
    MoveService moveService;

    @BeforeEach
    public void setupState(){
        List<SeedHolder> pits = new ArrayList<>(Arrays.asList(
        new Pit(0,1,new Player("Player 1")),
        new Pit(1,0,new Player("Player 1")),
        new Bank(2,1,new Player("Player 1")),
        new Pit(3,1,new Player("Player 2")),
        new Pit(4,0,new Player("Player 2")),
        new Bank(5,4,new Player("Player 2"))));

        Mockito.when(state.getBoard()).thenReturn(board);
        Mockito.when(state.getBoard().getPits()).thenReturn(pits);
        Mockito.when(state.getTurn()).thenReturn(new Player("Player 1"));
    }

    @Test
    public void isEligibleForFreeTurnTest(){
        Assertions.assertTrue(moveService.isEligibleToFreeTurn(2,state));
    }
    @Test
    public void isEligibleToFreeTurnOnNormalPit(){
        Assertions.assertFalse(moveService.isEligibleToFreeTurn(1,state));
    }

    @Test
    public void isEligibleToCaptureTest(){
        Assertions.assertTrue(moveService.isEligibleToCapture(0,new Player("Player 1"),state));
        Assertions.assertFalse(moveService.isEligibleToCapture(1,new Player("Player 1"),state));
    }
    @Test
    public void isEligibleToCaptureBank(){
        Assertions.assertFalse(moveService.isEligibleToCapture(2,new Player("Player 1"),state));
    }
    @Test
    public void isEligibleToCaptureOtherPlayerPit(){
        Assertions.assertFalse(moveService.isEligibleToCapture(3,new Player("Player 1"),state));
    }

    @Test
    public void isLegalMoveTest(){
        Assertions.assertTrue(moveService.isLegalMove(0,state));
        Assertions.assertFalse(moveService.isLegalMove(4,state));
    }
    @Test
    public void isLegalMoveOutOfRangeIndex(){
        Assertions.assertFalse(moveService.isLegalMove(1000,state));
    }
    @Test
    public void isLegalMoveEmptyPit(){
        Assertions.assertFalse(moveService.isLegalMove(1,state));
    }
}
