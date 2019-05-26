import java.util.*;
import java.math.*;

/**
 * Program that performs calculations on simple postfix mathematical
 * expressions and prints the result.  The program will terminate when 
 * Ctrl + D is entered.
 *
 * @author Anthony Chavez (1998)
 * @version 24 April 2019
 */

public class REPL {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque20<BigInteger> list = new Deque20<BigInteger>();
        System.out.println("Welcome");
        //boolean quit = false;
        boolean valid = true;
        String input = "";
        BigInteger result, operand_1, operand_2, tmp;
        //while(!quit) {   
            System.out.print("> "); 
            while(in.hasNextLine()) {
                try {
                    input = in.nextLine();
                    if(input.equals(""))
                        throw new NoSuchElementException();
                    if(input.equals(""))
                        throw new IllegalArgumentException();
                    result = new BigInteger("0");
                    operand_1 = new BigInteger("0");
                    operand_2 = new BigInteger("0");
                    boolean isNumberic = true;
                    Scanner tokenize = new Scanner(input);
                    while(tokenize.hasNext()) {
                        String token = tokenize.next();
                        if(token.equals("+")) {
                            operand_2 = list.pop();
                            operand_1 = list.pop();
                            result = add(operand_2, operand_1, result);
                            list.push(result);
                        } else if(token.equals("-")) {
                            operand_2 = list.pop();
                            operand_1 = list.pop();
                            result = sub(operand_2, operand_1, result);
                            list.push(result);
                        } else if(token.equals("*")) {
                            operand_2 = list.pop();
                            operand_1 = list.pop();
                            result = mul(operand_2, operand_1, result);
                            list.push(result);
                        } else if(token.equals("/")) {
                            operand_2 = list.pop();
                            operand_1 = list.pop();
                            result = div(operand_2, operand_1, result);
                            list.push(result);
                        } else if(token.equals("%")) {
                            operand_2 = list.pop();
                            operand_1 = list.pop();
                            result = mod(operand_2, operand_1, result);
                            list.push(result);
                        } else if(isNumber(token)) {
                            tmp = new BigInteger(token);
                            list.push(tmp);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                    System.out.println(list.pop());
                    System.out.print("> ");
                } catch(IllegalArgumentException e) {
                    Scanner end = new Scanner("");
                    in = end;
                } catch(NoSuchElementException e) {
                    System.out.println("ERROR");
                    System.out.print("> ");
                }
            }
        //}
        System.out.println("Goodbye");
    }
    
    private static BigInteger add(BigInteger operand_2, BigInteger operand_1, BigInteger result) {
        result = operand_2.add(operand_1);
        return result;
    }
    
    private static BigInteger sub(BigInteger operand_2, BigInteger operand_1, BigInteger result) {
        result = operand_1.subtract(operand_2);
        return result;
    }
    
    private static BigInteger mul(BigInteger operand_2, BigInteger operand_1, BigInteger result) {
        result = operand_2.multiply(operand_1);
        return result;
    }
    
    private static BigInteger div(BigInteger operand_2, BigInteger operand_1, BigInteger result) {
        try {
            result = operand_1.divide(operand_2);
        } catch(ArithmeticException e ) {
            throw new NoSuchElementException();
        }
        return result;
    }
    
    private static BigInteger mod(BigInteger operand_2, BigInteger operand_1, BigInteger result) {
        result = operand_2.remainder(operand_1);
        return result;
    }
    
    private static boolean isNumber(String s) { 
        for(int i = 0; i < s.length()-1; i++)
            if(Character.isDigit(s.charAt(i)) == false)
                throw new NoSuchElementException();
        return true;
        
        //return Character.isDigit(s.charAt(0));
    } 
}