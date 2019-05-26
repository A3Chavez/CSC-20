import java.util.*;

/**
 * Program that models an AI which plays a Tic-Tac-Toe game
 * against a human player. If the AI can win in the next move,
 * the AI will place its token in the winning coordinate.  If 
 * the AI detects a potential winning move by the human player, 
 * the AI will place its token to block the winning move. If both
 * conditions aren't present, the AI makes a random move.
 *
 * @author Anthony Chavez (1998)
 * @version 2 April 2019
 */
public class TTTAI {
    public static void move(TTTBoard board, char who) {
        Random rand = new Random();
        int tokenCountAI = 0;
        int tokenCountHuman = 0;
        int winningRow = 0;
        int winningCol = 0;
        int row = 0;
        int col = 0;
        boolean boardFull = false;
        boolean nextRowWin = false;
        boolean nextColWin = false;
        boolean nextDiagWin1 = false;
        boolean nextDiagWin2 = false;
        boolean valid = false;
        boardFull = boardFull(board);
        if(board.winner() != ' ' || boardFull) {
            throw new IllegalArgumentException();
        }
        try {
            nextRowWin = nextRowWin(board, tokenCountAI, tokenCountHuman, who, winningRow, winningCol);
            if(nextRowWin)
                throw new IllegalArgumentException();
            nextColWin = nextColWin(board, tokenCountAI, tokenCountHuman, who, winningRow, winningCol);
            if(nextColWin)
                throw new IllegalArgumentException();
            nextDiagWin1 = nextDiagWin1(board, tokenCountAI, tokenCountHuman, who, winningRow, winningCol);
            if(nextDiagWin1)
                throw new IllegalArgumentException();
            nextDiagWin2 = nextDiagWin2(board, tokenCountAI, tokenCountHuman, who, winningRow, winningCol);
        } catch(IllegalArgumentException e) {
            
        }
        if(nextRowWin || nextColWin || nextDiagWin1 || nextDiagWin2) {
            
        } else {
            int attempts = 0;
            while(!valid) {
                row = rand.nextInt(board.size()-1);
                col = rand.nextInt(board.size()-1);
                if(board.get(row,col) == ' ') {
                    valid = true;
                    board.set(row, col, who);
                }
                attempts++;
                if(attempts == 20) {
                    try {
                        for(int r = 0; r < board.size(); r++) {
                            for(int c = 0; c < board.size(); c++) {
                                if(board.get(r,c) == ' ') {
                                    board.set(r, c, who);
                                    throw new IllegalArgumentException();
                                }
                            }
                        }
                    } catch(IllegalArgumentException e) {
                        valid = true;
                    }
                }
            }
        }
    }

    private static boolean nextRowWin(TTTBoard board, int tokenCountAI, int tokenCountHuman, char who, int winRow, int winCol) {
        for(int cases = 0; cases < 2; cases++) {
            for(int r = 0; r < board.size(); r++) {
                tokenCountAI = 0;
                tokenCountHuman = 0;
                for(int c = 0; c < board.size(); c++) {
                    if(board.get(r,c) != ' ') {
                        if(board.get(r,c) == who)
                            tokenCountAI++;
                        else
                            tokenCountHuman++;
                    }
                    if(tokenCountAI == board.size() - 1 && cases == 0) {
                        winRow = r;
                        for(int i = 0; i < board.size(); i++) {
                            board.get(r,i);
                            if(board.get(r,i) == ' ')
                                winCol = i;
                        }
                        if(board.get(winRow,winCol) == ' ') {
                            board.set(winRow,winCol,who);  
                            return true;
                        }
                    } else if(tokenCountHuman == board.size() - 1 && cases == 1) {
                        winRow = r;
                        for(int i = 0; i < board.size(); i++) {
                            board.get(r,i);
                            if(board.get(r,i) == ' ')
                                winCol = i;
                        }
                        if(board.get(winRow,winCol) == ' ') {
                            board.set(winRow,winCol,who);  
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean nextColWin(TTTBoard board, int tokenCountAI, int tokenCountHuman, char who, int winRow, int winCol) {
        for(int cases = 0; cases < 2; cases++) {
            for(int c = 0; c < board.size(); c++) {
                tokenCountAI = 0;
                tokenCountHuman = 0;
                for(int r = 0; r < board.size(); r++) {
                    if(board.get(r,c) != ' ') {
                        if(board.get(r,c) == who)
                            tokenCountAI++;
                        else
                            tokenCountHuman++;
                    }
                    if(tokenCountAI == board.size() - 1 && cases == 0) {
                        winCol = c;
                        for(int i = 0; i < board.size(); i++) {
                            board.get(i,c);
                            if(board.get(i,c) == ' ')
                                winRow = i;
                        }
                        if(board.get(winRow,winCol) == ' ') {
                            board.set(winRow,winCol,who); 
                            return true;
                        }
                    } else if(tokenCountHuman == board.size() - 1 && cases == 1) {
                        winCol = c;
                        for(int i = 0; i < board.size(); i++) {
                            board.get(i,c);
                            if(board.get(i,c) == ' ')
                                winRow = i;
                        }
                        if(board.get(winRow,winCol) == ' ') {
                            board.set(winRow,winCol,who); 
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean nextDiagWin1(TTTBoard board, int tokenCountAI, int tokenCountHuman, char who, int winRow, int winCol) {
        for(int cases = 0; cases < 2; cases++) {
            tokenCountAI = 0;
            tokenCountHuman = 0;
            for(int r = 0; r < board.size(); r++) {
                int c = r;
                if(board.get(r,c) != ' ') {
                    if(board.get(r,c) == who)
                        tokenCountAI++;
                    else
                        tokenCountHuman++;
                }
                if(tokenCountAI == board.size() - 1 && cases == 0) {
                    for(int i = 0; i < board.size(); i++) {
                        int j = i;
                        board.get(i,j);
                        if(board.get(i,j) == ' ') {
                            winRow = i;
                            winCol = j;
                        }
                    }
                    if(board.get(winRow,winCol) == ' ') {
                        board.set(winRow,winCol,who);
                        return true;
                    }
                } else if(tokenCountHuman == board.size() - 1 && cases == 1) {
                    for(int i = 0; i < board.size(); i++) {
                        int j = i;
                        board.get(i,j);
                        if(board.get(i,j) == ' ') {
                            winRow = i;
                            winCol = j;
                        }
                    }
                    if(board.get(winRow,winCol) == ' ') {
                        board.set(winRow,winCol,who);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean nextDiagWin2(TTTBoard board, int tokenCountAI, int tokenCountHuman, char who, int winRow, int winCol) {
        for(int cases = 0; cases < 2; cases++) {
            tokenCountAI = 0;
            tokenCountHuman = 0;
            int c = board.size() - 1;
            for(int r = 0; r < board.size(); r++) {
                if(board.get(r,c) != ' ') {
                    if(board.get(r,c) == who)
                        tokenCountAI++;
                    else
                        tokenCountHuman++;
                }
                if(tokenCountAI == board.size() - 1 && cases == 0) {
                    int j = board.size() - 1;
                    for(int i = 0; i < board.size(); i++) {
                        board.get(i,j);
                        if(board.get(i,j) == ' ') {
                            winRow = i;
                            winCol = j;
                        }
                        j--;
                    }
                    if(board.get(winRow,winCol) == ' ') {
                        board.set(winRow,winCol,who);
                        return true;
                    }
                } else if(tokenCountHuman == board.size() - 1 && cases == 1) {
                    int j = board.size() - 1;
                    for(int i = 0; i < board.size(); i++) {
                        board.get(i,j);
                        if(board.get(i,j) == ' ') {
                            winRow = i;
                            winCol = j;
                        }
                        j--;
                    }
                    if(board.get(winRow,winCol) == ' ') {
                        board.set(winRow,winCol,who);
                        return true;
                    }
                }
            c--;
            }
        }
        return false;
    }
    
    private static boolean boardFull(TTTBoard board) {
        int count = 0;
        for(int r = 0; r < board.size(); r++)
            for(int c = 0; c < board.size(); c++) {
                if(board.get(r,c) != ' ')
                    count++;
                if(count == board.size() * board.size())
                    return true;
            }
        return false;
    }
}