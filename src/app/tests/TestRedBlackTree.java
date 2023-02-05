package app.tests;

import app.interfaces.ITest;
import app.structures.AvlTree;
import app.structures.RedBlackTree;
import app.structures.utils.AvlTreeNode;
import app.structures.utils.BinaryTreeNode;
import app.utils.GenericComparator;
import java.util.Random;
import java.util.ArrayList;

public class TestRedBlackTree implements ITest {
    public static void run() {

        // // insert father black
        // RedBlackTree tree = new RedBlackTree(15, new GenericComparator());
        // tree.insert(16);
        // tree.print();

        // // insert case 2
        // RedBlackTree tree = new RedBlackTree(15, new GenericComparator());
        // tree.insert(20);
        // tree.insert(10);
        // tree.insert(23);
        // tree.print();

        // // insert case 3a
        // RedBlackTree tree = new RedBlackTree(15, new GenericComparator());
        // tree.insert(14);
        // tree.insert(13);
        // tree.print();

        // // insert case 3b
        // RedBlackTree tree = new RedBlackTree(15, new GenericComparator());
        // tree.insert(16);
        // tree.insert(17);
        // tree.print();

        // // insert case 3c
        // RedBlackTree tree = new RedBlackTree(15, new GenericComparator());
        // tree.insert(16);
        // tree.insert(14);
        // tree.print();

        // // insert case 3d
        // RedBlackTree tree = new RedBlackTree(15, new GenericComparator());
        // tree.insert(14);
        // tree.insert(16);
        // tree.print();

        // linear
        RedBlackTree tree = new RedBlackTree(1, new GenericComparator());
        for (int i = 2; i < 11; i++) {
            System.out.println("Inserindo " + i);
            tree.insert(i);
            tree.print();
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------");
        }
        tree.print();
        tree.remove(6);
        tree.print();

        // // test random
        // RedBlackTree tree = new RedBlackTree(20, new GenericComparator());
        // Random gerador = new Random();
        // ArrayList<Integer> nums = new ArrayList<Integer>();
        // for (int i = 0; i < 10; i++) {
        // int num = gerador.nextInt(40);
        // BinaryTreeNode exists = tree.search(num);
        // if (exists.getObject() == null) {
        // tree.insert(num);
        // nums.add(num);
        // }
        // System.out.println("-------------------------");
        // tree.print();
        // }

        // // // insert case 1
        // RedBlackTree tree = new RedBlackTree(20, new GenericComparator());
        // tree.insert(30);
        // tree.insert(1);
        // tree.insert(21);
        // tree.print();
        // tree.insert(22);
        // tree.print();

        // // test remove right
        // RedBlackTree tree = new RedBlackTree(20, new GenericComparator());
        // tree.insert(21);
        // tree.print();
        // tree.remove(21);
        // tree.print();

        // // test remove with right
        // RedBlackTree tree = new RedBlackTree(20, new GenericComparator());
        // tree.insert(10);
        // tree.insert(5);
        // tree.insert(7);
        // tree.print();
        // tree.remove(5);
        // tree.print();

        // // test remove with right
        // RedBlackTree tree = new RedBlackTree(20, new GenericComparator());
        // tree.insert(10);
        // tree.insert(5);
        // tree.insert(7);
        // tree.print();
        // tree.remove(5);
        // tree.print();

        // // test remove with both
        // RedBlackTree tree = new RedBlackTree(20, new GenericComparator());
        // tree.insert(10);
        // tree.insert(5);
        // tree.insert(7);
        // tree.insert(2);
        // tree.insert(6);
        // tree.print();
        // tree.remove(5);
        // tree.print();

    }
}
