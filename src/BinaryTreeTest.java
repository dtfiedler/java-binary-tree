import org.jboss.arquillian.core.api.annotation.Inject;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    public BinaryTree createTree(){
        BinaryTree bt = new BinaryTree();
        bt.insert(1);
        bt.insert(4);
        bt.insert(2);
        bt.insert(6);
        bt.insert(3);
        bt.insert(9);
        return bt;
    }

    @Test
    public void insert() {
        BinaryTree bt = new BinaryTree();
        bt.insert(4);
        assertTrue(bt.containsNode(4));
    }

    @Test
    public void containsNode() {
        BinaryTree bt = createTree();
        System.out.print("TESTING CONTAINS NODE");
        bt.insert(1);
        bt.insert(2);

        assertTrue(bt.containsNode(1));
        assertTrue(bt.containsNode(2));
        assertFalse(bt.containsNode(0));
        assertFalse(bt.containsNode(-1));
        System.out.println(": PASSED");
    }

    @Test
    public void deleteNode(){
        BinaryTree bt = createTree();
        System.out.print("TESTING DELETE NODE");
        assertTrue(bt.containsNode(3));
        bt.deleteNode(3);
        assertFalse(bt.containsNode(3));
        System.out.println(": PASSED");
    }
}