package chess;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Knight chess piece. Can only move in an L shape.
 * 2 tiles horizontally then 1 tile vertically
 * OR
 * 2 tiles vertically then 1 tile horizontally.
 * 
 * @author JC
 * @version 1.0
 */
public class Knight extends Piece implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private ImageIcon icon;
    private String color;
    private String type;

    /**
     * Constructor for the Knight.
     * @param color the color of the Knight corresponding to its team.
     */
    public Knight(String color) {
        if (color.equals("black")) {
            icon = new ImageIcon("Black_Knight.png");
        } else {
            icon = new ImageIcon("White_Knight.png");
        }
        this.color = color;
        type = "Knight";
    }

    /**
     * Returns the image corresponding to its team.
     * @return the icon of the piece.
     */
    public ImageIcon getImage() {
        return icon;
    }

    /**
     * Returns the color corresponding to its team.
     * @return the color of the piece.
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the type of chess piece it is, which is a bishop.
     * @return the type of the piece.
     */
    public String pieceType() {
        return type;
    }
}
