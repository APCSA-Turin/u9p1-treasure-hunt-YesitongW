package com.example.project;
import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; // grid size (size x size)

    public Game(int size) {
        this.size = size;
        initialize(); // set up grid and sprites
        play();       // run game loop
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { //write your game logic here

        Scanner scanner = new Scanner(System.in);

        while (!player.getWin() && player.getLives() > 0) {
            clearScreen(); 
            grid.display();

            System.out.println("Player Coordinates: " + player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Lives: " + player.getLives() + ", Treasures: " + player.getTreasureCount());
            System.out.print("Move (w/a/s/d): ");
            String move = scanner.nextLine();

            if (player.isValid(size, move)) {
                // Calculate where the player wants to go
                int targetX = player.getX();
                int targetY = player.getY();
                if (move.equals("w")) targetY++;      // up
                else if (move.equals("s")) targetY--; // down
                else if (move.equals("a")) targetX--; // left
                else if (move.equals("d")) targetX++; // right

                // Get sprite at target spot
                Sprite targetObj = grid.getGrid()[size - 1 - targetY][targetX];
                // Block trophy if treasures not all collected
                if (targetObj instanceof Trophy && player.getTreasureCount() != treasures.length) {
                    System.out.println("Collect all treasures first");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    continue;
                }

                // Do the interaction (treasure, enemy, etc.)
                player.interact(size, move, treasures.length, targetObj);
                // Move the player
                player.move(move);
                // Update grid: clear old spot and set player in new spot
                grid.placeSprite(player, move);
            }
        }

        // If win, change all non-player sprites to 🌈
        if (player.getWin()) {
            Sprite[][] gridArray = grid.getGrid();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (gridArray[i][j] != player) {
                        gridArray[i][j].emoji = "🌈";
                    }
                }
            }
        }

        // If lost, change all non-player sprites to 💀 
        if (!(player.getWin())) {
            Sprite[][] gridArray = grid.getGrid();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (gridArray[i][j] != player) {
                        gridArray[i][j].emoji = "💀";
                    }
                }
            }
        }

        clearScreen();
        grid.display();
        System.out.println("Player Coordinates: " + player.getCoords());
        System.out.println(player.getRowCol(size));
        System.out.println("Lives: " + player.getLives() + ", Treasures: " + player.getTreasureCount());
        if (player.getWin()) System.out.println("Congratulations! You won!");
        else System.out.println("Game Over! You lost.");
    }

    // Set up grid and place all sprites
    public void initialize() {
        grid = new Grid(size);
        player = new Player(0, 0);
        trophy = new Trophy(size - 1, size - 1);
        treasures = new Treasure[]{ new Treasure(2, 2), new Treasure(7, 7) };
        enemies = new Enemy[]{ new Enemy(5, 5), new Enemy(8, 8) };

        grid.placeSprite(player);
        grid.placeSprite(trophy);
        for (Treasure t : treasures) {
            grid.placeSprite(t);
        }
        for (Enemy e : enemies) {
            grid.placeSprite(e);
        }
    }

    public static void main(String[] args) {
        new Game(10);
    }
}
