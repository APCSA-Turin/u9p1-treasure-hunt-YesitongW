package com.example.project;

public class Grid {
    private Sprite[][] grid;
    private int size; //size x size

    //create a grid
    public Grid(int size) {
        this.size = size;
        grid = new Sprite[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                grid[i][j] = new Dot(j, i); // fill spot with a Dot
            }
        }
    }
 
    // Get the grid array
    public Sprite[][] getGrid() { 
        return grid; 
    }
 
    // Place a sprite at its current coordinates
    public void placeSprite(Sprite s) {
        grid[size - 1 - s.getY()][s.getX()] = s;
    }
    
    
    public void placeSprite(Sprite s, String direction) {
        int newX = s.getX();
        int newY = s.getY();
        int oldX = newX;
        int oldY = newY;
        
        if (direction.equals("w")) {// Update sprite's position after moving
            oldY = newY - 1;
        } else if (direction.equals("s")) {
            oldY = newY + 1;
        } else if (direction.equals("a")) {
            oldX = newX + 1;
        } else if (direction.equals("d")) {
            oldX = newX - 1;
        }
        
        if (oldX >= 0 && oldX < size && oldY >= 0 && oldY < size) {
            grid[size - 1 - oldY][oldX] = new Dot(oldX, oldY);// Clears the old spot
        }
        
        // Place sprite in new spot
        grid[size - 1 - newY][newX] = s;
    }
    
    public void display() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(grid[i][j].getSymbol());//display the grids 
            }
            System.out.println();
        }
    }
    
    public void gameover() {//lose
        System.out.println("Game Over! You lost.");
    }


    public void win() {//win
        System.out.println("Congratulations! You won!");
    }
}
