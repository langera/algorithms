package org.langera.tree

import org.langera.sort.SortOrdering

public interface PriorityQueueFactory<T> {

    PriorityQueue<T> create(SortOrdering<T> ordering)
}