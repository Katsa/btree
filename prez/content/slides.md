!SLIDE center
##Let's code a B-tree
![Tree](tree.jpg)
<div style="margin-top: -20px;" xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/" about="http://i.images.cdn.fotopedia.com/flickr-564897564-hd/World_Heritage_Sites/America/South_America/Argentina/Los_Glaciares/Red_Tree_-_Los_Glaciares_National_Park.jpg"><span property="dct:title">Red Tree - Los Glaciares National Park</span> (<a rel="cc:attributionURL" property="cc:attributionName" href="http://www.fotopedia.com/users/80RxsWcKz0M">Javier Vidal</a>) / <a rel="license" href="http://creativecommons.org/licenses/by/2.0/">CC BY 2.0</a></div>

!SLIDE subsection
# What is a B-tree?
(and why would I want one?)

!SLIDE
[http://en.wikipedia.org/wiki/B-tree](http://en.wikipedia.org/wiki/B-tree):

> A tree data structure that **keeps data sorted** and allows searches, sequential access, insertions and deletions in **logarithmic time** [...] optimized for systems that read and write **large blocks of data**.

!SLIDE subsection
# Logarithmic time???

!SLIDE
# Asymptotic complexity
* RAM (Random Access Machine) model: one basic operation (+, =...) => one time step
* For a given algorithm:
  * total number of time steps = f(input size)
  * notation for worst-case complexity: O(f(n))

!SLIDE center
# Orders of magnitude
Assuming 1 time step = 1 nanosecond:

![table](complexities.png)

[http://www.cs.sunysb.edu/~skiena/373/newlectures/lecture3.pdf](http://www.cs.sunysb.edu/~skiena/373/newlectures/lecture3.pdf)

!SLIDE subsection
# Searchable data structures

!SLIDE center
# Unsorted array
* insertion (append): O(1)

![](unsorted_array_insert.jpg)

* search (sequential traversal): O(n)

![](unsorted_array_search.jpg)

!SLIDE center
# Sorted array
* binary search: O(log n)

![](sorted_array_search.jpg)

* insertion: O(n)

![](sorted_array_insert.jpg)

!SLIDE center
# Binary tree
* best case: O(log n)
* worst case (unbalanced): O(n)

![](binary_trees.jpg)

!SLIDE center
# Self-balancing binary tree
* mutative operations include steps to keep the tree balanced
* various implementations provide search, insertion and removal in O(log n)

  Ex: `java.util.TreeMap` => red-black tree

!SLIDE center
##If self-balancing binary trees already provide all operations in O(log n), what do we need B-trees for?

!SLIDE
# Memory locality
The limits of the RAM model

* memory access is not constant
  * disk blocks
  * virtual memory pages
  * (cache lines)
* when accessing values sequentially, performance will benefit if these values are stored in nearby memory locations

!SLIDE subsection
# B-tree definition

!SLIDE center smaller
# B-tree of degree m
* each node has between _m/2_ and _m_ children
* the root has at least two children if it is not a leaf node
* a non-leaf node with _k_ children contains _k-1_ keys
* all leaves appear at the same level, and carry information

![](btree_m4.jpg)

!SLIDE center
# Implementation
* children: array of size m + 1 (extra bucket will simplify some operations)
* keys: array of size m/2 + 1
* use raw types, expose typesafe API

!SLIDE subsection
# Uses of B-trees

!SLIDE
# Database indices

    @@@javascript
    > db.users.ensureIndex( {age: 1} );
    
    > db.users.find( {age: {$gte: 18,
                            $lt: 50}} )
              .explain();

    {
      "cursor" : "BtreeCursor age_1",
      ...
    }

!SLIDE center
# File systems

* map file blocks to disk blocks
* HFS+, NTFS, ext4

!SLIDE subsection
# Algorithms
[http://en.wikipedia.org/wiki/B-tree#Algorithms](http://en.wikipedia.org/wiki/B-tree#Algorithms)
[http://fr.wikipedia.org/wiki/Arbre_B#Op.C3.A9rations](http://fr.wikipedia.org/wiki/Arbre_B#Op.C3.A9rations)

!SLIDE center
# Search
Find target node, then key (sequential or binary search)

![](algo_01_contains.jpg)

!SLIDE center
# Add
When target node not full: insert key, moving other keys if necessary

![](algo_02_add_when_room.jpg)

!SLIDE center
# Add
When target node full: move median key to upper level, split remaining keys into two child nodes

![](algo_03_add_with_split.jpg)

!SLIDE center
# Add
Splits can bubble up the tree

![](algo_04_propagate_splits.jpg)

!SLIDE center
# Remove
Removing from a leaf: simply delete the key.

![](algo_05_remove_from_leaf.jpg)

!SLIDE center
# Remove
Removing from an internal node: swap key with righmost leaf key of left child, or leftmost leaf key of right child => back to previous case

![](algo_06_swap_rightmost_left_leaf.jpg)

!SLIDE center
# Rebalance
A removal can leave the tree in an inconsistent state. There are various ways to rebalance it

!SLIDE center
# Rebalance
Steal from a sibling that has enough keys, "rotating" through the parent

![](algo_07_rebalance_borrow_sibling.jpg)

!SLIDE center
# Rebalance
If there are no siblings or they both have the minimum cardinality, merge with a sibling, stealing the separating key from the parent

![](algo_08_rebalance_merge_parent_key.jpg)

!SLIDE center
# Rebalance
Merges also propagate up the tree

![](algo_09_propagate_merges.jpg)

!SLIDE center
# Removal
If the root node is empty, replace it with its unique child.

![](algo_10_delete_empty_root.jpg)

!SLIDE subsection
# Your turn!
