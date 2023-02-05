package app.utils;

import java.util.LinkedList;

import app.structures.RedBlackTree;
import app.structures.utils.RedBlackTreeNode;

public class RedBlackTreePrinter {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK = "\u001B[30m";
    
    public static void print(RedBlackTree tree) {
        LinkedList<RedBlackTreeNode> treeLevel = new LinkedList<RedBlackTreeNode>();
        LinkedList<RedBlackTreeNode> temp = new LinkedList<RedBlackTreeNode>();
        
        treeLevel.add((RedBlackTreeNode) tree.root());
        int counter = 0;
        int height = tree.height();
        double numberOfElements = Math.pow(2, (height + 1)) - 1;
        
        while (counter <= height) {
            RedBlackTreeNode removed = treeLevel.removeFirst();
            
            if (temp.isEmpty()) {
                printElement(numberOfElements / Math.pow(2, counter - 2), removed);
            } else {
                printElement(numberOfElements / Math.pow(2, counter - 3), removed);
            }

            if (removed == null) {
                temp.add(null);
                temp.add(null);
            } else {
                temp.add((RedBlackTreeNode) removed.getLeftChild());
                temp.add((RedBlackTreeNode) removed.getRightChild());
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

    public static void printElement(double n, RedBlackTreeNode removed) {
        for (; n/2 > 0; n--) {
            System.out.print(" ");
        }
        if (removed == null) {
            System.out.print(" ");
        } else {
            if (removed.getObject() != null){
                RedBlackTreeNode root = (RedBlackTreeNode) removed.getRootNode();
                Object pai = root != null ? root.getObject() : null;
                String color = removed.isBlack() ? "b" : "r";
                System.out.print((removed.isBlack() ? ANSI_BLACK : ANSI_RED)+ removed.getObject()+":"+color);
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
