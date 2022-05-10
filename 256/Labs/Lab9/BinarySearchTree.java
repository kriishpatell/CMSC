package Labs.Lab9;

public class BinarySearchTree <E extends Comparable<? super E>> {
    private int size = 0;
    private BinaryNode root = null;
    
    private boolean addToParent(BinaryNode parentNode, BinaryNode addNode){
        int compare = parentNode.value.compareTo(addNode.value);
        boolean wasAdded = false;

        if(compare > 0) {
            if(parentNode.left == null) {
                parentNode.left = addNode;
                wasAdded = true;
            } 
            else {
                wasAdded = addToParent(parentNode.left, addNode);
            } 
        } else if(compare < 0) {
            if(parentNode.right == null) {
                parentNode.right = addNode;
                wasAdded = true;
            } 
            else {
                wasAdded = addToParent(parentNode.right, addNode);
            }
        }

        return wasAdded;
    }

    public boolean add(E inValue) {
        BinaryNode node = new BinaryNode(inValue);
        boolean wasAdded = true;

        if(root == null){
            root = node;
        } else {
            wasAdded = addToParent(root, node);
        } 
        if(wasAdded) {
            size++;
        }
        return wasAdded;
    }

    public boolean remove(E removeValue) {
        if(root == null){
            return false;
        }

        if(root.value.compareTo(removeValue) == 0) {
            if(root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                BinaryNode formerRight = root.right;
                root = root.left;
                addToParent(root, formerRight);
            }
            size--;
            return true;
        }
        return removeSubNode(root, removeValue);
    }

    private boolean removeSubNode(BinaryNode parent, E removeValue){
        int compareParent = parent.value.compareTo(removeValue);
        BinaryNode subTree = null;
        if (compareParent > 0) {
            subTree = parent.left;
        } else {
            subTree = parent.right;
        }
        if (subTree == null) {
            return false;
        }
        if (subTree.value.compareTo(removeValue) == 0) {
            BinaryNode replacement;
            if (subTree.left == null) {
                replacement = subTree.right;
            } else if (subTree.right == null) {
                replacement = subTree.left;
            } else {
                BinaryNode formerRight = subTree.right;
                replacement = subTree.left;
                addToParent(replacement, formerRight);
            }
            if (compareParent > 0) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
            size--;
            return true;
        } else {
            return removeSubNode(subTree, removeValue);
        }
    }
    
    public int size() {
        return size;
    }

    public BinaryNode getRoot(){
        return root;
    }

    public void clear(){
        root = null;
        size = 0;
    }
    
    class BinaryNode {
        protected E value;
        protected BinaryNode right;
        protected BinaryNode left;

        public BinaryNode(E valueIn){
            value = valueIn;
        }
    }
}
