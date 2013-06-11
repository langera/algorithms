package org.langera.sort

class QuickSort implements Sort {

    @Override
    List<Integer> sort(final List<Integer> a) {
        if (!a.empty) {
            int pivotIndex = partition(a)
            sort(a.subList(0, pivotIndex))
            sort(a.subList(pivotIndex + 1, a.size()))
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
