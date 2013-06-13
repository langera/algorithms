package org.langera.tree

public interface SearchTree<T> {

    void insert(T item)
    void delete(T item)
    int size()
    T extractMax()
    T search(int key)

    List<T> inOrder()
    List<T> preOrder()
    List<T> postOrder()

}