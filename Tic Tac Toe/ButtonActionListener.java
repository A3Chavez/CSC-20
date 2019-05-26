import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*; 

/**
 * This program implements the logic for the Tic Tac Toe game:
 * Setting buttons to X when the user clicks a button on their turn.
 * Setting buttons to O when AI makes a move.
 * Checking for winning moves and updating the status message line 
 * respective to who wins or if there was a tie.
 *
 * @author Anthony Chavez (1998)
 * @version 10 May 2019
 */

public class ButtonActionListener implements ActionListener {
    private static TTTBoard theBoard = new TTTBoard(4);
    private char user = 'X';
    private char computer = 'O';
    private static int remainMoves = 16;
    private boolean end = false;
    private int row;
    private int col;

    /**
     * Creates a button action listener for a JButton.
     *
     * @param row of the JButton on the board
     * @param col of the JButton on the board
     */
    public ButtonActionListener(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Performs all the logic for the game:
     * Setting player moves on the GUI board
     * Displaying appropriate JLabel for winning
     * conditions.
     *
     * @param event is the information about what button was activated.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();   // reference to button
        
        button.setText("X");
        theBoard.set(row, col, user);       // set virtual board with user move
   
        // check winner, pass 1
        char winner = theBoard.winner();
        if(winner == user) {
            end = true;
            TTTGUI.l.setText("You Won!");
        } if(winner == computer && !end) {
            end = true;
            TTTGUI.l.setText("Sorry, the computer won!");
        } if(remainMoves == 0 && !end) {
            end = true;
            TTTGUI.l.setText("It's a draw!");
        }
        
        if(!end) {
            TTTBoard before = new TTTBoard(4);
            before = copyBoard(before, theBoard);       // make a copy of current board
            TTTAI.move(theBoard, computer);
            compareBoards(before, theBoard);
            remainMoves = remainMoves - 2;
        
           // System.out.println(remainMoves);
            //System.out.print(theBoard.toString());
        
            // check for winner or no remaining moves left pass 2
            winner = theBoard.winner();
            if(winner == user) {
                end = true;
                TTTGUI.l.setText("You Won!");
            } if(winner == computer && !end) {
                end = true;
                TTTGUI.l.setText("Sorry, the computer won!");
            } if(remainMoves == 0 && !end) {
                end = true;
                TTTGUI.l.setText("It's a draw!");
            }
        }
    }
    
    // make a copy of the current game board
    private TTTBoard copyBoard(TTTBoard copy, TTTBoard current) {
        for(int r = 0; r < 4; r++) {
            for(int c = 0; c < 4; c++) {
                char token = current.get(r,c);
                copy.set(r, c, token);
            }
        }
        return copy;
    }
    
    // compare two TTTBoards: before and after; AI move detection
    private void compareBoards(TTTBoard before, TTTBoard after) {
        for(int r = 0; r < 4; r++) {
            for(int c = 0; c < 4; c++) {
                if(before.get(r,c) == ' ' && after.get(r,c) == 'O') {
                    //System.out.println(row+","+col);
                    TTTGUI.b[r][c].setText("O");
                }
            }
        }
    }
}