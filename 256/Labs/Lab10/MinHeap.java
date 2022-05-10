package Labs.Lab10;

public class MinHeap <E extends Comparable<? super E>>{
    private E[] heap;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 8;

    @SuppressWarnings("unchecked")
    public MinHeap(int capacity){
        if(capacity > 0) {
            Object[] temp;
            temp = new Comparable[capacity];
            heap = (E[]) temp;
        }
        else {
            throw new IllegalArgumentException("Array size must be greater than 0.");
        }
    }
    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void expand() {
        @SuppressWarnings("unchecked")

        E[] newHeap = (E[]) new Comparable[heap.length * 2];
        for (int i = 0; i < size(); i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    private void swapElements(int index1, int index2) {

        E x = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = x;

    }
    private int getParentIndex(int childIndex) {

        if (childIndex % 2 != 0) {
            return childIndex / 2;
        }
        else {
            return childIndex / 2 - 1;
        }
    }

    private int smallerChildIndex(int parentIndex) {

        int smaller = parentIndex;
        int leftChild = (2 * (parentIndex) + 1);
        if (leftChild < size() - 1) {
            if(heap[leftChild].compareTo(heap[smaller]) < 0)
                smaller = leftChild;
            int rightChild = (2 *(parentIndex) + 2);

            if (rightChild < size() - 1) {

                if(heap[rightChild].compareTo(heap[smaller]) < 0)
                    smaller = rightChild;

            }
        }
        return smaller;

    }

    public void insert(E element) {

        int position = size();
        if (position == heap.length) {
            expand();
        }
        size++;
        heap[position] = element;
        int parentIndex = getParentIndex(position);
        while (position > 0 && heap[position].compareTo(heap[parentIndex]) < 0) {
            swapElements(parentIndex,position);
            position = parentIndex;
            parentIndex = getParentIndex(position);
        }
    }
    public static void main(String[] args) {

        MinHeap<Integer> mh = new MinHeap<>();

        mh.insert(2);
        mh.insert(4);
        mh.insert(1);
        mh.insert(10);
        mh.insert(3);
        mh.insert(6);
        mh.insert(15);
        mh.insert(12);
        mh.insert(16);
        mh.insert(5);

        while(!mh.isEmpty())

            System.out.println(mh.remove());

        System.out.println();
    }


    public E remove() {
        if (isEmpty()) {
            return null;
        }


        E min = heap[0];
        heap[0] = heap[size() - 1];
        heap[size() - 1] = null;
        size--;


        int position = 0;
        int smallerChildIndex = smallerChildIndex(position) ;


        while (smallerChildIndex != position) {
            swapElements(position, smallerChildIndex);
            position = smallerChildIndex;
            smallerChildIndex = smallerChildIndex(position);
        }
        return min;

    }



}

