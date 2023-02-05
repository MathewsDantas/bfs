package app.structures.utils;

public class RedBlackTreeNode extends BinaryTreeNode {
    boolean isBlack;

    public RedBlackTreeNode(Object o, RedBlackTreeNode root) {
        super(o, root);
        this.isBlack = false;
    }

    public RedBlackTreeNode(Object o, RedBlackTreeNode root, boolean isBlack) {
        super(o, root);
        this.isBlack = isBlack;
    }

    public RedBlackTreeNode getLeftChild() {
        return (RedBlackTreeNode) super.getLeftChild();
    }

    public RedBlackTreeNode getRightChild() {
        return (RedBlackTreeNode) super.getRightChild();
    }

    public RedBlackTreeNode getRootNode() {
        return (RedBlackTreeNode) super.getRootNode();
    }

    public void setIsBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return this.isBlack;
    }
}
