package com.bol.mancala.model;

import org.springframework.stereotype.Component;

@Component
public class Pit extends SeedHolder{

    public Pit(int index, int seeds, Player player) {
        super(index, seeds, player);
    }
    public Pit(){}

    public void takeSeed(){
        this.setSeeds(this.getSeeds()-1);
    }

    public void takeAllSeeds(){
        this.setSeeds(0);
    }

    public boolean isEmpty(){
        return getSeeds() == 0;
    }
}
