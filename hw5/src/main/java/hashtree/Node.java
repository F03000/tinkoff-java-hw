package hashtree;

public class Node<E extends Comparable<E>> {
    private Node<E> parent;
    private Node<E> leftChild;
    private Node<E> rightChild;
    private final E value;
    private int depth;

    public Node(E element) {
        this.value = element;
        this.depth = 1;
    }

    Node(Node<E> node) {
        this.parent = node.parent;
        this.leftChild = node.leftChild;
        this.rightChild = node.rightChild;
        this.value = node.value;
        this.depth = node.depth;
    }

    public void refreshDepthRecursively() {
        refreshDepth();
        if (parent != null) {
            parent.refreshDepth();
        }
    }

    private void refreshDepth() {
        if (leftChild == null || rightChild == null) {
            depth = 1;
        } else {
            depth = Math.min(leftChild.depth, rightChild.depth) + 1;
        }
    }

    public Node<E> getParent() {
        return parent;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
        if (parent != null) {
            parent.refreshDepth();
        }
    }

    public Node<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<E> leftChild) {
        this.leftChild = leftChild;
        if (leftChild != null) {
            leftChild.setParent(this);
        }
        refreshDepth();
    }

    public Node<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<E> rightChild) {
        this.rightChild = rightChild;
        if (rightChild != null) {
            rightChild.setParent(this);
        }
        refreshDepth();
    }

    public E getValue() {
        return value;
    }

    public int getDepth() {
        return depth;
    }

}
