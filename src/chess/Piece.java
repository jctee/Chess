package chess;

import javax.swing.ImageIcon;

/**
 * Abstract class for differentt types of chess pieces.
 * Every piece has and Image, a color, and a type.
 * Also has a first move boolean that denotes whether or not
 * it is the piece's first move(used for pawn).
 * 
 * @author JC
 * @version 1.0
 */
abstract class Piece {
    private ImageIcon icon;
    private String color;
    private String type;
    private boolean doneFirstMove = false;

    /**
     * Returns the image corresponding to its team.
     * @return icon of the piece.
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
     * @return type of the piece.
     */
    public String pieceType() {
        return type;
    }
    
    /**
     * checks to see if piece has moved at least once.
     * @return true if piece has moved at least once already.
     */
    public boolean isFirstMoveCompleted() {
        return doneFirstMove;
    }
    
    /**
     * sets doneFirstMove true when this function is called.
     */
    public void firstMoveComplete() {
        doneFirstMove = true;
    }
    
}
