package org.langera.search

import static java.lang.Math.max

class RedBlackTree<T> implements SearchTree<T> {

    private Comparator<T> comparator
    RedBlackTree left
    RedBlackTree right
    RedBlackTree parent
    SearchTreeRoot<T> root
    T item
    boolean redNode = true

    RedBlackTree(final SearchTreeRoot<T> root, final T item, final Comparator<T> comparator) {
        this.item = item
        this.root = root
        this.comparator = comparator
    }

    boolean contains(T item) {
        RedBlackTree<T> tree = this
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

    boolean red() {
        return redNode
    }

    boolean black() {
        return !redNode
    }

    void makeRed() {
        redNode = true
    }

    void makeBlack() {
        redNode = false
    }


    @Override
    T extractMax() {
        throw new UnsupportedOperationException()
    }

    @Override
    void insert(final T item) {
        final RedBlackTree newNode = new RedBlackTree(root, item, comparator)
        RedBlackTree<T> tree = this
        RedBlackTree<T> treeParent = null
        int value = 0
        while (tree != null) {
            value = comparator.compare(tree.item, item)
            if (value > 0) {
                treeParent = tree
                tree = tree.right
            }
            else {
                treeParent = tree
                tree = tree.left
            }
        }
        newNode.parent = treeParent
        if (value > 0) {
            treeParent.right = newNode
        }
        else {
            treeParent.left = newNode
        }
        redBlackTreeFixUp(newNode)
    }

    void redBlackTreeFixUp(RedBlackTree<T> newNode) {
        RedBlackTree<T> uncle
        while (newNode.parent.red()) {
            if (newNode.parent == newNode.parent.parent.left) {
                uncle = newNode.parent.parent.right
                if (uncle && uncle.red()) {
                    newNode.parent.makeBlack()
                    uncle.makeBlack()
                    newNode.parent.parent.makeRed()
                }
                else {
                    if (newNode == newNode.parent.right) {
                        newNode = newNode.parent
                        leftRotate(newNode)
                    }
                    newNode.parent.makeBlack()
                    newNode.parent.parent.makeRed()
                    rightRotate(newNode.parent.parent)
                }
            }
            else {
                uncle = newNode.parent.parent.left
                if (uncle && uncle.red()) {
                    newNode.parent.makeBlack()
                    uncle.makeBlack()
                    newNode.parent.parent.makeRed()
                }
                else {
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent
                        rightRotate(newNode)
                    }
                    newNode.parent.makeBlack()
                    newNode.parent.parent.makeRed()
                    leftRotate(newNode.parent.parent)
                }
            }
        }
    }

    void leftRotate(RedBlackTree<T> tree) {
        RedBlackTree<T> toRotate = tree.right
        tree.right = toRotate.left
        if (toRotate.left != null) {
            toRotate.left.parent = tree
        }
        toRotate.parent = tree.parent
        if (tree.parent == null) {
            root.root = toRotate
        }
        else if (tree == tree.parent.left) {
            tree.parent.left = toRotate
        }
        else {
            tree.parent.right = toRotate
        }
        toRotate.left = tree
        tree.parent = toRotate
    }

    void rightRotate(RedBlackTree<T> tree) {
        RedBlackTree<T> toRotate = tree.left
        tree.left = toRotate.right
        if (toRotate.right != null) {
            toRotate.right.parent = tree
        }
        toRotate.parent = tree.parent
        if (tree.parent == null) {
            root.root = toRotate
        }
        else if (tree == tree.parent.left) {
            tree.parent.left = toRotate
        }
        else {
            tree.parent.right = toRotate
        }
        toRotate.right = tree
        tree.parent = toRotate
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
