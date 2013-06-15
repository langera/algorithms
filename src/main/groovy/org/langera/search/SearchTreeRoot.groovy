package org.langera.search


class SearchTreeRoot<T> implements SearchTree<T> {

    private Comparator<T> comparator
    private SearchTreeFactory<T> factory
    private SearchTree<T> root

    SearchTreeRoot(final Comparator<T> comparator, final SearchTreeFactory<T> factory) {
        this.comparator = comparator
        this.factory = factory
    }

    @Override
    boolean contains(final T item) {
        return (root) ? root.contains(item) : false
    }

    @Override
    T extractMax() {
        return (root) ? root.extractMax() : null
    }

    @Override
    void insert(final T item) {
        if (root == null) {
            root = factory.create(item, comparator)
        }
        else {
            root.insert(item)
        }
    }

    @Override
    int size() {
        return (root) ? root.size() : 0
    }

    void clear() {
        root = null
    }

    @Override
    String toString() {
        return (root) ? root.class.name : factory.create(null, null).class.name
    }
}
