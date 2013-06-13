package org.langera.sort

import groovy.transform.ToString

import static java.lang.Math.abs
import static java.lang.Math.floor
import static java.lang.Math.log

@ToString
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
            result = countingSort.sort(result, new SortOrderingByDigit<T>(i, ordering))
        }
        return result
    }

    private class SortOrderingByDigit<T> implements SortOrdering<T> {

        SortOrdering<T> original
        int dividend

        SortOrderingByDigit(final int digitIndex, final SortOrdering<T> original) {
            this.original = original
            this.dividend = radix.power(digitIndex)
        }

        @Override
        int sortValue(final T instance) {
            int value = original.sortValue(instance) / dividend
            return value == 0 ? 0 : value % radix
        }
    }
}
