import java.util.*;
import java.io.*;

/**
 * Program that reads tokens from file data.txt and prints the integer
 * tokens found in it to the screen in increasing order.
 *
 * @author Anthony Chavez (1998)
 * @version 21 Feb 2019
 */
public class FilterSort {
    /**
     * Allocates and returns an integer array twice the size of the one
     * supplied as a parameter.  The first half of the new array will
     * be a copy of the supplied array and the second half of the new
     * array will be zeros.
     *
     * @param arr the array to be copied
     * @return array twice the size of <tt>arr<>
     */
    public static int[] doubleArrayAndCopy(int[] arr) {
        int[] temp = new int[arr.length * 2];
        for(int r = 0; r < arr.length; r++)
            temp[r] = arr[r];
        return temp;
    }
    
    public static void main(String[] args) {
        int[] data = new int[8];
        String fileName = "data.txt"; //specify file name here
        Scanner file;
        try {
            file = new Scanner(new File(fileName));
        } catch (FileNotFoundException e){
            file = null;
        }
        if(file != null) {
            int i = 0;
            int zeroCount = 0; //counts zeros in the file
            while(file.hasNext()) {
                if(file.hasNextInt()) {
                    if(i == data.length) 
                        data = doubleArrayAndCopy(data);
                    data[i] = file.nextInt();
                    if(data[i] == 0)
                        zeroCount++;
                    i++;
                } else {
                    file.next();
                }
            }
            Arrays.sort(data);
            //System.out.println(Arrays.toString(data));
            for(int index = 0; index < data.length; index++) {
                if(data[index] != 0)
                    System.out.println(data[index]);
                else if(zeroCount > 0 && data[index] == 0) {
                    System.out.println(data[index]);
                    zeroCount--;
                }
            }
        } else {
            System.out.println("File not found");
        }
    }
}
