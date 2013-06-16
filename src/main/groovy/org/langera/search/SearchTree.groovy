package org.langera.search

interface SearchTree<T> extends PriorityQueue<T>, Dictionary<T> {

    int maxDepth()
}