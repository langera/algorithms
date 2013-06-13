package org.langera.tree

import org.langera.sort.SortOrdering

class BinomialHeap<T> implements PriorityQueue<T> {

    List<BinomialTree<T>> heap = []
    SortOrdering<T> ordering

    @Override
    T extractMax() {
        BinomialTree<T> maxItemTree = null
        int max
        heap.each { BinomialTree<T> tree ->
            if (tree && (!maxItemTree || ordering.sortValue(tree.item) > max)) {
                max = ordering.sortValue(tree.item)
                maxItemTree = tree
            }
        }
        if (maxItemTree) {
            heap[maxItemTree.level()] = null
            maxItemTree.children.each { BinomialTree<T> child ->
                insertToHeap(child)
            }
        }
        return maxItemTree.item
    }

    @Override
    void insert(final T item) {
        BinomialTree<T> newTree = new BinomialTree<T>(item)
        insertToHeap(newTree)
    }

    @Override
    int size() {
        return heap.inject(0) { int size, BinomialTree<T> tree ->
            return size + ((tree) ? 2.power(tree.level()) : 0)
        }
    }

    private void insertToHeap(BinomialTree<T> tree) {
        int level = tree.level()
        if (heap[level]) {
            BinomialTree<T> combinedTree = union(tree, heap[level])
            heap[level] = null
            insertToHeap(combinedTree)
        }
        else {
            heap[level] = tree
        }
    }

    private BinomialTree union(BinomialTree<T> a, BinomialTree<T> b) {
        if (ordering.sortValue(a.item) > ordering.sortValue(b.item)) {
            a.insertChild(b)
            return a
        }
        else {
            b.insertChild(a)
            return b
        }
    }

    private static class BinomialTree<T> {

        T item
        List<BinomialTree<T>> children = []

        BinomialTree(final T item) {
            this.item = item
        }

        int level() {
            children.size()
        }

        void insertChild(BinomialTree<T> child) {
            children.add(child)
        }
    }
}
