package chess;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Pawn chess piece.
 * If on the white team:
 * Can move two tiles up or one tile up as long as this pawn has never moved before.
 * Can only move one tile up afterwards.
 * Can only capture going up once and either to the right or left once.
 * If on the black team:
 * Can move two tiles up or one tile down as long as this pawn has never moved before.
 * Can only move one tile down afterwards.
 * Can only capture going down once and either to the right or left once.
 * 
 * @author JC
 * @version 1.0
 */
public class Pawn extends Piece implements Serializable {
    private static final long serialVersionUID = 1L;
    private ImageIcon icon;
    private String color;
    private String type;
    
    /**
     * Constructor for the Pawn.
     * @param color the color of the Pawn corresponding to its team.
     */
    public Pawn(String color) {
        if (color.equals("black")) {
            icon = new ImageIcon("Black_Pawn.png");
        } else {
            icon = new ImageIcon("White_Pawn.png");
        }
        this.color = color;
        type = "Pawn";
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
