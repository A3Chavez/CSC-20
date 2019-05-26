import java.util.*;

/**
 * Program that models a Tic-Tac-Toe game board.
 * The user can play with a normal 3x3 board or a
 * custom board (size x size).
 *
 * @author Anthony Chavez (1998)
 * @version 11 March 2019
 */
public class TTTBoard {
    
    public static final int DEFAULT_SIZE = 3;
    private char[][] board;
    
    /**
     * Creates a 3 x 3 Tic-Tac-Toe game board.
     */
    public TTTBoard() {
        board = new char[DEFAULT_SIZE][DEFAULT_SIZE];
        board = fillArray(board);
    }
    
    /**
     * Creates a custom size x size Tic-Tac-Toe game board.
     * 
     * @throws IllegalArgumentException if size is less than 1.
     * @param size is the desired size x size Tic-Tac-Toe game board.
     */
    public TTTBoard(int size) {
        try {
            if (size < 1)
                throw new IllegalArgumentException();
            board = new char[size][size];
            board = fillArray(board);
        } catch (IllegalArgumentException e) {
            System.out.println("invalid size");
            System.out.println("Game will now end");
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Fills the array with single character ' '.
     * 
     * @param board is the initial board constructed
     *       in constructor.
     * @return board filled with ' '
     */
    public char[][] fillArray(char[][] board) {
        for(int r = 0; r < this.board.length; r++)
            for(int c = 0; c < this.board.length; c++)
                board[r][c] = ' ';
        return board;
    }
    
    /**
     * Returns the character that is at the given position
     * (r,c) or a space character ' ' if no character is at
     * that position.
     * 
     * @throws IndexOutOfBoundsException if either r is less
     *      than 0 or c is less than 0.
     * @param r the row number of the board.
     * @param c the column number of the board.
     * @return char at board[r][c], or ' ' if no char exists
     */
    public char get(int r, int c) {
        try {
            if(r < 0 || c < 0)
                throw new IndexOutOfBoundsException();
            if(this.board[r][c] != ' ')
                return (char)this.board[r][c];
            else
                return ' ';
        } catch (IndexOutOfBoundsException e) {
            System.out.println("invalid coordinate");
        }
        return ' ';
    }
    
    /**
     * Sets the coordinate r,c to the given character.
     * If a character already exists at the coordinate,
     * the character is overwritten with the new character.
     * 
     * @throws IndexOutOfBoundsException if either r is less
     *      than 0, c is less than 0, r is greater than board.length,
     *      or c is greater than board.length.
     * @param r the row number of the board.
     * @param c the column number of the board.
     * @param ch the character to be placed in coordinate r,c.
     */
    public void set(int r, int c, char ch) {
        try {
            if(r < 0 || c < 0 || r > this.board.length || c > this.board[0].length)
                throw new IndexOutOfBoundsException();
            this.board[r][c] = ch;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("invalid coordinate");
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * Returns the size of the Tic-Tac-Toe game board.
     *
     * @return size of the game board.
     */
    public int size() {
        return this.board.length;
    }
    
    /**
     * Returns the winner of the game.
     * String "check" is filled with chars from the 
     * board. Character "charStart" gets the 
     * character to check for as the winner.  This
     * character is copied repeatedly in String charWinner
     * for the length of an appropriate win for the board 
     * size. If check equals charWinner, the winning 
     * character is returned. Otherwise a single space
     * character is returned.
     *
     * @return winning char or ' ' if no winning char
     */
    public char winner() {   
        String check = "";
        String charWinner = "";
        char charStart;
        for(int cases = 0; cases < 4; cases++) {
            //Row wins
            if(cases == 0) {
                for(int r = 0; r < this.board.length; r++) {
                    check = "";
                    charStart = get(r,0);
                    charWinner = "";
                    for(int i = 0; i < this.board.length; i++)
                        charWinner += charStart;
                    for(int c = 0; c < this.board[r].length; c++) {
                        check += this.board[r][c];
                    }
                    if(charStart != ' ')
                        if(check.equals(charWinner))
                            return charStart;
                }
            }
            
            //Column wins
            else if(cases == 1) {
                for(int c = 0; c < this.board.length; c++) {
                    check = "";
                    charStart = get(0,c);
                    charWinner = "";
                    for(int i = 0; i < this.board.length; i++)
                        charWinner += charStart;
                    for(int r = 0; r < this.board[c].length; r++) {
                        check += this.board[r][c];
                    }
                    if(charStart != ' ')
                        if(check.equals(charWinner))
                            return charStart;
                }
            }
            
            //Diagonal win from top left corner to bottom right corner
            else if(cases == 2) {
                check = "";
                charWinner = "";
                charStart = get(0,0);
                for(int i = 0; i < this.board.length; i++)
                        charWinner += charStart;
                for(int r = 0; r < this.board.length; r++) {
                    int c = r;
                    check += this.board[r][c];
                }
                if(charStart != ' ')
                    if(check.equals(charWinner))
                        return charStart;
            }
            
            //Diagonal win from top right corner to bottom left corner
            else if(cases == 3) {
                int c = this.board.length -1;
                check = "";
                charWinner = "";
                charStart = get(0, this.board.length-1);
                for(int i = 0; i < this.board.length; i++)
                        charWinner += charStart;
                for(int r = 0; r < this.board.length; r++) {
                    check += this.board[r][c];
                    c--;
                }
                if(charStart != ' ')
                    if(check.equals(charWinner))
                        return charStart;
            }
        }
            return ' ';
    } 
    
    /**
     * Returns a string representation of the Tic-Tac-Toe
     * game board.
     *
     * @return String printed out game board. 
     */
    public String toString() {
        String boardPrinted = "";
        for(int r = 0; r < this.board.length; r++) {
            for(int c = 0; c < this.board[r].length; c++) {
                if(c < this.board.length-1)
                    boardPrinted += this.board[r][c] + "|";
                else
                    boardPrinted += this.board[r][c];
            }
            boardPrinted += "\n";
            if(r < board.length-1) {
                for(int dashes = 0; dashes < this.board.length; dashes++) {
                    if(dashes < this.board.length-1)
                        boardPrinted += "-+";
                    else
                        boardPrinted += "-\n";
                }
            }
        }
        return boardPrinted;
    }
}