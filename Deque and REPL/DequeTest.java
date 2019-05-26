import java.util.NoSuchElementException;

public class DequeTest {

    public static void main(String[] args) {
        boolean pass = true;
        Deque20<Integer> d = new Deque20<>();

        try { d.peek(); pass=false; }
        catch(NoSuchElementException e) { }
        catch(Exception e) { pass=false; }

        try { d.pop(); pass=false; }
        catch(NoSuchElementException e) { }
        catch(Exception e) { pass=false; }

        try { d.remove(); pass=false; }
        catch(NoSuchElementException e) { }
        catch(Exception e) { pass=false; }

        d.push(1); d.push(2); d.push(3);
        pass = pass && !d.isEmpty() && (d.size()==3) && (d.peek()==3) && (d.pop()==3);
        System.out.println(pass+"1");
        pass = pass && !d.isEmpty() && (d.size()==2) && (d.peek()==2) && (d.pop()==2);
        System.out.println(pass+"2");
        pass = pass && !d.isEmpty() && (d.size()==1) && (d.peek()==1) && (d.pop()==1);
        System.out.println(pass+"3");
        pass = pass && d.isEmpty() && (d.size()==0);
        System.out.println(pass+"4");

        d.add(1); d.add(2); d.add(3);
        pass = pass && !d.isEmpty() && (d.size()==3) && (d.peek()==1) && (d.remove()==1);
        System.out.println(pass+"5");
        pass = pass && !d.isEmpty() && (d.size()==2) && (d.peek()==2) && (d.remove()==2);
        System.out.println(pass+"6");
        pass = pass && !d.isEmpty() && (d.size()==1) && (d.peek()==3) && (d.remove()==3);
        System.out.println(pass+"7");
        pass = pass && d.isEmpty() && (d.size()==0);
        System.out.println(pass+"8");

        d.add(1); d.push(2); d.add(3);
        pass = pass && !d.isEmpty() && (d.size()==3) && (d.peek()==2) && (d.remove()==2);
        System.out.println(pass+"9");
        pass = pass && !d.isEmpty() && (d.size()==2) && (d.peek()==1) && (d.pop()==1);
        System.out.println(pass+"10");
        pass = pass && !d.isEmpty() && (d.size()==1) && (d.peek()==3) && (d.remove()==3);
        System.out.println(pass+"11");
        pass = pass && d.isEmpty() && (d.size()==0);
        System.out.println(pass+"12");
        
        d.add(null);
        pass = pass && !d.isEmpty() && (d.size()==1) && (d.peek()==null) && (d.remove()==null);
        System.out.println(pass+"13");

        try { d.peek(); pass=false; }
        catch(NoSuchElementException e) { }
        catch(Exception e) { pass=false; }

        try { d.pop(); pass=false; }
        catch(NoSuchElementException e) { }
        catch(Exception e) { pass=false; }

        try { d.remove(); pass=false; }
        catch(NoSuchElementException e) { }
        catch(Exception e) { pass=false; }

        System.out.println(pass);
    }

}