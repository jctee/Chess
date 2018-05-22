package chess;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Rook chess piece. Can only move horizontally or vertically, 
 * as long as there is nothing in its path. 
 * 
 * @author JC
 * @version 1.0
 */
public class Rook extends Piece implements Serializable {

    private static final long serialVersionUID = 1L;
    private ImageIcon icon;
    private String color;
    private String type;

    /**
     * Constructor for the Rook.
     * @param color the color of the Rook corresponding to its team.
     */
    public Rook(String color) {
        if (color.equals("black")) {
            icon = new ImageIcon("Black_Rook.png");
        } else {
            icon = new ImageIcon("White_Rook.png");
        }
        this.color = color;
        type = "Rook";
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
