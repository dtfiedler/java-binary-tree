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

import java.util.LinkedList;
import java.util.Queue;

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

    //delete node if exists from right subtree
    public void deleteNodeSmall(int value){
        deleteNodeRecursiveSmallest(root, value);
    }

    //delete node if exists from left subtree
    public void deleteNodeLarge(int value){
        deleteNodeRecursiveLargest(root, value);
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

    public Node deleteNodeRecursiveLargest(Node current, int value){
        if (current == null){
            return null;
        }

        //we are at the node we want to delete
        if (current.value == value){
            //three scenarios

            //1. no children
            if (current.leftChild == null && current.rightChild == null){
                return null;
            }

            //only right child, return that to replace current node
            if (current.leftChild == null){
                return current.rightChild;
            }

            //only left child, return that to replace current node
            if (current.rightChild == null){
                return current.leftChild;
            }

            //hardest: node has two children
            //in this we want to traverse left subtree and get largest value, then replace that value of current node, and delete the node of the left subtree
            int largestValue = findLargestValue(current.leftChild);
            current.value = largestValue;
            current.leftChild = deleteNodeRecursiveLargest(current.leftChild, largestValue);
            return current;
        }

        //not at the right node, keep going
        if (value < current.value){
            //value is in left subtree
            current.leftChild = deleteNodeRecursiveLargest(current.leftChild, value);
            return current;
        }
        //value is in right subtree
        current.rightChild = deleteNodeRecursiveLargest(current.rightChild, value);
        return current;
    }

    private Node deleteNodeRecursiveSmallest(Node current, int value){
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
            current.rightChild = deleteNodeRecursiveSmallest(current.rightChild, smallestValue);
            return current;
        }

        //traverse until we delete it
        if(value < current.value){
            //value is less than current
            current.leftChild = deleteNodeRecursiveSmallest(current.leftChild, value);
            return current;
        }

        current.rightChild = deleteNodeRecursiveSmallest(current.rightChild, value);
        return current;
    }

    private int findSmallestValue(Node root){
        //if no left child, return root, else return lowest value of right tree (leaf)
        return root.leftChild == null ? root.value : findSmallestValue(root.rightChild);
    }

    private int findLargestValue(Node root){
        //if no left child, return root, else return lowest value of right tree (leaf)
        return root.rightChild == null ? root.value : findLargestValue(root.rightChild);
    }


    public Node getRoot(){
        return root;
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
            System.out.print(" " + node.value);
            traversePreOrder(node.leftChild);
            traversePreOrder(node.rightChild);
        }
    }

    //traverse root, left, right
    public void traversePostOrder(Node node){
        if (node != null){
            if (node!= null){
                traversePostOrder(node.leftChild);
                traversePostOrder(node.rightChild);
                System.out.print(" " + node.value);
            }
        }
    }

    //Breadth first search (Level order searching)
    public void traverseLevelOrder(){
        if (root == null){
            return;
        }

        //Create a linked list of traversed nodes
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()){
            //remove the top node from the list and print it out
            Node node = nodes.remove();

            System.out.print(" " + node.value);

            //if it has a left child, add it to the list
            if (node.leftChild != null){
                nodes.add(node.leftChild);
            }

            //after we've added left child, add right child
            if (node.rightChild != null){
                nodes.add(node.rightChild);
            }
        }
        return;
    }
}


/*

Now we will explore search methods and their time complexities

1. Depth first search:
    - Depth-first search is a type of traversal that goes deep as much as possible in every child before exploring the next sibling.
    - 3 ways to traverse (in-order (left node, right), pre-order (root, left, right), and post-order (left, right, node))
2. Breadth-First search:
    - Display all the nodes at a current level before printing the level above
        - Start at root, then print left child, then right child, then next level
*/

class BinaryTreeDFS extends BinaryTree{

    public static void main(String args[]) {
        BinaryTreeDFS bt = new BinaryTreeDFS();
        bt.insert(6);
        bt.insert(4);
        bt.insert(8);
        bt.insert(3);
        bt.insert(5);
        bt.insert(7);
        bt.insert(9);
        bt.traverseInOrder(bt.getRoot());
        System.out.println();
        bt.traversePreOrder(bt.getRoot());
        System.out.println();
        bt.traversePostOrder(bt.getRoot());
        System.out.println();
        bt.traverseLevelOrder();
    }


}