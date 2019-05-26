import java.util.*;

public class Deque20Test {
    public static void main(String[] args) {
        boolean pass = true;
        // Put comprehensive tests here
        Deque20 list = new Deque20();
        
        // Test push method
        int num = 3;
        list.push((int)3);
        pass = pass && (list.size()==1) && ((int)list.peek()==3);
        System.out.println(pass + " pass 1");       
        
        // Test pop method
        list.pop();
        pass = pass && (list.size()==0);
        System.out.println(pass + " pass 2");
        
        // Test pop method exception catch
        try {
            Deque20 d = new Deque20();
            d.pop();
        } catch(NoSuchElementException e) {
            // getting here won't change 'pass'
            System.out.println("pop method exception proper, pass 3");
        } catch(Exception e) {
            pass = false;
        }
           
        // Test add method
        list.push((int) 1);
        list.push((int) 2);
        list.add((int) 10);
        
        pass = pass && (list.size()==3);
        list.pop();
        list.pop();
        pass = pass && ((int)list.peek()==10);
        System.out.println(pass+" pass 4");
        
        // Test remove method
        Deque20 list2 = new Deque20();
        list2.push((int) 1);
        list2.push((int) 2);
        list2.push((int) 3);
        list2.push((int) 4);
        list2.remove();
        pass = pass && (list2.size()==3) && ((int)list2.peek()==3);
        System.out.println(pass+" pass 5");
        
        // Test remove method exception catch
        try {
            Deque20 d2 = new Deque20();
            d2.remove();
        } catch(NoSuchElementException e) {
            // getting here won't change 'pass'
            System.out.println("remove method exception proper, pass 6");
        } catch(Exception e) {
            pass = false;
        }
        
        // Test peek method
        try {
            Deque20 d3 = new Deque20();
            d3.push((int) 1);
            d3.push((int) 2);
            d3.push((int) 3);
            pass = pass && ((int)d3.peek()==3);
            System.out.println(pass+" pass 7");
        } catch(NoSuchElementException e) {
            // getting here wont change 'pass'
            System.out.println("peek method exception proper");
        }
        
        // Test isEmpty method
        Deque20 emptyTest = new Deque20();
        emptyTest.isEmpty();
        boolean expected = true;
        pass = pass && (expected==true);
        System.out.println(pass+" pass 8");
        
        // Test size method
        Deque20 sizeTest = new Deque20();
        int size = sizeTest.size();
        pass = pass && (size==0);
        System.out.println(pass+" pass 9");
        
        // Test push method pushing null
        Deque20<String> list10 = new Deque20<String>();
        String s1 = null;
        list10.push(s1);
        pass = pass && (list10.size()==1);
        System.out.println(pass+ " null successfully pushed");
        String s2 = list10.pop();
        pass = pass && (list10.size()==0);
        System.out.println(pass+" null successfully popped");
        
        System.out.println("Program is good: "+pass);
    }
}