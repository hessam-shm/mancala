package com.bol.model;

public abstract class SeedHolder {

    private int index;
    private int seeds;
    private Player player;

    public SeedHolder(int index, int seeds, Player player) {
        this.index = index;
        this.seeds = seeds;
        this.player = player;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
