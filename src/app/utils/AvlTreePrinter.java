package app.utils;

import java.util.LinkedList;

import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;

public class AvlTreePrinter {
    public static void print(AvlTree tree) {
        LinkedList<AvlTreeNode> treeLevel = new LinkedList<AvlTreeNode>();
        LinkedList<AvlTreeNode> temp = new LinkedList<AvlTreeNode>();
        
        treeLevel.add((AvlTreeNode) tree.root());
        int counter = 0;
        int height = tree.height();
        double numberOfElements = Math.pow(2, (height + 1)) - 1;
        
        while (counter <= height) {
            AvlTreeNode removed = treeLevel.removeFirst();
            
            if (temp.isEmpty()) {
                printElement(numberOfElements / Math.pow(2, counter - 2), removed);
            } else {
                printElement(numberOfElements / Math.pow(2, counter - 3), removed);
            }

            if (removed == null) {
                temp.add(null);
                temp.add(null);
            } else {
                temp.add((AvlTreeNode) removed.getLeftChild());
                temp.add((AvlTreeNode) removed.getRightChild());
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

    public static void printElement(double n, AvlTreeNode removed) {
        for (; n > 0; n--) {
            System.out.print(" ");
        }
        if (removed == null) {
            System.out.print(" ");
        } else {
            if (removed.getObject() != null){
                AvlTreeNode root = (AvlTreeNode) removed.getRootNode();
                Object pai = root != null ? root.getObject() : null;
                System.out.print(removed.getObject()+":"+removed.getBalanceFactor());
                //System.out.print(removed.getObject()+":"+removed.getBalanceFactor());
                //System.out.print(removed.getObject()+":"+removed.getBalanceFactor()+";L:"+removed.getLeftChild().getObject()+";R:"+removed.getRightChild().getObject());
            }
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
