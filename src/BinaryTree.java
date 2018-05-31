/*
Steps:
    1. Create Node Structure
    2. Create Tree Class
    3. Insert to tree
        1. Check if tree is empty, if so , add there
        2. If not empty, check value compared to current node, if less recursively add to left, if greater, recursively add to right
        3. return the current tree (or recursive call)
    4. Find value
        1. Check value against root node, if root is null, return false
        2. If equal, return true, if less recursively search left, if greater recursively search right and return
    5. Delete node
        1. Check if root exists, else return error or null
        2. Similar to find, recursively check value, if node value equals value you want to delete:
            - To rebuild: 3 options
                   1. No children, just remove node (return null, again its recurisive so returning null means set the parent node .left or .right child to null)
                   2. if only left child, set node leftChild = null, if only right child set node rightChild = null
                   3. If 2 children, most complicated, traverse right subtree to replace, finding the left most leaf value to replace with
                        - recursively find the smallest value in the right subtree (return left child until no more left children) and return that value
                        - now that we have that value, set the current node value to that value, and then recursively delete the node iwth that value in the right subtree (using delete function again)
        3. Otherwise, if value is less then current node value, repeat with left child, else repeat function with right child
*/

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
        //if no left child, return root, else return lowest value of right tree (leaf)
        return root.leftChild == null ? root.value : findSmallestValue(root.rightChild);
    }

    //traverse tree in order (left, node, right)
    public void traverseInOrder(Node node){
        if (node != null){
            traverseInOrder(node.leftChild);
            System.out.print(" " + node.value);
            traverseInOrder(node.rightChild);
        }
    }


    //traverse left, right, node order
    public void traversePreOrder(Node node){
        if (node != null){
            traversePreOrder(node.leftChild);
            traversePreOrder(node.rightChild);
            System.out.print(" " + node.value);
        }
    }

    //traverse root, left, right
    public void traversePostOrder(Node node){
        if (node != null){
            if (node!= null){
                System.out.print(" " + node.value);
                traversePostOrder(node.leftChild);
                traversePostOrder(node.rightChild);
            }
        }
    }

    public Node getRoot(){
        return root;
    }
}
