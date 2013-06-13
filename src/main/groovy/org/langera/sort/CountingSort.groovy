package org.langera.sort

class CountingSort<T> implements Sort<T> {

    int max

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        int[] counters = new int[max + 1]
        for (int i = 0; i < a.size(); i++) {
            counters[ordering.sortValue(a[i])] += 1
        }
        for (int i = 1; i <= max; i++) {
            counters[i] += counters[i - 1]
        }
        List<T> b = new ArrayList<T>(a.size())
        for (int i = a.size() - 1; i >= 0; i--) {
            b[counters[ordering.sortValue(a[i])] - 1] = a[i]
            counters[ordering.sortValue(a[i])] -= 1
        }
        return b
    }
}
