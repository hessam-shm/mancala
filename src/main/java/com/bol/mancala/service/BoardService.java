package com.bol.mancala.service;

import com.bol.mancala.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
        board.setPits(seedHolders);
    }

    private void initialzePlayers(){
        List<Player> players = new ArrayList<>();
        for(int i=1;i<=NUMBER_OF_PLAYERS;i++)
            players.add(new Player("Player " + i));
        board.setPlayers(players);
    }

    //returns the last landing pit (or bank)
    public SeedHolder updateBoard(int pitIndex, Player player){
        Pit pit = (Pit)board.getPits().get(pitIndex);
        SeedHolder tmp = pit;

        int j = pit.getSeeds();
        pit.takeAllSeeds();
        int index = 1;
        while(j > 0){
            tmp = board.getPits().get((pitIndex + index) % board.getPits().size());
            index++;
            if(tmp instanceof Bank && !tmp.getPlayer().equals(player)){
                continue;
            }
            tmp.addSeed();
            j--;
        }
        return tmp;
    }

    public void capture(int pitIndex, Player player){
        int numberToCapture = board.getPits().get(pitIndex).getSeeds();
        board.getPits().get(pitIndex).setSeeds(0);
        board.getPits().stream().filter(p -> p instanceof Bank)
                .map(p -> (Bank)p).filter(p -> p.getPlayer().equals(player))
                .findFirst().get().accumulateSeeds(numberToCapture);
    }

    public int getNumberOfPlayers(){
        return NUMBER_OF_PLAYERS;
    }
    public int getnumberOfPits() {return NUMBER_OF_PITS;}
}
