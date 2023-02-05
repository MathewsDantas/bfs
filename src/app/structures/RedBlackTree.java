package app.structures;

import java.util.Comparator;
import java.lang.RuntimeException;

import app.structures.utils.RedBlackTreeNode;
import app.utils.InternetTreePrinterRB;
import app.utils.RedBlackTreePrinter;

public class RedBlackTree extends BinarySearchTree {
    public RedBlackTree(Object root, Comparator<Object> comparator) {
        super(root, comparator);
        this.setRoot(root);
    }

    public void setRoot(Object object) {
        RedBlackTreeNode rootNode = new RedBlackTreeNode(object, null);
        rootNode.setLeftChild(newEmptyChild(rootNode));
        rootNode.setRightChild(newEmptyChild(rootNode));
        rootNode.setIsBlack(true); // first rule
        super.setRoot(rootNode);
    }

    private RedBlackTreeNode newEmptyChild(RedBlackTreeNode root) {
        RedBlackTreeNode rbn = new RedBlackTreeNode(null, root);
        rbn.setIsBlack(true);
        return rbn;
    }

    @Override
    public void insert(Object value) throws RuntimeException {
        RedBlackTreeNode insertNode = (RedBlackTreeNode) this.search(value);
        if (insertNode.getObject() != null)
            throw new RuntimeException("Nó já inserido");

        insertNode.setObject(value);
        insertNode.setLeftChild(newEmptyChild(insertNode));
        insertNode.setRightChild(newEmptyChild(insertNode));
        insertNode.setIsBlack(false);

        this.validateInsert(insertNode);
    }

    private void validateInsert(RedBlackTreeNode insertNode) {
        if (this.getRoot() == insertNode) {
            insertNode.setIsBlack(true);
            return;
        }

        RedBlackTreeNode fatherNode = (RedBlackTreeNode) insertNode.getRootNode();
        RedBlackTreeNode grandFatherNode = fatherNode != null ? (RedBlackTreeNode) fatherNode.getRootNode() : null;
        RedBlackTreeNode uncleNode = grandFatherNode != null
                ? grandFatherNode.getLeftChild() == fatherNode ? (RedBlackTreeNode) grandFatherNode.getRightChild()
                        : (RedBlackTreeNode) grandFatherNode.getLeftChild()
                : null;

        if (fatherNode.isBlack()) {
            return;
        } else if (!fatherNode.isBlack() && grandFatherNode.isBlack() && !uncleNode.isBlack()) {
            uncleRedRebalance(insertNode, fatherNode, grandFatherNode, uncleNode);
        } else if (!fatherNode.isBlack() && grandFatherNode.isBlack() && uncleNode.isBlack()) {
            uncleBlackRebalance(insertNode, fatherNode, grandFatherNode, uncleNode);
        }
    }

    private void uncleRedRebalance(RedBlackTreeNode node, RedBlackTreeNode fatherNode,
            RedBlackTreeNode grandFatherNode, RedBlackTreeNode uncleNode) {

        if (grandFatherNode != null)
            grandFatherNode.setIsBlack(false);
        if (fatherNode != null)
            fatherNode.setIsBlack(true);
        if (uncleNode != null)
            uncleNode.setIsBlack(true);

        validateInsert(grandFatherNode);
    }

    private void uncleBlackRebalance(RedBlackTreeNode node, RedBlackTreeNode father,
            RedBlackTreeNode grandFather, RedBlackTreeNode uncleNode) {
        father.setIsBlack(true);
        grandFather.setIsBlack(false);
        
        if (grandFather.getLeftChild() == father) {
            if (father.getLeftChild() == node) {
                rightRotation(father, grandFather);
            } else {
                leftRightRotation(node, father, grandFather);
            }
        } else {
            if (father.getRightChild() == node) {
                leftRotation(father, grandFather);
            } else {
                rightLeftRotation(node, father, grandFather);     
            }
        }
    }

    public void leftRotation(RedBlackTreeNode father, RedBlackTreeNode grandFather) {
        System.out.println("LR");
        father.setRootNode(grandFather.getRootNode());
        grandFather.setRightChild(father.getLeftChild());
        father.getLeftChild().setRootNode(grandFather);
        
        father.setLeftChild(grandFather);
        grandFather.setRootNode(father);

        if (this.getRoot() == grandFather) {
            this.setRoot(father);
        } else {
            RedBlackTreeNode grandGrandFather = (RedBlackTreeNode) father.getRootNode();
            if (grandGrandFather.getLeftChild() == grandFather) {
                grandGrandFather.setLeftChild(father);
            } else {
                grandGrandFather.setRightChild(father);
            }
        }
    }

    public void rightLeftRotation(RedBlackTreeNode node, RedBlackTreeNode father, RedBlackTreeNode grandFather) {
        rightRotation(node, father);
        leftRotation(node, grandFather);
    }

    public void rightRotation(RedBlackTreeNode father, RedBlackTreeNode grandFather) {
        System.out.println("RR");
        father.setRootNode(grandFather.getRootNode());
        grandFather.setLeftChild(father.getRightChild());
        father.getRightChild().setRootNode(grandFather);
        
        father.setRightChild(grandFather);
        grandFather.setRootNode(father);

        if (this.getRoot() == grandFather) {
            this.setRoot(father);
        } else {
            RedBlackTreeNode grandGrandFather = (RedBlackTreeNode) father.getRootNode();
            if (grandGrandFather.getLeftChild() == grandFather) {
                grandGrandFather.setLeftChild(father);
            } else {
                grandGrandFather.setRightChild(father);
            }
        }
    }

    public void leftRightRotation(RedBlackTreeNode node, RedBlackTreeNode father, RedBlackTreeNode grandFather) {
        leftRotation(node, father);
        rightRotation(node, grandFather);
    }

    @Override
    public void remove(Object value) throws RuntimeException {
        RedBlackTreeNode removed = (RedBlackTreeNode) this.search(value);
        if (removed.getObject() == null) throw new RuntimeException("Chave não encontradoa");
        
        RedBlackTreeNode removedRoot = (RedBlackTreeNode) removed.getRootNode();
        if (removedRoot == null) {
            this.setRoot(newEmptyChild(null));
            return;
        }
        boolean isLeftOfRoot = removedRoot.getLeftChild() == removed;
        RedBlackTreeNode aux = null;
        boolean originalIsBlack = removed.isBlack();
        
        if (isExternal(removed.getLeftChild()) && isExternal(removed.getRightChild())){
            aux = newEmptyChild(removedRoot);
            if (isLeftOfRoot) removedRoot.setLeftChild(aux);
            else removedRoot.setRightChild(aux);
            
        } else if (isExternal(removed.getLeftChild()) && !isExternal(removed.getRightChild())){
            aux = (RedBlackTreeNode) removed.getRightChild();
            transplant(removed, aux);
            
        } else if (!isExternal(removed.getLeftChild()) && isExternal(removed.getRightChild())){
            aux = (RedBlackTreeNode) removed.getLeftChild();
            transplant(removed, aux);
        } else {
            aux = (RedBlackTreeNode) removed.getRightChild();
            while (aux.getLeftChild().getObject() != null)
                aux = (RedBlackTreeNode) aux.getLeftChild();

            RedBlackTreeNode rightOfAux = (RedBlackTreeNode) aux.getRightChild();
            originalIsBlack = aux.isBlack();
            
            if (aux.getRootNode() == removed) {
                rightOfAux.setRootNode(aux);
            } else {
                transplant(aux, rightOfAux);
                aux.setRightChild(removed.getRightChild());
                aux.getRightChild().setRootNode(aux);
            }

            transplant(removed, aux);
            aux.setLeftChild(removed.getLeftChild());
            aux.getLeftChild().setRootNode(aux);
            aux.setIsBlack(removed.isBlack());

            aux = rightOfAux;
        }

        this.print();

        if (originalIsBlack){
            validateRemove(aux);
        }
    }

    public void validateRemove(RedBlackTreeNode node) {
        while (this.getRoot() != node && node.isBlack()) {
            if (node.getRootNode().getLeftChild() == node) {
                System.out.println("Removeu o filho esquerdo");
                RedBlackTreeNode sibling = node.getRootNode().getRightChild();
                if (!sibling.isBlack()) {
                    System.out.println("Irmao é vermelho");
                    sibling.setIsBlack(true);
                    node.getRootNode().setIsBlack(false);
                    leftRotation(sibling, node.getRootNode());
                    sibling = node.getRootNode().getRightChild();
                } 
                
                if (sibling.getLeftChild().isBlack() && sibling.getRightChild().isBlack()){
                    System.out.println("Ambos os filhos sao negros");
                    sibling.setIsBlack(false);
                    node = node.getRootNode();
                } else {
                    if (sibling.getRightChild().isBlack()) {
                        sibling.getLeftChild().setIsBlack(true);
                        sibling.setIsBlack(false);
                        rightRotation(sibling, sibling.getRootNode());
                        sibling = node.getRootNode().getRightChild();
                    }

                    sibling.setIsBlack(node.getRootNode().isBlack());
                    node.getRootNode().setIsBlack(true);
                    sibling.getRightChild().setIsBlack(true);
                    leftRotation(node.getRootNode(), node.getRootNode().getRootNode());
                    node = (RedBlackTreeNode) this.getRoot();
                } 
            } else {
                System.out.println("Removeu o filho direito");
                RedBlackTreeNode sibling = node.getRootNode().getLeftChild();
                if (!sibling.isBlack()) {
                    System.out.println("Irmao é vermelho");
                    sibling.setIsBlack(true);
                    node.getRootNode().setIsBlack(false);
                    rightRotation(sibling, node.getRootNode());
                    sibling = node.getRootNode().getLeftChild();
                } 

                if (sibling.getLeftChild().isBlack() && sibling.getRightChild().isBlack()){
                    System.out.println("Ambos os filhos sao negros");
                    sibling.setIsBlack(false);
                    node = node.getRootNode();
                } else {
                    if (sibling.getLeftChild().isBlack()) {
                        sibling.getRightChild().setIsBlack(true);
                        sibling.setIsBlack(false);
                        leftRotation(sibling, sibling.getRootNode());
                        sibling = node.getRootNode().getRightChild();
                    }
                    
                    sibling.setIsBlack(node.getRootNode().isBlack());
                    node.getRootNode().setIsBlack(true);
                    sibling.getLeftChild().setIsBlack(true);
                    rightRotation(node.getRootNode(), node.getRootNode().getRootNode());
                    node = (RedBlackTreeNode) this.getRoot();
                }
            }
        }
        node.setIsBlack(true);
    }

    private void transplant(RedBlackTreeNode a, RedBlackTreeNode b) {
        if (a.getRootNode() == null) {
            this.setRoot(b);
        } else if (a == a.getRootNode().getLeftChild()) {
            a.getRootNode().setLeftChild(b);
        } else {
            a.getRootNode().setRightChild(b);
        }
        b.setRootNode(a.getRootNode());
    }

    @Override
    public void print() {
        InternetTreePrinterRB.print(this);
    }
}
