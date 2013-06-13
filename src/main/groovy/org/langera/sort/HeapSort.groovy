package org.langera.sort

import org.langera.tree.PriorityQueue
import org.langera.tree.PriorityQueueFactory

class HeapSort<T> implements Sort<T> {

    PriorityQueueFactory<T> factory

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        PriorityQueue<T> queue = factory.create(ordering)
        a.each { T item ->
            queue.insert(item)
        }
        List<T> results = []
        while (queue.size() > 0) {
            results << queue.extractMax()
        }
        return results.reverse()
    }
}
