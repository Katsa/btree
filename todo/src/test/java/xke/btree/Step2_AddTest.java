package xke.btree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class Step2_AddTest extends OneDegreeIntegerBTreeTest {
    @Test
    public void shouldNotAddIfAlreadyExistsInRoot() {
        // Given
        setTreeRoot(nodeWithKeys(3).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(4, 5).build()
        ).build());

        // Then
        assertFalse(tree.add(3));
    }

    @Test
    public void shouldNotAddIfAlreadyExistsInSubtree() {
        // Given
        setTreeRoot(nodeWithKeys(3).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(4, 5).build()
        ).build());

        // Then
        for (int key : new int[]{1, 2, 4, 5}) assertFalse(tree.add(key));
    }

    @Test
    public void shouldAddToSingleRootIfEnoughRoom() {
        // Given
        setTreeRoot(nodeWithKeys(3).build());

        // When
        tree.add(4);

        // Then
        assertTreeEquals(nodeWithKeys(3, 4).build());
    }

    @Test
    public void shouldAddToLeafIfEnoughRoom() {
        // Given
        setTreeRoot(nodeWithKeys(3).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(5).build()
        ).build());

        // When
        tree.addAll(2, 4);

        // Then
        assertTreeEquals(nodeWithKeys(3).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(4, 5).build()
        ).build());
    }

    @Test
    public void shouldSplitSingleRootWhenFull() {
        // Given
        setTreeRoot(nodeWithKeys(1, 2).build());

        // When
        tree.add(3);

        // Then
        assertTreeEquals(nodeWithKeys(2).withChildren(
                nodeWithKeys(1).build(),
                nodeWithKeys(3).build()
        ).build());
    }

    @Test
    public void shouldSplitLeafWhenFull() {
        // Given
        setTreeRoot(nodeWithKeys(3).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(4, 5).build()
        ).build());

        // When
        tree.add(6);

        // Then
        assertTreeEquals(nodeWithKeys(3, 5).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(4).build(),
                nodeWithKeys(6).build()
        ).build());
    }

    @Test
    public void shouldSplitInternalNodeWhenFull() {
        // Given
        setTreeRoot(nodeWithKeys(8).withChildren(
                nodeWithKeys(4, 6).withChildren(
                        nodeWithKeys(1, 2).build(),
                        nodeWithKeys(5).build(),
                        nodeWithKeys(7).build()
                ).build(),
                nodeWithKeys(10).withChildren(
                        nodeWithKeys(9).build(),
                        nodeWithKeys(11).build()
                ).build()
        ).build());

        // When
        tree.add(3);

        // Then
        assertTreeEquals(nodeWithKeys(4, 8).withChildren(
                nodeWithKeys(2).withChildren(
                        nodeWithKeys(1).build(),
                        nodeWithKeys(3).build()
                ).build(),
                nodeWithKeys(6).withChildren(
                        nodeWithKeys(5).build(),
                        nodeWithKeys(7).build()
                ).build(),
                nodeWithKeys(10).withChildren(
                        nodeWithKeys(9).build(),
                        nodeWithKeys(11).build()
                ).build()
        ).build());
    }
}
