package org.langera.sort

class BucketSort<T> implements Sort<T> {

    int max
    private InsertionSort<T> internalInPlaceSort = new InsertionSort<T>()

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        final int n = a.size()
        if (n < 2) {
            return a
        }
        final int dividend = Math.max(1, (int) max / n)
        List<T>[] buckets = new List<T>[n]
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<T>()
        }
        a.each {T item ->
            int value = ordering.sortValue(item)
            buckets[(int)(value / dividend)].add(item)
        }
        for (int i = 0; i < n; i++) {
            internalInPlaceSort.sort(buckets[i], ordering)
        }
        List<T> result = new ArrayList<T>(n)
        for (int i = 0; i < n; i++) {
            result.addAll(buckets[i].asList())
        }
        return result
    }
}
