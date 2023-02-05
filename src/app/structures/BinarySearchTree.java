package app.structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import app.structures.utils.BinaryTreeNode;
import app.utils.TreePrinter;

public class BinarySearchTree {
    
    private int IS_SMALLER = -1;
    private int IS_EQUAL = 0;
    private int IS_GREATER = 1;

    private BinaryTreeNode root;
    private int tamanho;
    private Comparator comparator;

    public BinarySearchTree(Object o, Comparator<Object> comparator)
    {
        this.root = new BinaryTreeNode(o);
        this.root.setLeftChild(new BinaryTreeNode(null, this.root));
        this.root.setRightChild(new BinaryTreeNode(null, this.root));
        this.tamanho++;
        this.comparator = comparator;
    }

    public BinaryTreeNode getRoot() {
        return this.root;
    }

    public void setRoot(BinaryTreeNode node) {
        this.root = node;
    }

    public int size()
    {
        return this.tamanho;
    }

    public int height()
    {
        int depth = 0;
 
        Iterator el = nos();
 
        while (el.hasNext()) {
            BinaryTreeNode v = (BinaryTreeNode) el.next();
            if (depth(v) > depth)
                depth = depth(v);
        }
 
        return depth;
    }

    public boolean isEmpty()
    {
        return this.tamanho == 0;
    }

    public Iterator elements()
    {
        ArrayList array = new ArrayList<>();
        this.elementsRec(array, this.root);
        return array.iterator();
    }

    public ArrayList<BinaryTreeNode> elementsArray()
    {
        ArrayList<BinaryTreeNode> array = new ArrayList<>();
        this.nosRec(array, this.root);
        return array;
    }

    private void elementsRec(ArrayList array, BinaryTreeNode node)
    {
        if (node.getObject() == null) return;
        array.add(node.getObject());
        elementsRec(array, node.getLeftChild());
        elementsRec(array, node.getRightChild());
    }

    public Iterator nos()
    {
        ArrayList<BinaryTreeNode> array = new ArrayList<>();
        this.nosRec(array, this.root);
        return array.iterator();
    }

    private void nosRec(ArrayList<BinaryTreeNode> array, BinaryTreeNode node)
    {
        if (node.getObject() == null) return;
        array.add(node);
        nosRec(array, node.getLeftChild());
        nosRec(array, node.getRightChild());
    }

    public BinaryTreeNode root()
    {
        return this.root;
    }

    public BinaryTreeNode parent(BinaryTreeNode node)
    {
        return node.getRootNode();
    }

    public BinaryTreeNode leftChild(BinaryTreeNode node)
    {
        return node.getLeftChild();
    }

    public BinaryTreeNode rightChild(BinaryTreeNode node)
    {
        return node.getRightChild();
    }

    public boolean hasLeft(BinaryTreeNode node)
    {
        return node.getLeftChild() != null;
    }

    public boolean hasRight(BinaryTreeNode node)
    {
        return node.getRightChild() != null;
    }

    public boolean isInternal(BinaryTreeNode node)
    {
        return node.getLeftChild() != null || node.getRightChild() != null;
    }

    public boolean isExternal(BinaryTreeNode node)
    {
        return node.getLeftChild() == null && node.getRightChild() == null;
    }

    public boolean isRoot(BinaryTreeNode node)
    {
        return node == this.root;
    }

    public int depth(BinaryTreeNode node)
    {
        if (this.isRoot(node)) return 0;
        return 1 + depth(node.getRootNode());
    }

    public BinaryTreeNode search(Object o) 
    {
        return this.searchRec(o, this.root);
    }

    private BinaryTreeNode searchRec(Object key, BinaryTreeNode node)
    {
        if (node == null || this.isExternal(node) || this.comparator.compare(key, node.getObject()) == IS_EQUAL)
            return node;
        
        else if (this.comparator.compare(key, node.getObject()) == IS_SMALLER)
            return searchRec(key, node.getLeftChild());
        
        return searchRec(key, node.getRightChild());
    }

    public void insert(Object key) throws RuntimeException 
    {
        BinaryTreeNode node = this.search(key);
        if (node.getObject() == null){
            node.setObject(key);
            node.setLeftChild(new BinaryTreeNode(null, node));
            node.setRightChild(new BinaryTreeNode(null, node));
            this.tamanho++;
        }
        else
            throw new IllegalArgumentException(String.format("Elemento %s ja existe", key));
    }

    public void remove(Object key)
    {
        BinaryTreeNode node = this.search(key);
        if (node.getObject() == null)
            throw new IllegalArgumentException("Elemento nao existe");

        BinaryTreeNode removedRoot = node.getRootNode();
        if (removedRoot == null) {
            this.setRoot(null);
        }
        boolean isLeft = removedRoot.getRightChild() == node;
       
        // se for externo
        if (isExternal(node.getLeftChild()) && isExternal(node.getRightChild())){
            node.setObject(null);
            node.setLeftChild(null);
            node.setRightChild(null);
        } else if (isExternal(node.getLeftChild()) && !isExternal(node.getRightChild())){
            node.setObject(node.getRightChild().getObject());
            node.setLeftChild(null);
            node.setRightChild(node.getRightChild().getRightChild());
        } else if (!isExternal(node.getLeftChild()) && isExternal(node.getRightChild())){
            node.setObject(node.getLeftChild().getObject());
            node.setLeftChild(node.getLeftChild().getLeftChild());
            node.setRightChild(null);
        } else {
            BinaryTreeNode aux = node.getRightChild();
            while (aux.getLeftChild().getObject() != null)
                aux = aux.getLeftChild();
            System.out.println(aux.getRootNode());

            node.setObject(aux.getObject());
            aux.setObject(null);

            if (aux.getRightChild().getObject() != null)
                aux.getRootNode().setLeftChild(aux.getRightChild());
                     
            aux.setRightChild(null);
            aux.setLeftChild(null);
        }
    }
    
    public void print()
    {
        TreePrinter.print(this);
    }

}