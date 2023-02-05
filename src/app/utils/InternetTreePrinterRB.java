package app.utils;

import app.structures.RedBlackTree;
import app.structures.utils.RedBlackTreeNode;

public class InternetTreePrinterRB {
    static final int COUNT = 24;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public static void print(RedBlackTree tree) {
        printR((RedBlackTreeNode) tree.getRoot(), 0, tree);
        System.out.println();
    }

    public static void printR(RedBlackTreeNode root, int space, RedBlackTree tree) {
        // Base case
        if (root == null)
            return;
 
        // Increase distance between levels
        space += COUNT;
 
        // Process right child first
        printR((RedBlackTreeNode) root.getRightChild(), space, tree);
 
        // Print current node after space
        // count
        RedBlackTreeNode rootz = (RedBlackTreeNode) root.getRootNode();
        Object pai = rootz != null ? rootz.getObject() : null;

        RedBlackTreeNode leftz = (RedBlackTreeNode) root.getLeftChild();
        Object left = leftz != null ? leftz.getObject() : null;

        RedBlackTreeNode rightz = (RedBlackTreeNode) root.getRightChild();
        Object right = rightz != null ? rightz.getObject() : null;

        String isRoot = root == (RedBlackTreeNode) tree.getRoot() ? "****" : "";
        
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print((root.isBlack() ? ANSI_BLACK : ANSI_RED)+isRoot+root.getObject()+(root.isBlack() ? ":B" : ":R")+";RO:"+pai+";L:"+left+";R:"+right);
 
        // Process left child
        printR((RedBlackTreeNode) root.getLeftChild(), space, tree);
    }
}
