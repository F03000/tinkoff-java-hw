package hashtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HashTreeImpl<E extends Comparable<E>> implements HashTree<E> {

    private Node<E> root;
    private final List<Node<E>> values;

    public HashTreeImpl(int capacity) {
        this.values = new ArrayList<>(Collections.nCopies(capacity, null));
    }

    @Override
    public void add(E element) {
        int elementIndexInValuesArray = getIndexInValuesArray(element);
        if (values.get(elementIndexInValuesArray) != null) {
            return;
        }
        Node<E> node = new Node<>(element);
        values.set(elementIndexInValuesArray, node);
        this.root = addElementInTree(node, root);
    }

    private int getIndexInValuesArray(E element) {
        return element.hashCode() % values.size();
    }

    private Node<E> addElementInTree(Node<E> insertedNode, Node<E> currentNode) {
        if (currentNode == null) {
            return insertedNode;
        }
        if (currentNode.getValue().compareTo(insertedNode.getValue()) >= 0) {
            addNodeToPlace(insertedNode, currentNode);
            return insertedNode;
        }
        if (currentNode.getLeftChild() == null) {
            currentNode.setLeftChild(insertedNode);
        } else if (currentNode.getRightChild() == null) {
            currentNode.setRightChild(insertedNode);
        } else if (currentNode.getLeftChild().getDepth() < currentNode.getRightChild().getDepth()) {
            currentNode.setLeftChild(addElementInTree(insertedNode, currentNode.getLeftChild()));
        } else {
            currentNode.setRightChild(addElementInTree(insertedNode, currentNode.getRightChild()));
        }
        return currentNode;
    }

    private void addNodeToPlace(Node<E> insertedNode, Node<E> currentNode) {
        insertedNode.setLeftChild(currentNode);
        if (isRightChildMoreSuitableForSet(currentNode)) {
            insertedNode.setRightChild(currentNode.getRightChild());
            currentNode.setRightChild(null);
        } else if (isLeftChildMoreSuitableForSet(currentNode)) {
            insertedNode.setRightChild(currentNode.getLeftChild());
            currentNode.setLeftChild(null);
        }
        insertedNode.refreshDepthRecursively();
    }

    private boolean isLeftChildMoreSuitableForSet(Node<E> currentNode) {
        return currentNode.getLeftChild() != null
                && currentNode.getRightChild() == null
                ||
                (currentNode.getLeftChild() != null
                        && currentNode.getRightChild() != null
                        && currentNode.getRightChild().getDepth() < currentNode.getLeftChild().getDepth());
    }

    private boolean isRightChildMoreSuitableForSet(Node<E> currentNode) {
        return currentNode.getLeftChild() == null
                && currentNode.getRightChild() != null
                ||
                (currentNode.getLeftChild() != null
                        && currentNode.getRightChild() != null
                        && currentNode.getRightChild().getDepth() > currentNode.getLeftChild().getDepth());
    }

    @Override
    public boolean contains(E element) {
        return values.get(getIndexInValuesArray(element)) != null;
    }

    @Override
    public E getMin() {
        return root.getValue();
    }

    @Override
    public void remove(E element) {
        int elementIndexInValuesArray = getIndexInValuesArray(element);
        Node<E> elementNode = values.get(elementIndexInValuesArray);
        values.set(elementIndexInValuesArray, null);
        if (elementNode == null) {
            return;
        }
        innerRemove(elementNode);
    }

    public void innerRemove(Node<E> elementNode) {
        Node<E> parent = elementNode.getParent();
        Node<E> leftChild = elementNode.getLeftChild();
        Node<E> rightChild = elementNode.getRightChild();
        if (leftChild == null && rightChild == null) {
            removeLeaf(elementNode, parent);
        } else if (leftChild == null) {
            if (checkIfLeftChild(elementNode)) {
                parent.setLeftChild(elementNode.getRightChild());
            } else {
                parent.setRightChild(elementNode.getRightChild());
            }
        } else if (rightChild == null) {
            if (checkIfLeftChild(elementNode)) {
                parent.setLeftChild(elementNode.getLeftChild());
            } else {
                parent.setRightChild(elementNode.getLeftChild());
            }
        } else {
            propagateNodeDown(elementNode, leftChild, rightChild);
            innerRemove(elementNode);
        }
    }

    private boolean checkIfLeftChild(Node<E> elementNode) {
        return elementNode.getParent().getLeftChild() == elementNode;
    }

    private void propagateNodeDown(Node<E> elementNode, Node<E> leftChild, Node<E> rightChild) {
        if (leftChild.getValue().compareTo(rightChild.getValue()) <= 0) {
            switchNodes(elementNode, leftChild);
            if (elementNode.getDepth() < rightChild.getDepth()) {
                switchNodes(leftChild, rightChild);
            }
        } else {
            switchNodes(elementNode, rightChild);
            if (elementNode.getDepth() < leftChild.getDepth()) {
                switchNodes(rightChild, leftChild);
            }
        }
    }

    private void removeLeaf(Node<E> elementNode, Node<E> parent) {
        if (parent == null) {
            root = null;
        } else {
            if (checkIfLeftChild(elementNode)) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        }
    }

    private void switchNodes(Node<E> exParentNode, Node<E> exChildNode) {
        if (this.root == exParentNode) {
            this.root = exChildNode;
        }
        Node<E> copyOfExParentNode = new Node<>(exParentNode);
        if (exParentNode.getLeftChild() == exChildNode) {
            switchNodeWithLeftChild(exParentNode, exChildNode, copyOfExParentNode);
        } else {
            switchNodeWithRightChild(exParentNode, exChildNode, copyOfExParentNode);
        }
    }

    private void switchNodeWithRightChild(Node<E> exParentNode, Node<E> exChildNode, Node<E> copyOfExParentNode) {
        exParentNode.setLeftChild(exChildNode.getLeftChild());
        exParentNode.setRightChild(exChildNode.getRightChild());
        exParentNode.setParent(exChildNode);

        exChildNode.setLeftChild(copyOfExParentNode.getLeftChild());
        exChildNode.setRightChild(exParentNode);
        exChildNode.setParent(copyOfExParentNode.getParent());
    }

    private void switchNodeWithLeftChild(Node<E> exParentNode, Node<E> exChildNode, Node<E> copyOfExParentNode) {
        exParentNode.setLeftChild(exChildNode.getLeftChild());
        exParentNode.setRightChild(exChildNode.getRightChild());
        exParentNode.setParent(exChildNode);

        exChildNode.setLeftChild(exParentNode);
        exChildNode.setRightChild(copyOfExParentNode.getRightChild());
        exChildNode.setParent(copyOfExParentNode.getParent());
    }
}
