public class TTTBoardTester {

    public static void main(String[] args) {
        // Code passes unless we find a problem
        boolean pass = true;
        
        // Test existence of class constant
        pass = pass && (TTTBoard.DEFAULT_SIZE == 3);
        System.out.println(pass); //pass 1
        
        // Test default board creation, legal set/get, toString, winners
        TTTBoard b = new TTTBoard();
        pass = pass && (b.winner()==' ');
        System.out.println(pass); //pass 2
        b.set(0,2,'X');
        b.set(1,1,'X');
        b.set(2,0,'X');
        b.set(2,2,'X');
        pass = pass && (b.get(2,2)=='X');
        System.out.println(pass); //pass 3
        pass = pass && (b.get(2,1)==' ');
        System.out.println(pass);  //pass 4
        String expected = " | |X\n-+-+-\n |X| \n-+-+-\nX| |X"; //Question: does the space in the string read a concatenation
        pass = pass && (b.toString().indexOf(expected)==0);
        System.out.println(pass); //pass 5  //pass becomes false here
        pass = pass && (b.winner()=='X');
        System.out.println(pass); //pass 6
        b.set(1,1,' ');
        b.set(1,2,'X');
        expected = " | |X\n-+-+-\n | |X\n-+-+-\nX| |X";
        pass = pass && (b.toString().indexOf(expected)==0);
        System.out.println(pass); //pass 7
        pass = pass && (b.winner()=='X');
        System.out.println(pass); //pass 8
        b.set(2,1,'X');
        b.set(1,2,' ');
        expected = " | |X\n-+-+-\n | | \n-+-+-\nX|X|X";
        pass = pass && (b.toString().indexOf(expected)==0);
        System.out.println(pass); //pass 9
        pass = pass && (b.winner()=='X');
        System.out.println(pass); //pass 10

        // Test bigger board creation, toString, size, not winner
        b = new TTTBoard(4);
        b.set(0,0,'X');
        b.set(1,1,'X');
        b.set(2,2,'X');
        b.set(0,2,'X');
        b.set(2,0,'X');
        b.set(1,2,'X');
        b.set(2,1,'X');
        b.set(1,3,'X');
        b.set(3,0,'X');
        b.set(0,1,'X');
        expected = "X|X|X| \n-+-+-+-\n |X|X|X\n-+-+-+-\nX|X|X| \n-+-+-+-\nX| | | ";
        pass = pass && (b.toString().indexOf(expected)==0);
        System.out.println(pass); //pass 11
        pass = pass && (b.winner()==' '); 
        System.out.println(pass); //pass 12
        pass = pass && (b.size()==4);
        System.out.println(pass); //pass 13
        // Make it a winner
        b.set(3,3,'X');
         pass = pass && (b.winner()=='X');
         System.out.println(pass); //pass 14
       
        // Test smaller board creation, toString, not winner
        b = new TTTBoard(2);
        expected = " | \n-+-\n | ";
        pass = pass && (b.toString().indexOf(expected)==0);
        System.out.println(pass); //pass 15
        pass = pass && (b.winner()==' ');
        System.out.println(pass); //pass 16
        
        // Test exceptions
        try {
            b = new TTTBoard(0);
            pass = false;        // Exception in constructor will bypass this
        }
        catch(IllegalArgumentException e) {
            // Geting here won't change 'pass'
        }
        catch(Exception e) {
            pass = false;
        }
        
        // Print final result
        System.out.println("program is "+pass);
    }

}