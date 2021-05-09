package com.bol.model;

import org.springframework.stereotype.Component;

@Component
public class Bank extends SeedHolder{
    public Bank(int index, int seeds, Player player) {
        super(index, seeds, player);
    }

    public void accumulateSeeds(int numberOfSeeds){
        this.setSeeds(this.getSeeds() + numberOfSeeds);
    }
}
