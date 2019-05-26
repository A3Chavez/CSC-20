import java.util.*;

/**
 * Program that models a Deque collection
 * 
 *
 * @author Anthony Chavez (1998)
 * @version 3 May 2019
 */
 
public class Deque20<E> {
    private ListNode<E> front;  // Reference to the front of the list
    private ListNode<E> back;   // Reference to the back of the list
    private int numElems;

    public Deque20() {
        front = null;
        back = null;
        numElems = 0;
    }
    
    /**
     * Adds x to the front of the deque
     *
     * @param x is the element added to the front of the deque
     */
    public void push(E x) {
        ListNode<E> newNode = new ListNode();
        newNode.next = front;
        newNode.data = x;
        front = newNode; 
        numElems++;   
    }
    
    /**
     * Removes and returns the front of the deque
     *
     * @return front of the deque
     * @throws NoSuchElementException when the deque is empty
     */
    public E pop() {
        try {
            if(numElems == 0)
                throw new NoSuchElementException();
            ListNode<E> temp = new ListNode();
            temp = front;
            front = front.next; 
            numElems--;
            return temp.data;
        } catch(NoSuchElementException e) {
            //System.out.println("The deque is empty");
            throw new NoSuchElementException();
        }
    }
    
    /**
     * Adds x to the back of the deque
     *
     * @param x is the element added to the back of the deque
     */
    public void add(E x) {
        /*
        if(front == null) {
            ListNode<E> temp = new ListNode();
            temp.data = x;
            temp.next = front;
            front = temp;
        } else {
            ListNode<E> cur = new ListNode();
            ListNode<E> newNode = new ListNode();
            cur = front;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
            newNode.next = null;
            newNode.data = x;
        }
        numElems++;
        */
        if(front == null) {
            ListNode<E> temp = new ListNode();
            temp.data = x;
            temp.next = front;
            front = temp;
        } else {
            ListNode<E> tmp = new ListNode();
            ListNode<E> newNode = new ListNode();
            tmp = front;
            tmp = addLast(tmp);
            tmp.next = newNode;
            newNode.data = x;
            newNode.next = null;
        }    
        numElems++;
    }
    
    private ListNode addLast(ListNode cur) {
        if(cur.next == null) {
            return cur;
        } else
            cur = addLast(cur.next);
        return cur;
    }
    
    /**
     * Removes and returns the front of the deque
     *
     * @return front of the deque
     * @throws NoSuchElementException when deque is empty
     */ 
    public E remove() {
        try {
            if(numElems == 0)
                throw new NoSuchElementException();
            ListNode<E> temp = new ListNode();
            temp = front;
            front = front.next; 
            numElems--;
            return temp.data;
        } catch(NoSuchElementException e) {
            //System.out.println("The deque is empty");
            throw new NoSuchElementException();
        }
    }
    
    /**
     * Returns the front of the deque without removing it
     *
     * @return front of the deque
     * @throws NoSuchElementException when deque is empty
     */ 
    public E peek() {
        try {
            if(numElems == 0)
                throw new NoSuchElementException();
            return front.data;
        } catch(NoSuchElementException e) {
            //System.out.println("Cannot peek an empty deque");
            throw new NoSuchElementException();
        }
    }
    
    /**
     * Return true or false if the deque is empty
     *
     * @return true if the deque has no elements, otherwise false
     */
    public boolean isEmpty() {
        if(numElems == 0)
            return true;
        else
            return false;
    }
    
    /**
     * Returns the number of elements in the deque
     *
     * @return number of elements in the deque
     */
    public int size() {
        return numElems;
    }
}