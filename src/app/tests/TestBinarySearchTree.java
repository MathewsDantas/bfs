package app.tests;

import app.interfaces.ITest;
import app.structures.BinarySearchTree;
import app.utils.GenericComparator;

public class TestBinarySearchTree implements ITest {
    public static void run() {
        BinarySearchTree tree = new BinarySearchTree(5, new GenericComparator());
        
        //tree.print();

        //slide
        // tree.insert(9);
        // tree.insert(8);
        // tree.insert(2);
        // tree.insert(1);
        // tree.insert(4);
        // tree.insert(3);
        // tree.insert(5);

        // tree.insert(9);
        // tree.insert(8);
        // tree.insert(1);
        // tree.insert(-1);
        // tree.insert(4);
        // tree.insert(2);
        // tree.insert(5);
        // tree.insert(3);
    
        tree.print();

        //tree.remove(1);

        //tree.print();
    }
}
