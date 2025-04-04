package com.example.project;

public class Treasure extends Sprite {
    public Treasure(int x, int y) {
        super(x, y);
        this.emoji = "🌈"; //treasure symbol
    }
    
    //treasure coordinates
    @Override
    public String getCoords() {
        return "Treasure:" + super.getCoords();
    }
    
    //treasure grid position
    @Override
    public String getRowCol(int size) {
        return "Treasure:" + super.getRowCol(size);
    }
}
