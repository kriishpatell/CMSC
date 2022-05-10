/* ***********************************************************************
 * Project5.java
 * Krish Patel
 * Project 5
 * CMSC 256 Semester 2
 * Creates a binary expression tree to evaluate through inorder traversal
 * ***********************************************************************/

package Projects.Project5;

import java.util.Stack;
import java.util.Arrays;
import java.util.List;

import bridges.connect.Bridges;
import bridges.base.BinTreeElement;

public class Project5 {

    public static BinTreeElement<String> buildParseTree(String expression) { // return value of root node
        if (expression == null || expression.length() == 0) {
            throw new IllegalArgumentException();
        }

        List<String> tokens = Arrays.asList(expression.split("\\s+"));
        Stack<BinTreeElement<String>> treeStack = new Stack<>();
        BinTreeElement<String> tree = new BinTreeElement<>();
        BinTreeElement<String> curr = tree;

        for(String token : tokens){
            if (token.equals("(")) {
                BinTreeElement<String> newNode = new BinTreeElement<>();
                treeStack.push(curr);
                curr.setLeft(newNode);
                curr = curr.getLeft();
            } else if (isOperator(token)) { 
                BinTreeElement<String> newNode = new BinTreeElement<>();
                curr.setLabel(token);
                curr.setValue(token);
                curr.setRight(newNode);
                treeStack.push(curr);
                curr = curr.getRight();
            } else if (isOperand(token)) {
                curr.setLabel(token);
                curr.setValue(token);
                curr = treeStack.pop();
            } else if (token.equals(")")) {
                if(!treeStack.isEmpty()){
                    curr = treeStack.pop();
                }
            } else {
                throw new IllegalArgumentException();   
            }
        }
        
        return curr;
    }

    public static double evaluate(bridges.base.BinTreeElement<String> tree) {
        if (tree == null) {
            return 0;
        } else if (tree.getRight() == null && tree.getLeft() == null){
            return Double.parseDouble(tree.getLabel());
        }
        
        double left = evaluate(tree.getLeft());
        double right = evaluate(tree.getRight());

        if(tree.getLabel().equals("+")){
            return left + right;
        } else if(tree.getLabel().equals("-")){
            return left - right;
        } else if(tree.getLabel().equals("*")){
            return left * right;
        } else if(tree.getLabel().equals("%")){
            return left % right;
        } else if(tree.getLabel().equals("/")){
            if(right == 0){
                throw new ArithmeticException("Unable to divide by 0");
            }
            return left / right;
        } 
        return 0.0;
    }

    public static String getEquation(bridges.base.BinTreeElement<String> tree) { 
        String eq = "";
        if(tree.getLeft() == null && tree.getRight() == null){
            return tree.getLabel();
        }
        eq = eq + "( " + getEquation(tree.getLeft()) + " " + tree.getLabel() + " " + getEquation(tree.getRight()) + " )";
        return eq;
    }

    public static boolean isOperator(String string) {
        return (string.equals("+")
                || string.equals("-")
                || string.equals("*")
                || string.equals("/")
                || string.equals("%"));
    }

    public static boolean isOperand(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args){
        String ex1 = "( ( 7 + 3 ) * ( 5 - 2 ) )";
        BinTreeElement<String> parseTree1 = buildParseTree(ex1);
        double answer1 = evaluate(parseTree1);
        System.out.println(answer1);
        System.out.println(getEquation(parseTree1));
    
        String ex2 = "( ( 10 + 5 ) * 3 )";
        BinTreeElement<String>  parseTree2 = buildParseTree(ex2);
        double answer2 = evaluate(parseTree2);
        System.out.println(answer2);
        System.out.println(getEquation(parseTree2));
    
        String ex3 = "( ( ( ( ( 2 * 12 ) / 6 ) + 3 ) - 17 ) + ( 2 * 0 ) )";
        BinTreeElement<String>  parseTree3 = buildParseTree(ex3);
        double answer3 = evaluate(parseTree3);
        System.out.println(answer3);
        System.out.println(getEquation(parseTree3));
    
        String ex4 = "( 3 + ( 4 * 5 ) )";
        BinTreeElement<String>  parseTree4 = buildParseTree(ex4);
        double answer4 = evaluate(parseTree4);
        System.out.println(answer4);
        System.out.println(getEquation(parseTree4));
    
        /* Initialize a Bridges connection */
        Bridges bridges = new Bridges(5,  "kriishpatell", "1635347605758");
    
        /* Set an assignment title */
        bridges.setTitle("Arithmetic Parse Tree Project - Krish Patel");
        bridges.setDescription("CMSC 256, Spring 2022");
    
        try {
            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree1);
            /* Visualize the Array */
            bridges.visualize();

            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree2);
            /* Visualize the Array */
            bridges.visualize();
    
            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree3);
            /* Visualize the Array */
            bridges.visualize();
    
            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree4);
            /* Visualize the Array */
            bridges.visualize();
        }
        catch(Exception ex){
            System.out.println("Error connecting to Bridges server.");
        }
    }
}