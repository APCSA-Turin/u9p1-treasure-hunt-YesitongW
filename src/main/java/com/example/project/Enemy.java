package com.example.project;

public class Enemy extends Sprite {
    public Enemy(int x, int y) {
        super(x, y);
        this.emoji = "🦂"; // set enemy symbol
    }
    
    //enemy coordinates
    @Override
    public String getCoords() {
        return "Enemy:" + super.getCoords();
    }
    
    //enemy grid position
    @Override
    public String getRowCol(int size) {
        return "Enemy:" + super.getRowCol(size);
    }
}
