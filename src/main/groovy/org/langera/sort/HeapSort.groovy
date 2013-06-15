package org.langera.sort

import org.langera.search.PriorityQueue
import org.langera.search.PriorityQueueFactory

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

    @Override
    String toString() {
        return "${this.class.name}(${factory.create(null).class.name})"
    }
}
