package org.langera.search

public interface PriorityQueue<T> {

    T extractMax()

    void insert(T item)

    int size()
}