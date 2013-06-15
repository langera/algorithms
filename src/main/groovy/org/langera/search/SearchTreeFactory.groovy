package org.langera.search


public interface SearchTreeFactory<T> {

    SearchTree<T> create(T item, Comparator<T> comparator)

}