package org.langera.sort

class HeapSort implements Sort {

    @Override
    List<Integer> sort(final List<Integer> a) {
        MaxHeap maxHeap = new MaxHeap()
        a.each { int i ->
            maxHeap.insert(i)
        }
        List<Integer> results = []
        while (maxHeap.heapSize > 0) {
            results << maxHeap.extractMax()
        }
        return results.reverse()
    }
}
