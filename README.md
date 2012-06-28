[B-Trees](http://en.wikipedia.org/wiki/B-tree) are balanced tree data structures, used mainly in databases and operating systems.

I looked into B-Trees out of curiosity while reading about MongoDB, and then decided to prepare this workshop for our monthly get-together at [Xebia](http://www.xebia.fr/).

Contents:

* `prez`: a [showoff](https://github.com/schacon/showoff) presentation, with crappy diagrams explaining the algorithms (search, insertion, removal and bulk loading).
* `solution`: a (very naive) implementation in Java. Disclaimer: the goal of this project is to understand how the algorithms work, not to provide an actual implementation. In particular, storing keys as Java objects on the heap is quite likely to ruin the whole purpose of data locality; a real-world version would probably use some form of off-heap storage (like memory-mapped files).
* `todo`: the same as above, with just the unit tests and a skeletal implementation.

The code requires Java 7, but could easily be translated for 5 or 6.
