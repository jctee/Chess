package chess;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The class Board represents a board that is constructed using a JFrame.
 * The frame is filled up with squares forming an 8x8, each containing
 * their own listener and some containing chess pieces.
 * This class also implements MouseListener which is used to determine which square
 * is being pressed to allow the user to select and move pieces.
 * This class also checks for valid movements that is implemented differently
 * for each type of chess piece.
 * 
 * @author JC
 * @version 1.0
 */
public class Board implements MouseListener, Serializable {

    private static final long serialVersionUID = 1L;
    private JFrame board;
    private Square[][] sqr;
    private ImageIcon tempImg = null;
    private int xval;
    private int yval;
    
    private Player black;
    private Player white;
    private Player curPlayer;
    
    private File file;

    /**
     * Constructor for the Board, initializes the GUI components
     * and sets the pieces in.
     */
    protected Board() {
        init();
        
    }

    /**
     * Initializes all the gui components.
     * Creates an 8x8 GridLayout and creates a 2d array.
     * Also adds in a JMenu that has can create a new game,
     * save a game, and load an old game.
     */
    public void init() {
        board = new JFrame();
        board.setTitle("Chess Board");
        board.setLayout(new GridLayout(8, 8));
        sqr = new Square[8][8];
        board.setSize(800, 800);
        drawBoard();
        setPieces();
        curPlayer = new Player("white");

        
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        file.add(newGame);
        file.add(save);
        file.add(load);
        

        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                newGame();
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                saveGameAction();
            }
        });

        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                loadGameAction();
            }
        });

        board.setJMenuBar(menuBar);
        board.setVisible(true);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a new game.
     */
    private void newGame() {
        init();
        
    }

    /**
     * Saves the current game.
     */
    private void saveGameAction() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                this.file = fileChooser.getSelectedFile();
            }
            FileOutputStream fs = new FileOutputStream(this.file.getPath());
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(board);
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads the old game that was previously saved.
     */
    private void loadGameAction() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                this.file = fileChooser.getSelectedFile();
            }
            board.dispose();
            FileInputStream fileStream = new FileInputStream(this.file.getPath());
            ObjectInputStream is = new ObjectInputStream(fileStream);
            JFrame restore = (JFrame) is.readObject();
            restore.setVisible(true);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates an 8x8 layout with alternating red and white squares.
     * Each square is given its own button and each button is given
     * a mouseListener used for moving pieces.
     */
    private void drawBoard() {
        sqr = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    sqr[i][j] = new Square(Color.RED);
                    board.add(sqr[i][j].getButton());
                } else {
                    sqr[i][j] = new Square(Color.WHITE);
                    board.add(sqr[i][j].getButton());
                }
                sqr[i][j].getButton().addMouseListener(this);
            }
        }
    }

    /**
     * Sets the pieces on the board and sets the first player as white.
     */
    private void setPieces() {
        setBlackPieces();
        setWhitePieces();
    }

    /**
     * sets the black chess pieces on the first and second row.
     */
    private void setBlackPieces() {
        black = new Player("black");
        sqr[0][0].setPiece(new Rook("black"));
        sqr[0][1].setPiece(new Knight("black"));
        sqr[0][2].setPiece(new Bishop("black"));
        sqr[0][3].setPiece(new King("black"));
        sqr[0][4].setPiece(new Queen("black"));
        sqr[0][5].setPiece(new Bishop("black"));
        sqr[0][6].setPiece(new Knight("black"));
        sqr[0][7].setPiece(new Rook("black"));
        for (int i = 0; i < 8; i++) {
            sqr[1][i].setPiece(new Pawn("black"));
        }
    }

    /**
     * sets the white chess pieces on the seventh and eight row.
     */
    private void setWhitePieces() {
        white = new Player("white");
        sqr[7][0].setPiece(new Rook("white"));
        sqr[7][1].setPiece(new Knight("white"));
        sqr[7][2].setPiece(new Bishop("white"));
        sqr[7][3].setPiece(new King("white"));
        sqr[7][4].setPiece(new Queen("white"));
        sqr[7][5].setPiece(new Bishop("white"));
        sqr[7][6].setPiece(new Knight("white"));
        sqr[7][7].setPiece(new Rook("white"));
        for (int i = 0; i < 8; i++) {
            sqr[6][i].setPiece(new Pawn("white"));
        }
    }

    /**
     * Locates the square that was clicked with the method getSource().
     * Lets user to pick up pieces and move them to either an empty square
     * or capture them if it's an opposing color's piece.
     * Doesn't let user to capture the same color pieces.
     */
    @Override
    public void mousePressed(MouseEvent ex) {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (ex.getSource() == sqr[i][j].getButton()) {
                    if (tempImg == null) {
                        choosePiece(i, j);
                    } else if (sqr[i][j].hasPiece() == true) {
                        if (sqr[i][j].getPColor().equals(sqr[xval][yval].getPColor())
                              || (!sqr[i][j].getPColor().equals(sqr[xval][yval].getPColor())
                                  && sqr[i][j].getPColor().equals(curPlayer.getColor()))) {
                            choosePiece(i, j);
                        } else {
                            move(i, j);
                        }
                    } else {
                        move(i, j);
                    }
                }
            }
        }
    }

    /**
     * Choose the piece clicked at coordinates (i,j).
     * @param xcoord represents the x-coord of piece clicked
     * @param ycoord represents the y-coord of piece clicked
     */
    private void choosePiece(int xcoord, int ycoord) {
        tempImg = sqr[xcoord][ycoord].getImage();
        xval = xcoord;
        yval = ycoord;
    }

    /**
     * Moves the piece and captures the opponent's piece.
     * @param newx represents the x-coord of piece clicked
     * @param newy represents the y-coord of piece clicked
     */
    private void move(int newx, int newy) {
        if (sqr[xval][yval].getPColor().equals(curPlayer.getColor())) {
            if (sqr[newx][newy].getButton() != sqr[xval][yval].getButton()) {
                if (sqr[newx][newy].getPColor() == null) {
                    moveValidation(newx, newy);
                } else if (!sqr[newx][newy].getPColor().equals(sqr[xval][yval].getPColor())) {
                    moveValidation(newx, newy);
                }
            }
        }
    }

    /**
     * Validates the specific type of chess piece and moves the piece
     * if the piece is valid.
     * @param newx represents the x-coord of piece clicked
     * @param newy represents the y-coord of piece clicked
     */
    private void moveValidation(int newx, int newy) {
        if (sqr[xval][yval].getPiece().pieceType().equals("Knight")) {
            if (validateKnight(xval, yval, newx, newy) == true) {
                moveAndRepaint( newx, newy);

            }
        } else if (sqr[xval][yval].getPiece().pieceType().equals("Bishop")) {
            if (validateBishop(xval, yval, newx, newy) == true) {
                moveAndRepaint( newx, newy);

            }
        } else if (sqr[xval][yval].getPiece().pieceType().equals("Rook")) {
            if (validateRook(xval, yval, newx, newy) == true) {
                moveAndRepaint( newx, newy);

            }
        } else if (sqr[xval][yval].getPiece().pieceType().equals("King")) {
            if (validateKing(xval, yval, newx, newy) == true) {
                moveAndRepaint( newx, newy);

            }
        } else if (sqr[xval][yval].getPiece().pieceType().equals("Queen")) {
            if (validateQueen(xval, yval, newx, newy) == true) {
                moveAndRepaint( newx, newy);

            }
        } else if (sqr[xval][yval].getPiece().pieceType().equals("Pawn")
                && sqr[xval][yval].getPColor().equals("white")) {
            if (validateWhitePawn(xval, yval, newx, newy) == true) {
                moveAndRepaint( newx, newy);

            }
        } else if (sqr[xval][yval].getPiece().pieceType().equals("Pawn")
                && sqr[xval][yval].getPColor().equals("black")) {
            if (validateBlackPawn(xval, yval, newx, newy) == true) {
                moveAndRepaint( newx, newy);
            }
        }
    }
    
    /**
     * sets piece onto square. sets new image on new square, and
     * nullifies image on previous square.
     * @param newx new x-coordinate
     * @param newy new y-coordinate
     */
    private void moveAndRepaint(int newx, int newy) {
        sqr[newx][newy].setPiece(sqr[xval][yval].getPiece());
        sqr[newx][newy].setImage(tempImg);
        sqr[xval][yval].setImage(null);
        tempImg = null;
    }

    /**
     * function to switch turns when it is called.
     */
    private void switchTurns() {
        if (curPlayer.getColor().equals("black")) {
            curPlayer = white;
        } else {
            curPlayer = black;
        }
    }

    /**
     * validates white pawn movement.
     * @param oldX x-coordinate the pawn is moving from
     * @param oldY y-coordinate the pawn is moving from
     * @param newX x-coordinate the pawn is moving to
     * @param newY y-coordinate the pawn is moving to
     * @return true if move is valid
     */
    private boolean validateWhitePawn(int oldX, int oldY, int newX, int newY) {
        if (sqr[newX][newY].hasPiece() == true) {
            if (Math.abs(newY - oldY) == 1 && newX - oldX == -1) {
                sqr[xval][yval].getPiece().firstMoveComplete();
                switchTurns();
                return true;
            }
        } else if (sqr[newX][newY].hasPiece() == false) {
            if (sqr[xval][yval].getPiece().isFirstMoveCompleted() == false) {
                if (newY - oldY == 0 && (newX - oldX == -2 || newX - oldX == -1)) {
                    sqr[xval][yval].getPiece().firstMoveComplete();
                    switchTurns();
                    return true;
                }
            } else {
                if (newY - oldY == 0 && newX - oldX == -1) {
                    sqr[xval][yval].getPiece().firstMoveComplete();
                    switchTurns();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * validate black pawn movement.
     * @param oldX x-coordinate the pawn is moving from
     * @param oldY y-coordinate the pawn is moving from
     * @param newX x-coordinate the pawn is moving to
     * @param newY y-coordinate the pawn is moving to
     * @return true if move is valid
     */
    private boolean validateBlackPawn(int oldX, int oldY, int newX, int newY) {
        if (sqr[newX][newY].hasPiece() == true) {
            if (Math.abs(newY - oldY) == 1 && newX - oldX == 1) {
                sqr[xval][yval].getPiece().firstMoveComplete();
                switchTurns();
                return true;
            }
        } else if (sqr[newX][newY].hasPiece() == false) {
            if (sqr[xval][yval].getPiece().isFirstMoveCompleted() == false) {
                if (newY - oldY == 0 && (newX - oldX == 2 || newX - oldX == 1)) {
                    sqr[xval][yval].getPiece().firstMoveComplete();
                    switchTurns();
                    return true;
                }
            } else {
                if (newY - oldY == 0 && newX - oldX == 1) {
                    sqr[xval][yval].getPiece().firstMoveComplete();
                    switchTurns();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * validates rook movement.
     * @param oldX x-coordinate the rook is moving from.
     * @param oldY y-coordinate the rook is moving from.
     * @param newX x-coordinate the rook is moving to.
     * @param newY y-coordinate the rook is moving to.
     * @return true if move is valid
     */
    private boolean validateRook(int oldX, int oldY, int newX, int newY) {
        if (oldX == newX || oldY == newY) {

            if (oldX == newX && newY - oldY > 0) {
                for (int i = 1; i < Math.abs(newY - oldY); i++) {
                    if (sqr[oldX][oldY + i].hasPiece() == true) {
                        return false;
                    }
                }
            } else if (oldX == newX && newY - oldY < 0) {
                for (int i = 1; i < Math.abs(newY - oldY); i++) {
                    if (sqr[oldX][oldY - i].hasPiece() == true) {
                        return false;
                    }
                }
            } else if (oldY == newY && newX - oldX > 0) {
                for (int i = 1; i < Math.abs(newX - oldX); i++) {
                    if (sqr[oldX + i][oldY].hasPiece() == true) {
                        return false;
                    }
                }
            } else if (oldY == newY && newX - oldX < 0) {
                for (int i = 1; i < Math.abs(newX - oldX); i++) {
                    if (sqr[oldX - i][oldY].hasPiece() == true) {
                        return false;
                    }
                }
            }

            switchTurns();
            return true;

        } else {
            return false;
        }
    }

    /**
     * Validates knight movement.
     * @param oldX x-coordinate the knight is moving from
     * @param oldY y-coordinate the knight is moving from
     * @param newX x-coordinate the knight is moving to
     * @param newY y-coordinate the knight is moving to
     * @return true if move is valid
     */
    private boolean validateKnight(int oldX, int oldY, int newX, int newY) {
        if (Math.abs(newY - oldY) == 2 && Math.abs(newX - oldX) == 1
                || Math.abs(newY - oldY) == 1 && Math.abs(newX - oldX) == 2) {
            switchTurns();
            return true;
        }
        return false;
    }

    /**
     * Validates bishop movement.
     * @param oldX x-coordinate the bishop is moving from
     * @param oldY y-coordinate the bishop is moving from
     * @param newX x-coordinate the bishop is moving to
     * @param newY y-coordinate the bishop is moving to
     * @return true if move is valid
     */
    private boolean validateBishop(int oldX, int oldY, int newX, int newY) {
        int deltaY = newY - oldY;
        int deltaX = newX - oldX;
        if (Math.abs(newY - oldY) == Math.abs(newX - oldX)) {
            if (deltaX > 0 && deltaY > 0) {
                for (int i = 1; i < Math.abs(deltaX); i++) {
                    if (sqr[oldX + i][oldY + i].hasPiece() == true) {
                        return false;
                    }
                }
            } else if (deltaX < 0 && deltaY > 0) {
                for (int i = 1; i < Math.abs(deltaX); i++) {
                    if (sqr[oldX - i][oldY + i].hasPiece() == true) {
                        return false;
                    }
                }
            } else if (deltaX > 0 && deltaY < 0) {
                for (int i = 1; i < Math.abs(deltaX); i++) {
                    if (sqr[oldX + i][oldY - i].hasPiece() == true) {
                        return false;
                    }
                }
            } else if (deltaX < 0 && deltaY < 0) {
                for (int i = 1; i < Math.abs(deltaX); i++) {
                    if (sqr[oldX - i][oldY - i].hasPiece() == true) {
                        return false;
                    }
                }
            }
            switchTurns();
            return true;
        }
        return false;
    }

    /**
     * Validates queen movement
     * @param oldX x-coordinate the queen is moving from.
     * @param oldY y-coordinate the queen is moving from.
     * @param newX x-coordinate the queen is moving to.
     * @param newY y-coordinate the queen is moving to.
     * @return true if move is valid
     */
    private boolean validateQueen(int oldX, int oldY, int newX, int newY) {
        if (validateRook(oldX, oldY, newX, newY) == true) {
            return true;
        } else if (validateBishop(oldX, oldY, newX, newY) == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * validates the king movement.
     * @param oldX x-coordinate the king is moving from
     * @param oldY y-coordinate the king is moving from
     * @param newX x-coordinate the king is moving to. 
     * @param newY y-coordinate the king is moving to. 
     * @return true if move is valid.
     */
    private boolean validateKing(int oldX, int oldY, int newX, int newY) {
        if ((Math.abs(newY - oldY) == 1 || newY - oldY == 0) 
                && ((Math.abs(newX - oldX) == 1) || newX - oldX == 0)) {
            switchTurns();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent ex) {
    }

    @Override
    public void mouseClicked(MouseEvent ex) {
    }

    @Override
    public void mouseEntered(MouseEvent ex) {

    }

    @Override
    public void mouseExited(MouseEvent ex) {

    }

}
