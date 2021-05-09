package com.bol.service;

import com.bol.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private Board board;
    private final int NUMBER_OF_PITS;
    private final int NUMBER_OF_SEEDS;
    private final int NUMBER_OF_PLAYERS;

    @Autowired
    public BoardService(@Value("${game.numberofpits}") int numberOfPits,
                        @Value("${game.numberofseeds}") int numberOfSeeds,
                        @Value("${game.numberofplayers}") int numberOfPlayers,
                        Board board){
        NUMBER_OF_PITS = numberOfPits;
        NUMBER_OF_SEEDS = numberOfSeeds;
        NUMBER_OF_PLAYERS = numberOfPlayers;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void initializeBoard(){
        initialzePlayers();
        List<SeedHolder> seedHolders = new ArrayList<>();
        int globalIndex = 0;
        for(Player p: board.getPlayers()){
            for(int i=0;i<NUMBER_OF_PITS;i++){
                seedHolders.add(new Pit(globalIndex,NUMBER_OF_SEEDS,p));
                globalIndex++;
            }
            seedHolders.add(new Bank(globalIndex,0,p));
            globalIndex++;
        }
    }

    private void initialzePlayers(){
        List<Player> players = new ArrayList<>();
        for(int i=1;i<=NUMBER_OF_PLAYERS;i++)
            players.add(new Player("Player_" + i));
        board.setPlayers(players);
    }

    public void updateBoard(int pitIndex){
        Pit pit = board.getPitByIndex(pitIndex);
        for(int i=1;i <= pit.getSeeds(); i++){
            board.getPitByIndex(pitIndex + i).addSeed();
        }
        pit.takeAllSeeds();
    }

    public int getNumberOfPlayers(){
        return NUMBER_OF_PLAYERS;
    }
}
