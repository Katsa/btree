package xke.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Bulkloader<E extends Comparable<E>> {
    private final int minKeyCount;

    final Node<E> root;

    Bulkloader(int minKeysPerNode, E[] elements) {
        this.minKeyCount = minKeysPerNode;

        // TODO
        root = null;
    }
}
