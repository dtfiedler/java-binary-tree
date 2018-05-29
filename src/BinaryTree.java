class Node {
    int value;
    Node leftChild;
    Node rightChild;

    //constructor to create a new node
    Node(int value){
        this.value = value;
        leftChild = null;
        rightChild = null;
    }
}

public class BinaryTree {

    Node root;

    //insert a node into the proper position based on it's value
    public void insert(int value){
        //recursively add from root
        root = addRecursive(root, value);
    }

    //determine if current tree contains node of certain value
    public boolean containsNode(int value){
        return containsNodeRecursive(root, value);
    }

    //delete node if exists
    public void deleteNode(int value){
        deleteNodeRecursive(root, value);
    }

    //recursively add Nodes to binary tree in proper position
    private Node addRecursive(Node current, int value){
        //nothing set
        if (current == null){
            current = new Node(value);
            return current;
        }

        //1 check value compared to value of current node value
        if (value < current.value){
            //it goes to the left node
            current.leftChild = addRecursive(current.leftChild, value);
        } else {
            //it goes to the right node
            current.rightChild = addRecursive(current.rightChild, value);
        }

        return current;
    }

    //recursively search tree for node value
    private boolean containsNodeRecursive(Node current, int value){
        if (current == null){
            return false;
        }

        if (current.value == value){
            return true;
        }

        //determine if value is on left or right side of current node
        if (value < current.value){
            return containsNodeRecursive(current.leftChild, value);
        } else {
            return containsNodeRecursive(current.rightChild, value);
        }
    }

    private Node deleteNodeRecursive(Node current, int value){
        //if null, return null
        if (current == null){
            return null;
        }

        if (current.value == value){
            //1. node has no left or right value, so just return null
            if (current.leftChild == null && current.rightChild == null) {
                return null;
            }

            //2.a. only one left child
            if (current.rightChild == null){
                return  current.leftChild;
            }

            //2.b. only one right child
            if (current.leftChild == null){
                return  current.rightChild;
            }

            //it has both children, need to traverse tree and find the lowest value of the right subtree
            int smallestValue = findSmallestValue(current.rightChild);
            //found the value, now rebuild right subtree by removing the value from it's previous spot
            current.value = smallestValue;
            current.rightChild = deleteNodeRecursive(current.rightChild, smallestValue);
            return current;
        }

        //traverse until we delete it
        if(value < current.value){
            //value is less than current
            current.leftChild = deleteNodeRecursive(current.leftChild, value);
            return current;
        }

        current.rightChild = deleteNodeRecursive(current.rightChild, value);
        return current;
    }

    private int findSmallestValue(Node root){
        System.out.println("traversing:" + root.value);
        //if no left child, return root, else return lowest value of right tree (leaf)
        return root.leftChild == null ? root.value : findSmallestValue(root.rightChild);
    }

    public void print(){
        //traverse and print
    }
}
