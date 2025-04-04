package com.example.project;

//wins the game when collected after ALL treasures
public class Trophy extends Treasure {
    public Trophy(int x, int y) {
        super(x, y);
        this.emoji = "🏆"; //trophy symbol
    }
    
    //trophy coordinates
    @Override
    public String getCoords(){
        return "Trophy:" + super.getCoords();
    }
    
    //trophy grid position
    @Override
    public String getRowCol(int size){
        return "Trophy:" + super.getRowCol(size);
    }
}
