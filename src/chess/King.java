package chess;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * King chess piece. Can only move 1 tile, horizontally, vertically,
 * or diagonally.
 * 
 * @author JC
 * @version 1.0
 */
public class King extends Piece implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private ImageIcon icon;
    private String color;
    private String type;

    /**
     * Constructor for the King.
     * @param color the color of the King corresponding to its team.
     */
    public King(String color) {
        if (color.equals("black")) {
            icon = new ImageIcon("Black_King.png");
        } else {
            icon = new ImageIcon("White_King.png");
        }
        this.color = color;
        type = "King";
    }

    /**
     * Returns the image corresponding to its team.
     * @return icon of the piece
     */
    public ImageIcon getImage() {
        return icon;
    }
    
    /**
     * Returns the color corresponding to its team.
     * @return color of the piece
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Returns the type of chess piece it is, which is a bishop.
     * @return type of the piece
     */
    public String pieceType() {
        return type;
    }
    
}
