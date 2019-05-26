import java.util.*;

/**
 * Program that models a Tic-Tac-Toe game.
 * The user can play with a normal 3x3 board or a
 * custom board (size x size).
 *
 * @author Anthony Chavez (1998)
 * @version 2 April 2019
 */
public class TTT {
    public static void main(String[] args) {
        TTTBoard board = new TTTBoard();
        Scanner kb = new Scanner(System.in);
        String prompt = "";
        String player1 = "";
        String player2 = "";
        char token = ' ';
        char winner = ' ';
        String tokenDummy = "";
        char tokenAI = 'o';  //change AI's token here
        int remainMoves = 0;
        boolean gameOver = false;
        
        System.out.println("Let's play tic tac toe!!!");
        System.out.println("First enter in the size of the board you wish to use");
        System.out.println("and try to beat the computer by being the first to");
        System.out.println("match all of your tokens in a row either in a single");
        System.out.println("row, column, or diagonal.");
        System.out.println();
        
        System.out.println("What board size would you like to use?");
        System.out.println("CAUTION: Board size cannot be less than 1");
        System.out.print("Enter 3 for 3x3 or enter a number for size x size.: ");
        int size;
        boolean boardValid = false;
        try {
            while(!kb.hasNextInt()) {
                System.out.println("Invalid input");
                size = kb.nextInt();
            }
            size = kb.nextInt();
            if(size == 3) {
                board = new TTTBoard();
                remainMoves = board.size() * board.size();
            } else {
                board = new TTTBoard(size);
                remainMoves = board.size() * board.size();
            }
            boardValid = true;        
        } catch (IllegalArgumentException e) {
            boardValid = false;
            gameOver = true;
        } catch (InputMismatchException e) {
            boardValid = false;
            gameOver = true;
            System.out.println("Game will now end");
        }
        
        if(boardValid) {
            System.out.print("Would you like to go first? Enter Y for Yes or N for No: ");
            prompt = "";
            prompt = kb.nextLine();
            prompt = kb.nextLine();
            while(!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n")) {
                System.out.println("Invalid input! Please enter Y or N");
                System.out.print("Would you like to go first? Enter Y for Yes or N for No: ");
                prompt = kb.nextLine();
            }
        
            if(prompt.equalsIgnoreCase("y")) {
                System.out.println("You will go first");
                player1 = "Human";
                player2 = "Computer";
            } else {
                System.out.println("Computer will go first");
                player1 = "Computer";
                player2 = "Human";
            }
        }
        
        while(!gameOver) {
            int rowCoord = 0;
            int colCoord = 0;
            boolean valid = false;
            if(player1.equals("Human")) {
                while(!valid) {
                    valid = false;
                    try {
                        System.out.print("Please enter a row number 0-2: ");
                        while(!kb.hasNextInt()) {
                            System.out.print("Please enter a row number 0-2: ");
                            kb.nextLine();
                        }
                        rowCoord = kb.nextInt();
                        kb.nextLine();
                        System.out.print("Please enter a column number 0-2: ");
                        while(!kb.hasNextInt()) {
                            System.out.print("Please enter a column number 0-2: ");
                            kb.nextLine();
                        }
                        colCoord = kb.nextInt();
                        if(token == ' ') {
                            System.out.println("CAUTION: Only the first character will be your token");
                            System.out.print("Please enter a character to represent your token: ");
                            kb.nextLine();
                            while(!kb.hasNextLine()) {
                                System.out.println("CAUTION: Only the first character will be your token");
                                System.out.print("Please enter a character to represent your token: ");
                                kb.nextLine();
                            }
                            tokenDummy = kb.next();
                            token = tokenDummy.charAt(0);
                            if(token == tokenAI)
                                throw new IllegalArgumentException();
                        }
                        if(board.get(rowCoord,colCoord) == ' ') {
                            board.set(rowCoord,colCoord,token);
                            valid = true;
                        } else {
                            System.out.println();
                            System.out.println("(" + rowCoord + "," + colCoord + ") is already occupied.");
                            System.out.println("Please enter a vacant slot");
                        }
                    } catch(IndexOutOfBoundsException e) {
                        valid = false;
                    } catch(IllegalArgumentException e) {
                        System.out.println("Cannot have the same token (" + tokenAI + ") as the Computer");
                        token = ' ';
                        valid = false;
                    }
                }
                try {
                    winner = board.winner();
                    if(winner != ' ')
                        throw new IllegalArgumentException();
                    System.out.println();
                    System.out.println(board.toString());
                    TTTAI.move(board, tokenAI);
                    System.out.println();
                    System.out.println(board.toString());
                    winner = board.winner();
                    if(winner != ' ')
                        throw new IllegalArgumentException();
                    } catch (IllegalArgumentException e) {
                    
                        System.out.println();
                        System.out.println(board.toString());
                        if(board.winner() == ' ')
                            gameOver = true;
                        else
                            System.out.println(winner + " is the winner");
                        gameOver = true;
                }
            }
        
            else {
                try {
                    TTTAI.move(board, tokenAI);
                    remainMoves--;
                    System.out.println();
                    System.out.println(board.toString());
                    board.winner();
                    if(board.winner() != ' ')
                        valid = true;
                } catch(IllegalArgumentException e) {
                    gameOver = true;
                }
                if(remainMoves == 0)
                    valid = true;
                if(!valid) {
                    while(!valid) {
                        valid = false;
                        try {
                            System.out.print("Please enter a row number 0-2: ");
                            while(!kb.hasNextInt()) {
                                System.out.print("Please enter a row number 0-2: ");
                                kb.nextLine();
                            }
                            rowCoord = kb.nextInt();
                        
                            System.out.print("Please enter a column number 0-2: ");
                            while(!kb.hasNextInt()) {
                                System.out.print("Please enter a column number 0-2: ");
                                kb.nextLine();
                                kb.nextLine();
                            }
                            colCoord = kb.nextInt();
                            if(token == ' ') {
                                System.out.println("CAUTION: Only the first character will be your token");
                                System.out.print("Please enter a character to represent your token: ");
                                kb.nextLine();
                                while(!kb.hasNextLine()) {
                                    System.out.println("CAUTION: Only the first character will be your token");
                                    System.out.print("Please enter a character to represent your token: ");
                                    kb.nextLine();
                                }
                                tokenDummy = kb.next();
                                token = tokenDummy.charAt(0);
                                if(token == tokenAI)
                                    throw new IllegalArgumentException();
                            }
                            if(board.get(rowCoord,colCoord) == ' ') {
                                board.set(rowCoord,colCoord,token);
                                valid = true;
                                remainMoves--;
                            } else {
                                System.out.println();
                                System.out.println("(" + rowCoord + "," + colCoord + ") is already occupied.");
                                System.out.println("Please enter a vacant slot");
                            }
                    } catch(IndexOutOfBoundsException e) {
                        valid = false;
                    } catch(IllegalArgumentException e) {
                        System.out.println("Cannot have the same token (" + tokenAI + ") as the Computer");
                        token = ' ';
                        valid = false;
                    }
                    }
                }
                
                try {
                    winner = board.winner();
                    if(winner != ' ')
                        throw new IllegalArgumentException();
                    System.out.println();
                    System.out.println(board.toString());
                    if(winner != ' ')
                        throw new IllegalArgumentException();
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        if(winner == ' ')
                            gameOver = true;
                        else
                            System.out.println(winner + " is the winner");
                        gameOver = true;
                }
            }
        }
        if(winner == ' ')
            System.out.println("Draw");
    }      
}