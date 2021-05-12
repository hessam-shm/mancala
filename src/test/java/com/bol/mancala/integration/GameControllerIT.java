package com.bol.mancala.integration;

import com.bol.mancala.model.GameState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    GameState state;

    @Test
    public void startTest() throws Exception {
        mockMvc.perform(get("/mancala/start"))
                .andExpect(jsonPath("$.message").value("Game starts"))
                .andExpect(jsonPath("$.turn.name").value("Player 1"))
                .andReturn();
    }

    @Test
    public void restartTest() throws Exception {
        mockMvc.perform(get("/mancala/restart"))
                .andExpect(jsonPath("$.message").value("Game starts"))
                .andExpect(jsonPath("$.turn.name").value("Player 1"))
                .andReturn();
    }

    @Test
    public void moveTest() throws Exception {
        mockMvc.perform(get("/mancala/start")).andReturn();
        mockMvc.perform(get("/mancala/move").param("pitId","0"))
                .andExpect(jsonPath("$.board.pits[0].seeds").value(0))
                .andExpect(jsonPath("$.board.pits[2].seeds").value(1))
                .andExpect(jsonPath("$.board.pits[4].seeds").value(5))
                .andExpect(jsonPath("$.turn.name").value("Player 2"))
                .andReturn();

    }
    @Test
    public void outOfTurnMoveTest() throws Exception {
        mockMvc.perform(get("/mancala/start")).andReturn();
        mockMvc.perform(get("/mancala/move").param("pitId","0"))
                .andExpect(jsonPath("$.board.pits[0].seeds").value(4))
                .andExpect(jsonPath("$.board.pits[2].seeds").value(0))
                .andExpect(jsonPath("$.board.pits[4].seeds").value(4))
                .andExpect(jsonPath("$.turn.name").value("Player 1"))
                .andReturn();

    }
    @Test
    public void outOfTurnMoveTest1() throws Exception {
        mockMvc.perform(get("/mancala/start")).andReturn();
        mockMvc.perform(get("/mancala/move").param("pitId","0"))
                .andExpect(jsonPath("$.board.pits[0].seeds").value(0))
                .andExpect(jsonPath("$.board.pits[2].seeds").value(1))
                .andExpect(jsonPath("$.board.pits[4].seeds").value(4))
                .andExpect(jsonPath("$.turn.name").value("Player 2"))
                .andReturn();

    }
}
