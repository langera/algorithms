package org.langera.sort

class HeapSort<T> implements Sort<T> {

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        MaxHeap<T> maxHeap = new MaxHeap<T>(ordering: ordering)
        a.each { T item ->
            maxHeap.insert(item)
        }
        List<T> results = []
        while (maxHeap.heapSize > 0) {
            results << maxHeap.extractMax()
        }
        return results.reverse()
    }
}
