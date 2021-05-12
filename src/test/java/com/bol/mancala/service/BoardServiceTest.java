package com.bol.mancala.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @BeforeEach
    public void setupBoard(){
        boardService.initializeBoard();
    }

    @Test
    public void updateBoardTest(){
        boardService.updateBoard(0,boardService.getBoard().getPlayers().get(0));
        Assertions.assertEquals(0,boardService.getBoard().getPits().get(0).getSeeds());
        Assertions.assertEquals(5,boardService.getBoard().getPits().get(1).getSeeds());
        Assertions.assertEquals(1,boardService.getBoard().getPits().get(2).getSeeds());
        Assertions.assertEquals(5,boardService.getBoard().getPits().get(4).getSeeds());
    }
    @Test
    public void updateBoardOpponentBankRemainsIntact(){
        boardService.updateBoard(1,boardService.getBoard().getPlayers().get(0));
        Assertions.assertEquals(0,boardService.getBoard().getPits().get(1).getSeeds());
        Assertions.assertEquals(5,boardService.getBoard().getPits().get(0).getSeeds());
        Assertions.assertEquals(0,boardService.getBoard().getPits().get(5).getSeeds());
    }
    @Test
    public void updateBoardLoopAround(){
        boardService.getBoard().getPits().get(0).setSeeds(10);
        boardService.updateBoard(0,boardService.getBoard().getPlayers().get(0));
        Assertions.assertEquals(2,boardService.getBoard().getPits().get(0).getSeeds());
        Assertions.assertEquals(6,boardService.getBoard().getPits().get(1).getSeeds());
        Assertions.assertEquals(2,boardService.getBoard().getPits().get(2).getSeeds());
        Assertions.assertEquals(6,boardService.getBoard().getPits().get(3).getSeeds());
        Assertions.assertEquals(0,boardService.getBoard().getPits().get(5).getSeeds());
    }

    @Test
    public void captureTest(){
        boardService.capture(1,boardService.getBoard().getPlayers().get(0));
        Assertions.assertEquals(0,boardService.getBoard().getPits().get(1).getSeeds());
        Assertions.assertEquals(4,boardService.getBoard().getPits().get(2).getSeeds());
    }
}
