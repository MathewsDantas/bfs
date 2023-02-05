package app.utils;

import java.util.LinkedList;

import app.structures.BinarySearchTree;
import app.structures.utils.BinaryTreeNode;

public class TreePrinter {
    public static void print(BinarySearchTree tree) {
        LinkedList<BinaryTreeNode> treeLevel = new LinkedList<BinaryTreeNode>();
        LinkedList<BinaryTreeNode> temp = new LinkedList<BinaryTreeNode>();
        
        treeLevel.add(tree.root());
        int counter = 0;
        int height = tree.height();
        double numberOfElements = Math.pow(2, (height + 1)) - 1;
        
        while (counter <= height) {
            BinaryTreeNode removed = treeLevel.removeFirst();
            
            if (temp.isEmpty()) {
                printElement(numberOfElements / Math.pow(2, counter + 1), removed);
            } else {
                printElement(numberOfElements / Math.pow(2, counter), removed);
            }

            if (removed == null) {
                temp.add(null);
                temp.add(null);
            } else {
                temp.add(removed.getLeftChild());
                temp.add(removed.getRightChild());
            }

            if (treeLevel.isEmpty()) {
                System.out.println("");
                printLine(numberOfElements / Math.pow(2, counter + 1), counter);
                System.out.println("");
                treeLevel = temp;
                temp = new LinkedList<>();
                counter++;
            }

        }
    }

    public static void printElement(double n, BinaryTreeNode removed) {
        for (; n > 0; n--) {
            System.out.print("    ");
        }
        if (removed == null) {
            System.out.print(" ");
        } else {
            if (removed.getObject() != null)
                 System.out.print(removed.getObject());
        }
    }

    public static void printLine(double n, int counter)
    {
        n/=2;
        for (double z = n+counter/2; z > 0; z--) {
            System.out.print("    ");
        }
        for (double z = n*2; z > 0; z--) {
            System.out.print("    ");
        }
    }
}
