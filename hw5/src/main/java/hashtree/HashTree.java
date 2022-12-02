package hashtree;

public interface HashTree<E extends Comparable<E>> {
    void add(E element);

    boolean contains(E element);

    E getMin();

    void remove(E element);
}
