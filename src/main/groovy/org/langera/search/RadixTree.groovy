package org.langera.search

import static java.lang.Math.max

class RadixTree implements SearchTree<String> {

    boolean endOfWord = false
    RadixTree[] children = new RadixTree[((int) 'z') + 1]

    @Override
    boolean contains(final String item) {
        return containsInternal(item, 0)
    }

    private boolean containsInternal(final String item, int index) {
        if (index == item.length()) {
            return endOfWord
        }
        else {
            char c = item.charAt(index)
            return (children[c]) ? children[c].containsInternal(item, index + 1) : false
        }
    }

    @Override
    String extractMax() {
        throw new UnsupportedOperationException()
    }

    @Override
    void insert(final String item) {
        insertInternal(item, 0)
    }

    private void insertInternal(final String item, int index) {
        if (index == item.length()) {
            endOfWord = true
        }
        else {
            char c = item.charAt(index)
            if (!children[c]) {
                children[c] = new RadixTree()
            }
            children[c].insertInternal(item, index + 1)
        }
    }

    @Override
    int size() {
        int size = children.inject(0) { acc, RadixTree child ->
            acc + ((child) ? child.size() : 0)
        }
        return size + 1
    }


    @Override
    int maxDepth() {
        int maxDepth = 0

        children.each { RadixTree child ->
            if (child) {
                 maxDepth = max(maxDepth, child.maxDepth() + 1)
            }
        }
        return maxDepth
    }
}
