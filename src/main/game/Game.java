package main.game;

import main.game_objects.Player;

import javax.swing.*;
import java.util.Scanner;

/**
 * A class encapsulating the logic for all the game states .
 * <p>
 * The game starts from level one.
 * If the player completes level one, the player can proceed to level two.
 * The game ends when the player fails in either levels.
 * The player wins when the player completes both levels.
 *
 * @author kphilemon
 */
public class Game implements ProgressListener {

    static boolean isVisibilityMode = true;
    private JFrame jFrame;
    private GameMap gameMap;
    private Player player;
    private Display display;
    private LevelOne levelOne;
    private LevelTwo levelTwo;

    public Game() {
        init();
        setupWindowForKeyListener();
        startLevelOne();
    }

    /**
     * Prompts the player to enter the maze height and maze width and show the game intro.
     * The difficulty of the game increases with the size of the maze.
     * <p>
     * Note: the maze height and width must be at least 5 because there will be 5 game objects in level one.
     * Each game object will be scattered randomly in different rows and columns. No two game objects
     * will appear in the same row nor the same column. Therefore the maze must have a minimum size of 5x5.
     */
    private void init() {
        Scanner s = new Scanner(System.in);
        int mazeHeight, mazeWidth;
        do {
            System.out.print("Enter maze height (min 5): ");
            while (!s.hasNextInt()) {
                System.out.print("That's not a number! Enter again: ");
                s.next();
            }
            mazeHeight = s.nextInt();
        } while (mazeHeight < 5);
        do {
            System.out.print("Enter maze width (min 5): ");
            while (!s.hasNextInt()) {
                System.out.print("That's not a number! Enter again: ");
                s.next();
            }
            mazeWidth = s.nextInt();
        } while (mazeWidth < 5);

        gameMap = new GameMap(mazeHeight, mazeWidth);
        player = new Player(gameMap.getRandomPosition());
        display = new Display(gameMap.getMap(), player);
        display.gameIntroMessage();
    }

    /**
     * Creates a small window on the top left corner of the screen.
     * <p>
     * KeyEvents cannot be detected in the console without JNI or GUI.
     * By setting up a small window and hook the KeyListener to the window,
     * player can control the Player's movement by using the arrow keys.
     * <p>
     * Note: The player has to click on the window first before playing so that the KeyEvents
     * can be captured by the window.
     */
    private void setupWindowForKeyListener() {
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setSize(100, 100);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initializes LevelOne, setup the ProgressListener and KeyListener before starting level one.
     */
    private void startLevelOne() {
        levelOne = new LevelOne(gameMap, player, display);
        levelOne.addProgressListener(this);
        jFrame.addKeyListener(levelOne);
        levelOne.start();
    }

    /**
     * Initializes LevelTwo, setup the ProgressListener and KeyListener before starting level two.
     */
    private void startLevelTwo() {
        levelTwo = new LevelTwo(gameMap, player, display);
        levelTwo.setProgressListener(this);
        jFrame.addKeyListener(levelTwo);
        levelTwo.start();
    }

    @Override
    public void levelOneCompleted() {
        jFrame.removeKeyListener(levelOne);
        display.nextLevelMessage();
        startLevelTwo();
    }

    @Override
    public void levelTwoCompleted() {
        jFrame.removeKeyListener(levelTwo);
        display.winMessage();
        System.exit(0);
    }

    @Override
    public void levelFailed() {
        display.loseMessage();
        System.exit(0);
    }
}
