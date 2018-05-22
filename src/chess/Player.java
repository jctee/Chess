package chess;

import java.io.Serializable;

/**
 * The Class Player represents a person playing the game of Chess.
 * A Player is given a color that determines whether the player
 * is on the black team or the white team.
 * A player can only move pieces of its corresponding color.
 */
public class Player implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String color;

    /**
     * Constructor for player, sets the color.
     * @param color of the player
     */
    public Player(String color) {
        this.color = color;
    }

    /**
     * Returns the color the player is currently assigned to.
     * @return the color of the player.
     */
    public String getColor() {
        return this.color;
    }

    
}
