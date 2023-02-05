package app.structures.utils;

import app.interfaces.IGenericNode;

public class BinaryTreeNode implements IGenericNode {
    private Object object;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;
    private BinaryTreeNode rootNode;

    public BinaryTreeNode(Object o) {
        this.object = o;
    }

    public BinaryTreeNode(Object o, BinaryTreeNode node) {
        this.object = o;
        this.rootNode = node;
    }

    public void setObject(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return this.object;
    }

    public void setLeftChild(BinaryTreeNode node) {
        this.leftChild = node;
    }

    public BinaryTreeNode getLeftChild() {
        return this.leftChild;
    }

    public void setRightChild(BinaryTreeNode node) {
        this.rightChild = node;
    }

    public BinaryTreeNode getRightChild() {
        return this.rightChild;
    }

    public void setRootNode(BinaryTreeNode node) {
        this.rootNode = node;
    }

    public BinaryTreeNode getRootNode() {
        return this.rootNode;
    }
}
