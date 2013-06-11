package org.langera.sort

class CountingSort implements Sort {

    int max

    @Override
    List<Integer> sort(final List<Integer> a) {
        int[] counters = new int [max + 1]
        for (int i = 0; i < a.size(); i++) {
            counters[a[i]] += 1
        }
        for (int i = 1; i <= max; i++) {
            counters[i] += counters[i - 1]
        }
        List<Integer> b = new ArrayList<Integer>(a.size())
        for (int i = 0; i < a.size(); i++) {
            b[counters[a[i]] - 1] = a[i]
            counters[a[i]] -= 1
        }
        return b
    }
}
