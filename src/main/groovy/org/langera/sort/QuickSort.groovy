package org.langera.sort

class QuickSort<T> implements Sort<T> {

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        if (!a.empty) {
            int pivotIndex = partition(a, ordering)
            sort(a.subList(0, pivotIndex), ordering)
            sort(a.subList(pivotIndex + 1, a.size()), ordering)
        }
        return a
    }

    int partition(List<T> a, SortOrdering<T> ordering) {
        T pivot = a.last()
        int pivotIndex = -1
        for (int i=0; i < a.size()-1; i++) {
            if (ordering.sortValue(a[i]) <= ordering.sortValue(pivot)) {
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
