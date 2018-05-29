import org.jboss.arquillian.core.api.annotation.Inject;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

//    @Inject
//    BinaryTree bt;
    public BinaryTree createTree(){
        BinaryTree bt = new BinaryTree();
        bt.insert(1);
        bt.insert(2);
        bt.insert(3);
        bt.insert(4);
        bt.insert(5);
        bt.insert(6);

        return bt;
    }

    @Test
    public void insert() {
    }

    @Test
    public void containsNode() {
        System.out.print("TESTING CONTAINS NODE");
        BinaryTree bt = createTree();
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
        System.out.print("TESTING DELETE NODE");
        BinaryTree bt = createTree();
        assertTrue(bt.containsNode(3));
        bt.deleteNode(3);
        assertFalse(bt.containsNode(3));
        System.out.println(": PASSED");
    }
}