package com.bol.mancala.model;

import org.springframework.stereotype.Component;

@Component
public class Move {

    private int pitId;
    private Player player;

    public int getPitId() {
        return pitId;
    }

    public void setPitId(int pitId) {
        this.pitId = pitId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
