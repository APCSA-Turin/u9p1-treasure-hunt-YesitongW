package com.example.project;

public class Player extends Sprite {
    private int treasureCount; // treasures collected
    private int numLives;      // lives remaining
    private boolean win;       // win status

    public Player(int x, int y) {
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
        this.emoji = "🦄"; //player symbol
    }

    public int getTreasureCount() { return treasureCount; }
    public int getLives() { return numLives; }
    public boolean getWin() { return win; }

    @Override
    public void move(String direction) {    // Move the player based on input direction
        if(direction.equals("w")){
            setY(getY() + 1); //up
        } else if(direction.equals("s")){
            setY(getY() - 1); //down
        } else if(direction.equals("a")){
            setX(getX() - 1); //left
        } else if(direction.equals("d")){
            setX(getX() + 1); //right
        }
    }
    
    // Return coordinates with "Player:"
    @Override
    public String getCoords() {
        return "Player:" + super.getCoords();
    }

    // Return grid position with "Player:"
    @Override
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }

    public void interact(int size, String direction, int numTreasures, Object obj) { // handle interactions based on the object the player moves into
        if (obj instanceof Treasure && !(obj instanceof Trophy)) {
            treasureCount++; //touch treasure
        } else if (obj instanceof Enemy) {
            numLives--; //touch enemy
        } else if (obj instanceof Trophy) {
            if (treasureCount == numTreasures) {
                win = true; //if all treasures collected
            }
        }
    }

    public boolean isValid(int size, String direction) {// Check if is in bounds
        int newX = getX();
        int newY = getY();
        if (direction.equals("w")) {
            newY++;
        } else if (direction.equals("s")) {
            newY--;
        } else if (direction.equals("a")) {
            newX--;
        } else if (direction.equals("d")){
            newX++;
        }
        return newX >= 0 && newX < size && newY >= 0 && newY < size; //return true if the nre position is within grid boundaries, false otherwise
    }
}
