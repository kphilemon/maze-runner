package main;

import main.game.Game;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        // clear screen first
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("***********Welcome to the Maze Runner Game***********");
        new Game();
    }
}
