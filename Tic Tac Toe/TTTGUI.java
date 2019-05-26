import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*; 
import java.util.*;

/**
 * This program models a graphical Tic Tac Toe game with a custom
 * board size of 4 x 4. 
 *
 * @author Anthony Chavez (1998)
 * @version 10 May 2019
 */

class TTTGUI { 
    // create a frame 
    static JFrame f; 
  
    // create a label 
    static JLabel l; 
  
    // declare button array
    static JButton b[][] = new JButton[4][4]; 
    
    // main function 
    public static void main(String args[]) { 
        // create a frame 
        f = new JFrame("Tic Tac Toe GUI"); 
        
        // setLayout
        f.setLayout(new GridLayout(5,5));

        // create buttons for AI movement
        for(int r = 0; r < 4; r++) {
            for(int c = 0; c < 4; c++) {
                b[r][c] = new JButton();
                b[r][c].setText(" ");
                b[r][c].addActionListener(new ButtonActionListener(r,c));
                f.add(b[r][c]);
            }
        }
        
        try { 
            // set look and feel 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } 
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        } 
  
        // create status line
        l = new JLabel("Your Turn"); 
  
        // create JPanel
        JPanel p = new JPanel(); 
    
        // status message line
        p.add(l,BorderLayout.SOUTH);       
        
        // set Background of panel 
        p.setBackground(Color.white); 
  
        // add panel to frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(p); 
        f.setSize(400, 400); 
        f.setResizable(false);
        f.show(); 
    } 
} 