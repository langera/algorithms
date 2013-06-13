package org.langera.tree

import org.langera.sort.SortOrdering

class MaxHeap<T> implements PriorityQueue<T> {

    private int heapSize = 0
    private SortOrdering<T> ordering
    private List<T> array = []

    int parentOf(int i) {
        return i / 2
    }

    int leftChildOf(int i) {
        return 2 * i
    }

    int rightChildOf(int i) {
        return 2 * i + 1
    }

    T extractMax() {
        if (heapSize == 0) {
            throw new IllegalStateException('Cannot extract from empty heap')
        }
        T max = array[0]
        array[0] = array[--heapSize]
        maxHeapify(0)
        return max
    }

    void insert(T value) {
        array[heapSize++] = value
        maxHeapIncreaseKey()
    }

    int size() {
        return heapSize
    }

    private void maxHeapIncreaseKey() {
        if (heapSize == 0) {
            throw new IllegalStateException('Cannot increae empty heap')
        }
        int ptr = heapSize - 1
        while (ptr > 0) {
            int parent = parentOf(ptr)
            if (ordering.sortValue(array[ptr]) > ordering.sortValue(array[parent])) {
                T temp = array[ptr]
                array[ptr] = array[parent]
                array[parent] = temp
                ptr = parent
            }
            else {
                break;
            }
        }
    }

    private void maxHeapify(int i) {
        int l = leftChildOf(i)
        int r = rightChildOf(i)
        int indexOfLargest
        if (l < heapSize && ordering.sortValue(array[l]) > ordering.sortValue(array[i])) {
            indexOfLargest = l
        }
        else {
            indexOfLargest = i
        }
        if (r < heapSize && ordering.sortValue(array[r]) > ordering.sortValue(array[indexOfLargest])) {
            indexOfLargest = r
        }
        if (indexOfLargest != i) {
            T temp = array[i]
            array[i] = array[indexOfLargest]
            array[indexOfLargest] = temp
            maxHeapify(indexOfLargest)
        }
    }
}
