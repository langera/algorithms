package org.langera.sort

class InsertionSort implements Sort {

    @Override
    List<Integer> sort(List<Integer> a) {
        if (a.size() < 2) {
            return a
        }
        for (int i = 1; i < a.size(); i++) {
            int key = a[i]
            int j = i - 1
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j]
                j = j - 1
            }
            a[j + 1] = key
        }

        return a
    }


}
