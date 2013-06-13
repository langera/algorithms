package org.langera.tree

public interface PriorityQueue<T> {

    T extractMax()

    void insert(T item)

    int size()
}