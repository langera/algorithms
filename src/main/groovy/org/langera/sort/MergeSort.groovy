package org.langera.sort

class MergeSort implements Sort {

    @Override
    List<Integer> sort(final List<Integer> a) {
        if (a.size() < 2) {
            return a
        }
        int med = a.size() / 2
        List<Integer> first = sort(a.subList(0, med))
        List<Integer> second = sort(a.subList(med, a.size()))
        return merge(first, second)
    }

    List<Integer> merge(List<Integer> a1, List<Integer> a2) {
        List<Integer> a = []
        int a1ptr = 0
        int a2ptr = 0
        for (int i=0; i < a1.size() + a2.size(); i++) {
            if (a1 != null && a1ptr < a1.size()) {
                if (a2 != null && a2ptr < a2.size()) {
                    a << ((a1[a1ptr] < a2[a2ptr]) ? a1[a1ptr++] : a2[a2ptr++])
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
}
