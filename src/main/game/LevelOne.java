package main.game;

import main.game_objects.Key;
import main.game_objects.Player;
import main.game_objects.Torch;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * A class encapsulating the game logic of level one.
 * <p>
 * Level details:
 * <ul>
 * <li>Mission: collect all keys to reach level two</li>
 * <li>Bonus: collect torch to increase visibility</li>
 * <li>Each valid movement costs one drop of blood.
 * Game over when the health level of the player is equals to zero.</li>
 * </ul>
 *
 * @author kphilemon
 */
class LevelOne implements KeyListener {

    static final int NUMBER_OF_KEYS = 3;
    private final GameMap gameMap;
    private final Player player;
    private final Display display;
    private ProgressListener progressListener;
    private Torch torch;
    private ArrayList<Key> keys;

    /**
     * @param gameMap the {@link GameMap} object
     * @param player  the {@link Player} object
     * @param display the {@link Display} object
     */
    LevelOne(GameMap gameMap, Player player, Display display) {
        this.gameMap = gameMap;
        this.player = player;
        this.display = display;
        init();
    }

    /**
     * Adds the specified progress listener.
     *
     * @param progressListener the progress listener
     */
    void addProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    /**
     * Initializes level one by adding game objects to the game map.
     */
    private void init() {
        // init game objects
        gameMap.addToMap(player);

        keys = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            Key key = new Key(gameMap.getRandomPosition());
            keys.add(key);
            gameMap.addToMap(key);
        }
        torch = new Torch(gameMap.getRandomPosition());
        gameMap.addToMap(torch);

        display.update(keys);
    }

    /**
     * Starts the game loop of level one.
     */
    void start() {
        while (true) {
            if (player.isDead()) {
                progressListener.levelFailed();
                break;
            }
            // 0 means all keys are collected
            if (keys.size() == 0) {
                gameMap.removeFromMap(torch);
                progressListener.levelOneCompleted();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the game stats if the player has reached specified items in the game.
     */
    private void checkIfPlayerHasReachedItem() {
        for (Key key : keys) {
            if (player.getPosition().equals(key.getPosition())) {
                keys.remove(key);
                keys.trimToSize();
                System.out.println(keys.size());
                return;
            }
        }
        if (!player.hasTorch() && player.getPosition().equals(torch.getPosition()))
            player.foundTorch();
    }

    /**
     * Invoked when a key has been pressed.
     * <p>
     * If the arrow keys are pressed, the movement is validated first.
     * If the direction of movement is valid (not blocked by any walls), the player icon at the
     * position before moving is first removed from the game map. The player icon is added back
     * to the game map at the new position after moving. The health level of the player is reduced
     * by one after each valid movement.
     * <p>
     * The F6 key is used for debugging purposes to reveal the map even the map is previously covered.
     * This is the hidden easter egg :)
     *
     * @param e the KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                final int DIRECTION = e.getKeyCode();
                if (gameMap.validateMovement(player, DIRECTION)) {
                    gameMap.removeFromMap(player);
                    player.move(DIRECTION);
                    gameMap.addToMap(player);
                    checkIfPlayerHasReachedItem();
                    player.reduceHealthLevelBy(1);
                    display.update(keys);
                } else {
                    display.invalidMovementMessage();
                }
                break;
            case KeyEvent.VK_F6:
                Game.isVisibilityMode = !Game.isVisibilityMode;
                display.update(keys);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
