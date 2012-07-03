# Algorithms

## Search
Find target node, then key (sequential or binary search)

![](../../raw/master/prez/content/algo_01_contains.jpg)

## Add
When target node not full: insert key, moving other keys if necessary

![](../../raw/master/prez/content/algo_02_add_when_room.jpg)

When target node full: move median key to upper level, split remaining keys into two child nodes

![](../../raw/master/prez/content/algo_03_add_with_split.jpg)

Splits can bubble up the tree

![](../../raw/master/prez/content/algo_04_propagate_splits.jpg)

## Remove
Removing from a leaf: simply delete the key.

![](../../raw/master/prez/content/algo_05_remove_from_leaf.jpg)

Removing from an internal node: swap key with righmost leaf key of left child, or leftmost leaf key of right child => back to previous case

![](../../raw/master/prez/content/algo_06_swap_rightmost_left_leaf.jpg)

A removal can leave the tree in an inconsistent state. There are various ways to rebalance it

# Rebalance
Steal from a sibling that has enough keys, "rotating" through the parent

![](../../raw/master/prez/content/algo_07_rebalance_borrow_sibling.jpg)

If there are no siblings or they both have the minimum cardinality, merge with a sibling, stealing the separating key from the parent

![](../../raw/master/prez/content/algo_08_rebalance_merge_parent_key.jpg)

Merges also propagate up the tree

![](../../raw/master/prez/content/algo_09_propagate_merges.jpg)

## Remove (cont'd)
If the root node is empty, replace it with its unique child.

![](../../raw/master/prez/content/algo_10_delete_empty_root.jpg)

## Bulk load
Construct a tree from scratch, when all the keys are known in advance.

### Leaf nodes
* sort the keys
* create an initial set of nodes. All must have one extra key, _except the last one_

![](../../raw/master/prez/content/algo_11_bulkload.jpg)

## Create next level
* take the last key of each existing node (except the last) to fill the new nodes
* again, overfill the new nodes, except the last one

![](../../raw/master/prez/content/algo_12_bulkload.png)

## Termination
Rince and repeat, until there is one node left.

![](../../raw/master/prez/content/algo_13_bulkload.png)

