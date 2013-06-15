package org.langera.search

import groovy.transform.PackageScope

@PackageScope class IterativeBinarySearchTree<T> implements SearchTree<T> {

    private Comparator<T> comparator
    IterativeBinarySearchTree left
    IterativeBinarySearchTree right
    T item

    IterativeBinarySearchTree(final T item, final Comparator<T> comparator) {
        this.item = item
        this.comparator = comparator
    }

    boolean contains(T item) {
        IterativeBinarySearchTree<T> tree = this
        while (tree != null) {
            int value = comparator.compare(tree.item, item)
            if (value > 0) {
                tree = tree.right
            }
            else if (value < 0) {
                tree = tree.left
            }
            else {
                return true;
            }
        }
        return false
    }

    @Override
    T extractMax() {
        throw new UnsupportedOperationException()
    }

    @Override
    void insert(final T item) {
        IterativeBinarySearchTree<T> tree = this
        IterativeBinarySearchTree<T> parent = null
        int value = 0
        while (tree != null) {
            value = comparator.compare(tree.item, item)
            if (value > 0) {
                parent = tree
                tree = tree.right
            }
            else {
                parent = tree
                tree = tree.left
            }
        }
        if (value > 0) {
            parent.right = new IterativeBinarySearchTree(item, comparator)
        }
        else {
            parent.left = new IterativeBinarySearchTree(item, comparator)
        }
    }

    @Override
    int size() {
        return ((right) ? right.size() : 0) + ((left) ? left.size() : 0) + 1
    }
}
