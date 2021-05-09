package com.bol.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Board {

    List<SeedHolder> pits = new ArrayList<>();
    List<Player> players = new ArrayList<>();

    public List<SeedHolder> getPits() {
        return pits;
    }

    public void setPits(List<SeedHolder> pits) {
        this.pits = pits;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Pit getPitByIndex(int index){
        return pits.stream().filter(p -> p instanceof Pit)
                .map(p -> (Pit)p).filter(p -> p.getIndex() == index)
                .findFirst().orElse(null);
    }
}
