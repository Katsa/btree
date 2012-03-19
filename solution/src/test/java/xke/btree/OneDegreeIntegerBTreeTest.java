package xke.btree;

import static org.junit.Assert.assertEquals;

public class OneDegreeIntegerBTreeTest {
    protected BTree<Integer> tree;

    protected void assertTreeEquals(Node<Integer> expected) {
        assertEquals(expected, tree.root);
    }

    protected NodeBuilder nodeWithKeys(Integer... keys) {
        return new NodeBuilder(keys);
    }

    static class NodeBuilder {
        Integer[] keys;
        Node<Integer>[] children;

        public NodeBuilder(Integer... keys) {
            this.keys = keys;
        }

        @SafeVarargs
        public final NodeBuilder withChildren(Node<Integer>... children) {
            this.children = children;
            return this;
        }

        public Node<Integer> build() {
            Node<Integer> node = new Node<>(1);
            node.keyCount = this.keys.length;
            System.arraycopy(this.keys, 0, node.keys, 0, this.keys.length);
            if (this.children != null)
                System.arraycopy(this.children, 0, node.children, 0, this.children.length);
            return node;
        }
    }

    public void setTreeRoot(Node<Integer> root) {
        this.tree = new BTree<>(root);
    }
}
