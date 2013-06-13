package org.langera.sort

import groovy.transform.ToString

@ToString
class InsertionSort<T> implements Sort<T> {

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        if (a.size() < 2) {
            return a
        }
        for (int i = 1; i < a.size(); i++) {
            T key = a[i]
            int j = i - 1
            while (j >= 0 && ordering.sortValue(a[j]) > ordering.sortValue(key)) {
                a[j + 1] = a[j]
                j = j - 1
            }
            a[j + 1] = key
        }

        return a
    }


}
