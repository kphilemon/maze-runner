package main.game_objects;

/**
 * A class representing the 'key' game object.
 *
 * @author kphilemon
 */
public class Key extends GameObject {

    private static final char DEFAULT_ICON = 'K';

    /**
     * @param position the {@link Position} of this game object
     */
    public Key(Position position) {
        super(DEFAULT_ICON, position);
    }

    /**
     * Returns a String object representing the direction hints to this Key object
     * from the specified player's position.
     *
     * @param playerPosition the {@link Position} of the {@link Player}
     * @return the direction hints
     */
    public String getDirectionHints(Position playerPosition) {
        StringBuilder sb = new StringBuilder();

        if (this.getX() > playerPosition.getX())
            sb.append("-> ");
        else if (this.getX() < playerPosition.getX())
            sb.append("<- ");

        if (this.getY() > playerPosition.getY())
            sb.append("v ");
        else if (this.getY() < playerPosition.getY())
            sb.append("^ ");

        return sb.toString();
    }
}

