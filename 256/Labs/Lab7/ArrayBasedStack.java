package Labs.Lab7;

import java.util.Arrays; 

public class ArrayBasedStack<T> implements StackInterface<T> {
    private T[] data;
    private int topOfStack;
    private static final int INITIAL_CAPACITY = 5;

    public ArrayBasedStack(){
        clear();
    }
    
    public ArrayBasedStack(int capacity){
        if(capacity <= 0) {
            throw new IllegalArgumentException("Array initial size error.");
        }

        @SuppressWarnings("unchecked")

        T[] tempStack = (T[])new Object[capacity]; 
        data = tempStack;
        topOfStack = -1;
    }

    private void expandArray(){
        data = Arrays.copyOf(data, data.length * 2);
    }

    private boolean isArrayFull(){
        if(topOfStack >= data.length - 1){
            return true;
        } else {
            return false;
        }
    }

    public void push(T newEntry){
        if(isArrayFull() == true){
            expandArray();
        }
        topOfStack++;
        data[topOfStack] = newEntry;
    }
  
    public T pop(){
        if(topOfStack == -1){
           throw new EmptyStackException();
        }
        T temp = data[topOfStack];
        data[topOfStack] = null;
        topOfStack--;
        return temp;
    }
  
    public T peek(){
        if(isEmpty() == true){
            throw new EmptyStackException();
        }
        return data[topOfStack];
    }

    public boolean isEmpty(){
        if(topOfStack < 0){
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        @SuppressWarnings("unchecked")

        T[] tempStack = (T[])new Object[INITIAL_CAPACITY];
        data = tempStack;
        topOfStack = -1;
    }
}
