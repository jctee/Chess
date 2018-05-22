package chess;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The class Square is represented by a JButton.
 * Each square is a JButton that contains a piece, 
 * an image of the piece it contains.
 * A square also has a color to allow board to create
 * a checkered pattern for the game of Chess.
 * 
 * @author JC
 * @version 1.0
 */
public class Square implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private JButton button;
    private ImageIcon img;
    protected Piece piece;

    /**
     * Constructor for square. Each square is created with a button.
     * SEts the background of the square to the color passed in as a
     * parameter and defaults the image in the square as null.
     * 
     * @param color color of the square
     */
    public Square(Color color) {
        button = new JButton();
        button.setBackground(color);
        button.setOpaque(true);
        img = null;
    }

    /**
     * checks to see if there is a piece in the square.
     * @return true if square contains a piece
     */
    public boolean hasPiece() {
        if (img != null) {
            return true;
        }
        return false;
    }

    /**
     * Gets the button that is being requested.
     * @return the button that is being requested.
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Gets the image that is in the square
     * @return the image that is in the square.
     */
    public ImageIcon getImage() {
        return img;
    }

    /**
     * Sets the image of the piece onto the square.
     * @param piece piece's image being set onto the square.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        img = piece.getImage();
        button.setIcon(img);
    }

    /**
     * gets the piece currently in the square.
     * @return the piece currently in the square.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * gets the color of the piece currently in the square.
     * @return the color of the piece currently in the square
     */
    public String getPColor() {
        if (hasPiece() == true) {
            return this.piece.getColor();
        }
        return null;
    }

    /**
     * sets the image of the square and updates the button's image as well.
     * @param img image of the piece in the square.
     */
    public void setImage(ImageIcon img) {
        this.img = img;
        button.setIcon(this.img);
    }
}
