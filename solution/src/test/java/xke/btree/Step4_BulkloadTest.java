package xke.btree;

import org.junit.Test;

public class Step4_BulkloadTest extends OneDegreeIntegerBTreeTest {
    @Test
    public void shouldBulkloadWhenElementsFitInRoot() {
        // Given
        tree = new BTree<>(1,
                1, 2);

        // Then
        assertTreeEquals(nodeWithKeys(1, 2).build());
    }

    @Test
    public void shouldBulkloadVariousLevels() {
        // Given
        tree = new BTree<>(1,
                10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

        // Then
        assertTreeEquals(nodeWithKeys(6).withChildren(
                nodeWithKeys(3).withChildren(
                        nodeWithKeys(1, 2).build(),
                        nodeWithKeys(4, 5).build()
                ).build(),
                nodeWithKeys(9).withChildren(
                        nodeWithKeys(7, 8).build(),
                        nodeWithKeys(10).build()
                ).build()
        ).build());
    }
}
