package org.langera.sort

class BubbleSort implements Sort {

    @Override
    List<Integer> sort(final List<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = a.size() - 1; j > i; j--) {
                if (a[i] > a[j]) {
                    int temp = a[i]
                    a[i] = a[j]
                    a[j] = temp
                }
            }
        }
        return a
    }
}
