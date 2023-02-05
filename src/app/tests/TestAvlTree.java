package app.tests;

import app.interfaces.ITest;
import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;
import app.utils.GenericComparator;
import java.util.Random;
import java.util.ArrayList;

public class TestAvlTree implements ITest {
    public static void run() { // 32
        AvlTree tree = new AvlTree(32, new GenericComparator());

        // // remove with left child
        // tree.insert(25);
        // tree.insert(37);
        // tree.insert(22);
        // tree.print();
        // System.out.println("----");
        // tree.remove(25);
        // tree.print();

        // // remove with right child
        // tree.insert(25);
        // tree.insert(37);
        // tree.insert(26);
        // tree.print();
        // System.out.println("----");
        // tree.remove(25);
        // tree.print();

        // // remove folha
        // tree.insert(25);
        // tree.insert(37);
        // tree.insert(26);
        // tree.print();
        // System.out.println("----");
        // tree.remove(26);
        // tree.print();

        // // remove with both childs (1d)
        // tree.insert(24);
        // tree.insert(37);
        // tree.insert(26);
        // tree.insert(22);
        // tree.insert(38);
        // tree.insert(25);
        // tree.print();
        // System.out.println("----");
        // tree.remove(24);
        // tree.print();

        // // remove with both childs (0d)
        // tree.insert(24);
        // tree.insert(37);
        // tree.insert(26);
        // tree.insert(22);
        // tree.insert(38);
        // tree.print();
        // System.out.println("----");
        // tree.remove(24);
        // tree.print();

        // //test simple left
        // tree.insert(33);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(34);
        // tree.print();

        // // test simple left not root
        // tree.insert(31);
        // tree.insert(33);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(34);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(35);
        // tree.print();

        // //test simple right
        // tree.insert(31);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(30);
        // tree.print();

        // //test simple right not root
        // tree.insert(33);
        // tree.insert(31);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(30);
        // tree.insert(29);
        // tree.print();

        // // test double left
        // tree.insert(34);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(33);
        // tree.print();

        // // test double right
        // tree.insert(30);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(31);
        // tree.print();

        // // test random
        // Random gerador = new Random();
        // while (true){
        // ArrayList<Integer> nums = new ArrayList<Integer>();
        // for (int i = 0; i < 10; i++) {
        //     int num = gerador.nextInt(40);
        //     AvlTreeNode exists = (AvlTreeNode) tree.search(num);
        //     if (exists.getObject() == null) {
        //         tree.insert(num);
        //         nums.add(num);
        //     }
        //     System.out.println("-------------------------");
        //     tree.print();
        // }
        // System.out.println("-------------------------");
        // System.out.println("Removendo");
        // for (Integer num : nums) {
        //     System.out.println("Removendo: " + num);
        //     tree.remove(num);
        //     tree.print();
        // }
        // }

        // // test error case 3
        // AvlTree tree2 = new AvlTree(8, new GenericComparator());
        // tree2.insert(6);
        // tree2.insert(31);
        // tree2.insert(3);
        // tree2.insert(32);
        // tree2.insert(15);
        // tree2.insert(14);
        // tree2.print();
        // tree2.remove(6);
        // tree2.print();

        // // test error case 2
        // AvlTree tree2 = new AvlTree(31, new GenericComparator());
        // tree2.insert(30);
        // tree2.insert(32);
        // tree2.insert(11);
        // tree2.insert(38);
        // //tree2.insert(33);
        // // tree2.insert(39);
        // tree2.print();
        // tree2.remove(30);
        // tree2.print();

        // // test error case 1
        // AvlTree tree2 = new AvlTree(23, new GenericComparator());
        // tree2.insert(25);
        // tree2.insert(11);
        // tree2.insert(32);
        // tree2.insert(14);
        // tree2.insert(9);
        // tree2.insert(16);
        // tree2.insert(10);
        // tree2.insert(5);
        // tree2.print();
        // tree2.remove(25);
        // tree2.print();

        // // test ten
        // ArrayList<Integer> nums = new ArrayList<Integer>();
        // for (int i = 0; i < 10; i++) {
        // int num = i;
        // AvlTreeNode exists = (AvlTreeNode) tree.search(num);
        // if (exists.getObject() == null){
        // tree.insert(num);
        // nums.add(num);
        // }
        // System.out.println("-------------------------");
        // tree.print();
        // }
        // System.out.println("-------------------------");
        // System.out.println("Removendo");
        // for (Integer num : nums) {
        // System.out.println("Removendo: "+num);
        // tree.remove(num);
        // tree.print();
        // }

        // // Test remove
        // tree.insert(34);
        // tree.insert(35);
        // tree.insert(31);
        // tree.insert(30);
        // tree.print();
        // System.out.println("----------");
        // tree.remove(35);
        // tree.print();
    }
}
