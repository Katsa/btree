package xke.btree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Step3_RemoveTest extends OneDegreeIntegerBTreeTest {
    @Test
    public void shouldNotRemoveWhenElementNotPresent() {
        // Given
        tree = new BTree<>(1);
        tree.addAll(1, 2, 3, 4, 5, 6, 7, 8);

        // When
        boolean removed = tree.remove(9);

        // Then
        assertFalse(removed);
    }

    @Test
    public void shouldRemoveFromLeaf() {
        // Given
        setTreeRoot(nodeWithKeys(2).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3, 4).build()
        ).build());

        // When
        boolean removed = tree.remove(4);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(2).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3).build()
        ).build());
    }

    @Test
    public void shouldSwapWithLeftChild() {
        // Given
        setTreeRoot(nodeWithKeys(3).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(4).build()
        ).build());

        // When
        boolean removed = tree.remove(3);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(2).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(4).build()
        ).build());
    }

    @Test
    public void shouldSwapWithRightChild() {
        // Given
        setTreeRoot(nodeWithKeys(2).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3, 4).build()
        ).build());

        // When
        boolean removed = tree.remove(2);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(3).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(4).build()
        ).build());
    }

    @Test
    public void shouldSwapWithRightMostLeafOfLeftChildIfEnoughKeys() {
        // Given
        setTreeRoot(nodeWithKeys(5).withChildren(
                nodeWithKeys(2).withChildren(
                        nodeWithKeys(1).build(),
                        nodeWithKeys(3, 4).build()
                ).build(),
                nodeWithKeys(7).withChildren(
                        nodeWithKeys(6).build(),
                        nodeWithKeys(8).build()
                ).build()
        ).build());

        // When
        boolean removed = tree.remove(5);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(4).withChildren(
                nodeWithKeys(2).withChildren(
                        nodeWithKeys(1).build(),
                        nodeWithKeys(3).build()
                ).build(),
                nodeWithKeys(7).withChildren(
                        nodeWithKeys(6).build(),
                        nodeWithKeys(8).build()
                ).build()
        ).build());
    }

    @Test
    public void shouldSwapWithLeftMostLeafOfRightChildOtherwise() {
        // Given
        setTreeRoot(nodeWithKeys(4).withChildren(
                nodeWithKeys(2).withChildren(
                        nodeWithKeys(1).build(),
                        nodeWithKeys(3).build()
                ).build(),
                nodeWithKeys(7).withChildren(
                        nodeWithKeys(5, 6).build(),
                        nodeWithKeys(8).build()
                ).build()
        ).build());

        // When
        boolean removed = tree.remove(4);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(5).withChildren(
                nodeWithKeys(2).withChildren(
                        nodeWithKeys(1).build(),
                        nodeWithKeys(3).build()
                ).build(),
                nodeWithKeys(7).withChildren(
                        nodeWithKeys(6).build(),
                        nodeWithKeys(8).build()
                ).build()
        ).build());
    }

    @Test
    public void shouldBorrowKeyFromLeftSibling() {
        // Given
        setTreeRoot(nodeWithKeys(3).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(4).build()
        ).build());

        // When
        boolean removed = tree.remove(4);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(2).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3).build()
        ).build());
    }

    @Test
    public void shouldBorrowKeyFromRightSibling() {
        // Given
        setTreeRoot(nodeWithKeys(2).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3, 4).build()
        ).build());

        // When
        boolean removed = tree.remove(1);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(3).withChildren(
                nodeWithKeys(2).build(),
                nodeWithKeys(4).build()
        ).build());
    }

    @Test
    public void shouldMergeChildWithLeftSiblingAndAKeyFromTheParent() {
        // Given
        setTreeRoot(nodeWithKeys(2, 4).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3).build(),
                nodeWithKeys(5).build()
        ).build());

        // When
        boolean removed = tree.remove(3);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(4).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(5).build()
        ).build());
    }

    @Test
    public void shouldMergeChildWithRightSiblingAndAKeyFromTheParent() {
        // Given
        setTreeRoot(nodeWithKeys(2, 4).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3).build(),
                nodeWithKeys(5).build()
        ).build());

        // When
        boolean removed = tree.remove(1);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(4).withChildren(
                nodeWithKeys(2, 3).build(),
                nodeWithKeys(5).build()
        ).build());
    }

    @Test
    public void shouldPropagateMergesToRightUpTheTree() {
        // Given
        setTreeRoot(nodeWithKeys(4).withChildren(
                nodeWithKeys(2).withChildren(
                        nodeWithKeys(1).build(),
                        nodeWithKeys(3).build()
                ).build(),
                nodeWithKeys(6).withChildren(
                        nodeWithKeys(5).build(),
                        nodeWithKeys(7).build()
                ).build()
        ).build());

        // When
        boolean removed = tree.remove(1);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(4, 6).withChildren(
                nodeWithKeys(2, 3).build(),
                nodeWithKeys(5).build(),
                nodeWithKeys(7).build()
        ).build());
    }

    @Test
    public void shouldPropagateMergesToLeftUpTheTree() {
        // Given
        setTreeRoot(nodeWithKeys(4).withChildren(
                nodeWithKeys(2).withChildren(
                        nodeWithKeys(1).build(),
                        nodeWithKeys(3).build()
                ).build(),
                nodeWithKeys(6).withChildren(
                        nodeWithKeys(5).build(),
                        nodeWithKeys(7).build()
                ).build()
        ).build());

        // When
        boolean removed = tree.remove(7);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(2, 4).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3).build(),
                nodeWithKeys(5, 6).build()
        ).build());
    }

    @Test
    public void shouldMergeBackRoot() {
        // Given
        setTreeRoot(nodeWithKeys(2).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3).build()
        ).build());

        // When
        boolean removed = tree.remove(1);

        // Then
        assertTrue(removed);
        assertTreeEquals(nodeWithKeys(2, 3).build());
    }
}