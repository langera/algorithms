package org.langera.search


public interface SearchTreeFactory<T> {

    SearchTree<T> create(SearchTreeRoot<T> root, T item, Comparator<T> comparator)

}