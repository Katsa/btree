package xke.btree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Step1_ContainsTest extends OneDegreeIntegerBTreeTest {
    @Test
    public void shouldSucceedWhenElementIsInRootNode() {
        // Given
        @SuppressWarnings("unchecked")
        Node<Integer> root = nodeWithKeys(1, 2).build();

        // When
        setTreeRoot(root);

        // Then
        for (int key : new int[]{1, 2}) assertTrue(tree.contains(key));
    }

    @Test
    public void shouldFailWhenJustARootNodeAndElementNotInside() {
        // Given
        @SuppressWarnings("unchecked")
        Node<Integer> root = nodeWithKeys(2, 4).build();

        // When
        setTreeRoot(root);

        // Then
        for (int key : new int[]{1, 3, 5}) assertFalse(tree.contains(key));
    }

    @Test
    public void shouldSucceedWhenElementIsInChildNode() {
        // Given
        @SuppressWarnings("unchecked")
        Node<Integer> root = nodeWithKeys(3, 6).withChildren(
                nodeWithKeys(1, 2).build(),
                nodeWithKeys(4, 5).build(),
                nodeWithKeys(7, 8).build()
        ).build();

        // When
        setTreeRoot(root);

        // Then
        for (int key : new int[]{1, 2, 3, 4, 5, 6, 7, 8}) assertTrue(tree.contains(key));
    }

    @Test
    public void shouldFailWhenElementWouldBelongToChildNodeButIsNotPresent() {
        // Given
        @SuppressWarnings("unchecked")
        Node<Integer> root = nodeWithKeys(4, 8).withChildren(
                nodeWithKeys(1, 3).build(),
                nodeWithKeys(5, 7).build(),
                nodeWithKeys(9, 11).build()
        ).build();

        // When
        setTreeRoot(root);

        // Then
        for (int key : new int[]{2, 6, 10}) assertFalse(tree.contains(key));
    }
}
