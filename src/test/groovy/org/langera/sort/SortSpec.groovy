package org.langera.sort

import spock.lang.Specification
import spock.lang.Unroll

class SortSpec extends Specification {

    Sort algorithm

    @Unroll('#algorithm.class')
    def 'run sort'() {

    expect:
        algorithm.sort([]) == []
        algorithm.sort([0]) == [0]
        algorithm.sort([4,3,2,1]) == [1,2,3,4]
        algorithm.sort([1,2,3,4]) == [1,2,3,4]
        algorithm.sort([5,2,4,6,1,3]) == [1,2,3,4,5,6]

    where:
        algorithm << [ new InsertionSort(), new MergeSort(), new BubbleSort(), new HeapSort() ]
    }
}
