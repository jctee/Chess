package chess;

/**
 * Chess has a 8x8 board that contains squares, some containg chess Pieces.
 * There are two players (black and white). Each player is given 16 
 * chess pieces, each type having different unique movements.
 * The game starts with the white player and alternates every turn.
 * A player can move his chess piece on an empty square or capture an
 * enemy team's chess piece if the move is valid(follows the rules of
 * that chess piece's unique move).
 * 
 * @author JC
 * @version 1.0
 */
public class Chess {

    /**
     * This is the driver for the Chess game.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Board();
    }
    
    
}

