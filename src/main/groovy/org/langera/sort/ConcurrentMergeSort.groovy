package org.langera.sort

import groovy.transform.ToString

@ToString
class ConcurrentMergeSort<T> implements Sort<T> {

    @Override
    List<T> sort(final List<T> a, final SortOrdering<T> ordering) {
        if (a.size() < 2) {
            return a
        }
        int med = a.size() / 2
        final Runner runner = new Runner(toSort: a.subList(0, med), ordering:  ordering)
        Thread t = new Thread(runner)
        t.start()

        List<T> second = sort(a.subList(med, a.size()), ordering)
        t.join()
        return merge(runner.collector, second, ordering)
    }

    List<T> merge(List<T> a1, List<T> a2, SortOrdering<T> ordering) {
        List<T> a = []
        int a1ptr = 0
        int a2ptr = 0
        for (int i = 0; i < a1.size() + a2.size(); i++) {
            if (a1 != null && a1ptr < a1.size()) {
                if (a2 != null && a2ptr < a2.size()) {
                    a << ((ordering.sortValue(a1[a1ptr]) < ordering.sortValue(a2[a2ptr])) ? a1[a1ptr++] : a2[a2ptr++])
                }
                else {
                    a << a1[a1ptr++]
                }
            }
            else {
                a << a2[a2ptr++]
            }
        }
        return a
    }


    private class Runner implements Runnable {

        List collector
        List toSort
        SortOrdering ordering

        @Override
        void run() {
            collector = sort(toSort, ordering)
        }
    }

}
