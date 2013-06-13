package org.langera.sort

import groovy.transform.ToString

@ToString
class TailRecursiveQuickSort<T> implements Sort<T> {

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        if (!a.empty) {
            int start = 0
            while (start < a.size()) {
                int pivotIndex = partition(a.subList(start, a.size()),ordering) + start
                sort(a.subList(start, pivotIndex), ordering)
                start = pivotIndex + 1
            }
        }
        return a
    }

    int partition(List<T> a, SortOrdering<T> ordering) {
        T pivot = a.last()
        int pivotIndex = -1
        for (int i=0; i < a.size()-1; i++) {
            if (a[i] <= pivot) {
                T temp = a[++pivotIndex]
                a[pivotIndex] = a[i]
                a[i] = temp
            }
        }
        a[a.size() - 1] = a[++pivotIndex]
        a[pivotIndex] = pivot
        return pivotIndex
    }
}
