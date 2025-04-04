package com.example.project;

// Base class for all game objects
public class Sprite {
    private int x, y; // position of the sprite
    // Emoji used to represent the sprite on screen
    protected String emoji;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        this.emoji = "."; // default symbol
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    // Returns (x,y) coordinate
    public String getCoords() {
        return "(" + x + "," + y + ")";
    }

    // Returns grid array position [row][col]
    public String getRowCol(int size) {
        return "[" + (size - 1 - y) + "][" + x + "]";
    }

    //move method (will be overridden)
    public void move(String direction) {
    }

    //interact method (will be overridden)
    public void interact() {
    }
    
    // Get the emojis
    public String getSymbol(){
        return emoji;
    }
}
