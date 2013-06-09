package org.langera.sort

class MaxHeap {

    int heapSize = 0
    @Delegate List<Integer> array = []

    int parentOf(int i) {
        return i / 2
    }

    int leftChildOf(int i) {
        return 2 * i
    }

    int rightChildOf(int i) {
        return 2 * i + 1
    }

    int extractMax() {
        if (heapSize == 0) {
            throw new IllegalStateException('Cannot extract from empty heap')
        }
        int max = array[0]
        array[0] = array[--heapSize]
        maxHeapify(0)
        return max
    }

    void insert(int value) {
        array[heapSize++] = value
        maxHeapIncreaseKey()
    }

    private void maxHeapIncreaseKey() {
        if (heapSize == 0) {
            throw new IllegalStateException('Cannot increae empty heap')
        }
        int ptr = heapSize - 1
        while (ptr > 0) {
            int parent = parentOf(ptr)
            if (array[ptr] > array[parent]) {
                int temp = array[ptr]
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
        if (l < heapSize && array[l] > array[i]) {
            indexOfLargest = l
        }
        else {
            indexOfLargest = i
        }
        if (r < heapSize && array[r] > array[indexOfLargest]) {
            indexOfLargest = r
        }
        if (indexOfLargest != i) {
            int temp = array[i]
            array[i] = array[indexOfLargest]
            array[indexOfLargest] = temp
            maxHeapify(indexOfLargest)
        }
    }


}
