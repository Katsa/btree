package xke.btree;

import java.util.Arrays;

class Node<E extends Comparable<E>> {
    final int minKeyCount;
    private final int maxKeyCount;

    // Use non-generic containers, we'll make sure only the right types end up into them
    final Object[] keys;
    final Node<?>[] children;

    char label;
    int keyCount;

    Node(int minKeyCount) {
        this.minKeyCount = minKeyCount;
        this.maxKeyCount = 2 * minKeyCount;

        this.keys = new Object[maxKeyCount + 1];
        this.children = new Node<?>[maxKeyCount + 2];

        this.keyCount = 0;
    }

    boolean contains(E e) {
        // TODO
        return false;
    }

    AddResult<E> add(E e) {
        // TODO
        return AddResult.alreadyExisted();
    }

    public boolean remove(E e) {
        //TODO
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Node<?>) {
            Node<?> that = (Node<?>) other;
            return this.minKeyCount == that.minKeyCount &&
                    Arrays.deepEquals(this.keys, that.keys) &&
                    Arrays.deepEquals(this.children, that.children);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode = 31 * hashCode + minKeyCount;
        for (Object key : keys)
            if (key != null) hashCode = 31 * hashCode + key.hashCode();
        for (Node<?> child : children)
            if (child != null) hashCode = 31 * hashCode + child.hashCode();
        return hashCode;
    }

    @Override
    public String toString() {
        return Nodes.toString(this);
    }

    @SuppressWarnings("unchecked")
    private E keys(int i) {
        return (E) keys[i];
    }

    @SuppressWarnings("unchecked")
    Node<E> children(int i) {
        return (Node<E>) children[i];
    }

    /**
     * Returns the index of the first key that is greater than or equal to a given value.
     * If all keys are smaller, return the index after the last key.
     */
    private int firstHigher(E e) {
        return 0;
    }
}