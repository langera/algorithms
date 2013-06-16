package org.langera.search

import static java.lang.Math.max

class RecursiveBinarySearchTree<T> implements SearchTree<T> {

    private Comparator<T> comparator
    RecursiveBinarySearchTree left
    RecursiveBinarySearchTree right
    T item

    RecursiveBinarySearchTree(final T item, final Comparator<T> comparator) {
        this.item = item
        this.comparator = comparator
    }

    boolean contains(T item) {
        int value = comparator.compare(this.item, item)
        if (value > 0) {
            return (right) ? right.contains(item) : false
        }
        else if (value < 0) {
            return (left) ? left.contains(item) : false
        }
        else {
            return true;
        }
    }

    @Override
    T extractMax() {
        throw new UnsupportedOperationException()
    }

    @Override
    void insert(final T item) {
        int value = comparator.compare(this.item, item)
        if (value > 0) {
            if (right) {
                right.insert(item)
            }
            else {
                right = new RecursiveBinarySearchTree(item, comparator)
            }
        }
        else {
            if (left) {
                left.insert(item)
            }
            else {
                left = new RecursiveBinarySearchTree(item, comparator)
            }
        }
    }

    @Override
    int size() {
        return ((right) ? right.size() : 0) + ((left) ? left.size() : 0) + 1
    }

    @Override
    int maxDepth() {
        return max((left) ? left.maxDepth() : 0, (right) ? right.maxDepth() : 0) + 1
    }
}
