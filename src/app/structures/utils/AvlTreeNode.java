package app.structures.utils;

public class AvlTreeNode extends BinaryTreeNode {
    int balanceFactor;

    public AvlTreeNode(Object o, AvlTreeNode root) {
        super(o, root);
        this.balanceFactor = 0;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public int getBalanceFactor() {
        return this.balanceFactor;
    }
}
