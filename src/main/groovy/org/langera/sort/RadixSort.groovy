package org.langera.sort

import static java.lang.Math.abs
import static java.lang.Math.floor
import static java.lang.Math.log

class RadixSort<T> implements Sort<T> {

    int max
    private int radix = 16
    private CountingSort<T> countingSort

    RadixSort(final int max) {
        this.max = max
        this.countingSort = new CountingSort<T>(max: max)
    }

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        int d = floor(log(abs(max))/log(radix)) + 1
        List<T> result = a
        for (int i=0; i < d; i++) {
            result = countingSort.sort(result, new SortOrderingByDigit<T>(radix: radix, digitIndex: i, original: ordering))
        }
        return result
    }

    private static class SortOrderingByDigit<T> implements SortOrdering<T> {

        int radix
        int digitIndex
        SortOrdering<T> original

        @Override
        int sortValue(final T instance) {
            int value = (original.sortValue(instance) / (radix.power(digitIndex)))
            return value == 0 ? 0 : value % radix
        }
    }
}
