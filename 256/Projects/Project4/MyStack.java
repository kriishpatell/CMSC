/* *****************************************************************************************
 * MyStack.java
 * Krish Patel
 * Project 4
 * CMSC 256 Semester 2
 * File takes an HTML format file and checks whether the format of the file is valid or not
 * *****************************************************************************************/

package Projects.Project4;

import java.io.FileNotFoundException;
import java.util.EmptyStackException;

import bridges.base.SLelement;
import java.io.File;
import java.util.Scanner;

public class MyStack<E> implements StackInterface<E> {
    
    public static void main(String[] args) throws FileNotFoundException{
        File goodFile = new File("1goodeasypage.html");
        System.out.println(isBalanced(goodFile));
        File badFile =  new File("2badeasypage.html");
        System.out.println(isBalanced(badFile));
    }
    
    private SLelement<E> topOfStack;

    public MyStack() {
        clear();
    }

    public void push(E newEntry) {
        if(newEntry == null){
            throw new IllegalArgumentException();
        }
        SLelement<E> newNode = new SLelement<E>(newEntry, topOfStack);
        topOfStack = newNode;
    }

    public E pop() {
        E top = null;
        if(topOfStack == null){
            throw new EmptyStackException();
        } else {
            assert (topOfStack != null);
            top = topOfStack.getValue();
            topOfStack = topOfStack.getNext();
        }
        return top;
    }

    public E peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        } 
        return topOfStack.getValue();
    }

    public boolean isEmpty() {
        return topOfStack == null;
    }

    public void clear() {
        topOfStack = null;
    }

    public static boolean isBalanced(File webpage) throws FileNotFoundException {
        Scanner scanner = new Scanner(webpage); // create Scanner to read file
        MyStack<String> newStack = new MyStack<String>(); // create stack for the HTML tags to be added to

        while(scanner.hasNextLine()){ 
            String curr = scanner.nextLine(); // read line
            String openingDelimiter = "";
            String closingDelimiter = "";
            String last = "";

            for(int i = 0; i < curr.length(); i++){
                if(curr.charAt(i) == '<' && curr.charAt(i+1) != '/'){ // check if line is opening delimiter
                    for(int j = i+1; j < curr.length() && curr.charAt(j) != '>'; j++){ // read each character for the HTML tag
                        openingDelimiter += curr.charAt(j);
                    }
                }

                if(!openingDelimiter.equals("")){
                    newStack.push(openingDelimiter); // push the opening delimiter onto the stack
                    openingDelimiter = ""; // clear/reset value of opening delimiter 
                }

                if(curr.charAt(i) == '<' && curr.charAt(i+1) == '/'){ // check for closing delmiimter 
                    for(int j = i+2; j < curr.length() && curr.charAt(j) != '>'; j++){ // read each character for HTML tag
                        closingDelimiter += curr.charAt(j);
                    }

                    // if stack is not empty, file is not balance, this part of code checks for corresponding tags and removes them from the stack if true
                    if(!newStack.isEmpty()){ 
                        last = newStack.peek();
                    }
                    if(newStack.isEmpty() || !last.equals(closingDelimiter)){ 
                        return false; 
                    } else {
                        newStack.pop();
                    }
                }       
            }
        }
        return newStack.isEmpty(); // once the stack is empty after passing through each line, each tag has a matching tag, meaning file is balanced and will return true
    }
}
