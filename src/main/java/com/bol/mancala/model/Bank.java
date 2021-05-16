package com.bol.mancala.model;

import org.springframework.stereotype.Component;

@Component
public class Bank extends SeedHolder{
    public Bank(int index, int seeds, Player player) {
        super(index, seeds, player);
    }
    public Bank(){}

    public void accumulateSeeds(int numberOfSeeds){
        this.setSeeds(this.getSeeds() + numberOfSeeds);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
