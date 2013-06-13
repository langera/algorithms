package org.langera.sort

import groovy.transform.ToString

@ToString
class BubbleSort<T> implements Sort<T> {

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = a.size() - 1; j > i; j--) {
                if (ordering.sortValue(a[i]) > ordering.sortValue(a[j])) {
                    T temp = a[i]
                    a[i] = a[j]
                    a[j] = temp
                }
            }
        }
        return a
    }
}
