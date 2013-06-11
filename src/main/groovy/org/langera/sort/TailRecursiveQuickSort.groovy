package org.langera.sort

class TailRecursiveQuickSort implements Sort {

    @Override
    List<Integer> sort(final List<Integer> a) {
        if (!a.empty) {
            int start = 0
            while (start < a.size()) {
                int pivotIndex = partition(a.subList(start, a.size())) + start
                sort(a.subList(start, pivotIndex))
                start = pivotIndex + 1
            }
        }
        return a
    }

    int partition(List<Integer> a) {
        int pivot = a.last()
        int pivotIndex = -1
        for (int i=0; i < a.size()-1; i++) {
            if (a[i] <= pivot) {
                int temp = a[++pivotIndex]
                a[pivotIndex] = a[i]
                a[i] = temp
            }
        }
        a[a.size() - 1] = a[++pivotIndex]
        a[pivotIndex] = pivot
        return pivotIndex
    }
}
