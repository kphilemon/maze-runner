package main.game_objects;

/**
 * A class representing the 'player' of this game.
 *
 * @author kphilemon
 */
public class Player extends GameObject implements Movable {

    public static final int INITIAL_HEALTH_LEVEL = 250;
    private static final char DEFAULT_ICON = 'J';
    private static final int INITIAL_VISIBILITY = 2;
    private int visibility;
    private int healthLevel;
    private boolean hasTorch;

    /**
     * @param position the {@link Position} of this game object
     */
    public Player(Position position) {
        super(DEFAULT_ICON, position);
        this.visibility = INITIAL_VISIBILITY;
        this.healthLevel = INITIAL_HEALTH_LEVEL;
        this.hasTorch = false;
    }

    /**
     * Returns the {@link #visibility} of this Player object.
     * Visibility is the number of blocks that is not covered up and is visible to the player.
     *
     * @return the {@link #visibility} of this Player object
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * Returns the {@link #healthLevel} of this Player object.
     *
     * @return the {@link #healthLevel} of this Player object
     */
    public int getHealthLevel() {
        return healthLevel;
    }

    /**
     * Reduces the {@link #healthLevel} of this Player object.
     *
     * @param value the value to be reduced
     */
    public void reduceHealthLevelBy(int value) {
        healthLevel -= value;
    }

    /**
     * Returns <code>true</code> if this Player object's {@link #healthLevel} is less than or equals to zero.
     *
     * @return <code>true</code> if {@link #healthLevel} is less than or equals to zero, <code>false</code> otherwise
     */
    public boolean isDead() {
        return healthLevel <= 0;
    }

    /**
     * Returns <code>true</code> if the player has reached a torch before.
     *
     * @return <code>true</code> if the player has torch, <code>false</code> otherwise
     */
    public boolean hasTorch() {
        return hasTorch;
    }

    /**
     * Sets {@link #hasTorch} to <code>true</code> and increase this Player object's {@link #visibility}.
     */
    public void foundTorch() {
        hasTorch = true;
        visibility += Torch.VISIBILITY_BOOST;
    }

    /**
     * Changes this Player object's Position based on the specified direction
     *
     * @param direction the direction to move in integer
     */
    @Override
    public void move(int direction) {
        switch (direction) {
            case DIRECTION_UP:
                this.setY(this.getY() - 2);
                break;
            case DIRECTION_DOWN:
                this.setY(this.getY() + 2);
                break;
            case DIRECTION_LEFT:
                this.setX(this.getX() - 4);
                break;
            case DIRECTION_RIGHT:
                this.setX(this.getX() + 4);
                break;
            default:
                break;
        }
    }
}
