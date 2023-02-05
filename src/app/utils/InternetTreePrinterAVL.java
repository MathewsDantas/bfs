package app.utils;

import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;

public class InternetTreePrinterAVL {
    static final int COUNT = 24;

    public static void print(AvlTree tree) {
        printR((AvlTreeNode) tree.getRoot(), 0, tree);
        System.out.println();
    }

    public static void printR(AvlTreeNode root, int space, AvlTree tree) {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        printR((AvlTreeNode) root.getRightChild(), space, tree);

        // Print current node after space
        // count
        AvlTreeNode rootz = (AvlTreeNode) root.getRootNode();
        Object pai = rootz != null ? rootz.getObject() : null;

        AvlTreeNode leftz = (AvlTreeNode) root.getLeftChild();
        Object left = leftz != null ? leftz.getObject() : null;

        AvlTreeNode rightz = (AvlTreeNode) root.getRightChild();
        Object right = rightz != null ? rightz.getObject() : null;

        String isRoot = root == (AvlTreeNode) tree.getRoot() ? "****" : "";

        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(isRoot + root.getObject() + ":" + root.getBalanceFactor() + ";RO:" + pai + ";L:" + left + ";R:"
                + right);

        // Process left child
        printR((AvlTreeNode) root.getLeftChild(), space, tree);
    }
}
