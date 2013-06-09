package org.langera.sort

class InsertionSort implements Sort {

    List<Integer> sort(List<Integer> a) {
        if (a.size() < 2) {
            return a
        }
        (1..a.size() - 1).each { int i ->
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
