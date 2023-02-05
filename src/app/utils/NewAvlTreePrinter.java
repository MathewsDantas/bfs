package app.utils;

import java.lang.Math;
import java.util.List;

import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;


public class NewAvlTreePrinter {
    public static void print(AvlTree tree) {
        int height = tree.height();
        int lineSizeArray = (int) Math.pow(2, height) + ((int) Math.pow(2, height) - 1);
        AvlTreeNode[] avlNodeArray = treeToArray(tree);

        int breakpoin = 0;
        for(AvlTreeNode node : avlNodeArray){
            for (int i = 0; i < 3; i++)
                System.out.print(" ");

            if (node == null) {
                System.out.println("*");
            } else {
                System.out.println("-");
            }

            for (int i = 0; i < 3; i++)
                System.out.print(" ");

            breakpoin++;
            if (breakpoin == lineSizeArray) {
                System.out.println();
                breakpoin = 0;
            }
        }
    }

    public static AvlTreeNode[] treeToArray(AvlTree tree) {
        int height = tree.height();
        int lineSizeArray = (int) Math.pow(2, height) + ((int) Math.pow(2, height) - 1);
        AvlTreeNode[] nodes = new AvlTreeNode[lineSizeArray*(height+1)];

        return nodes;
    }
}
