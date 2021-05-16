package com.bol.mancala.model;

import java.util.Objects;

public abstract class SeedHolder {

    private int index;
    private int seeds;
    private Player player;

    protected SeedHolder(int index, int seeds, Player player) {
        this.index = index;
        this.seeds = seeds;
        this.player = player;
    }
    protected SeedHolder(){}

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

    public void addSeed(){
        this.setSeeds(this.getSeeds()+1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeedHolder that = (SeedHolder) o;
        return getIndex() == that.getIndex() && Objects.equals(getPlayer(), that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getPlayer());
    }
}
