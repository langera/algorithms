package org.langera.sort

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static java.lang.Math.random

class SortSpec extends Specification {

    @Shared List<Sort> algorithms = [new InsertionSort(),
            new MergeSort(),
            new BubbleSort(),
            new HeapSort(),
            new QuickSort(),
            new TailRecursiveQuickSort(),
            new CountingSort(max: 10000),
            new RadixSort(10000),
    ]

    SortOrdering<Integer> ordering = { it } as SortOrdering

    @Unroll('#algorithm.class')
    def 'run sort'() {

    expect:
        algorithm.sort([], ordering) == []
        algorithm.sort([0], ordering) == [0]
        algorithm.sort([4, 3, 2, 1], ordering) == [1, 2, 3, 4]
        algorithm.sort([1, 2, 3, 4], ordering) == [1, 2, 3, 4]
        algorithm.sort([5, 2, 4, 6, 1, 3], ordering) == [1, 2, 3, 4, 5, 6]
        algorithm.sort([5, 5, 4, 3, 6, 1, 3, 2], ordering) == [1, 2, 3, 3, 4, 5, 5, 6]

    where:
        algorithm << algorithms
    }

    def 'quickly hacked perf test sort'() {
    given:
        int max = 10000
        Map<String, Long> avgTimeByAlgorithm = [:]
        algorithms.each { Sort sortAlgorithm ->
            avgTimeByAlgorithm[sortAlgorithm.class.simpleName] = 0
        }
    expect:
        warmUp(algorithms, max)

        (1..4).each { counter ->
            List toSort = (0..max).collect {
                (int) (random() * max)
            }
            println "run $counter"
            algorithms.each { Sort sortAlgorithm ->
                final List<Integer> copyToSort = new ArrayList<Integer>(toSort)
                long start = System.nanoTime()
                sortAlgorithm.sort(copyToSort, ordering)
                long end = System.nanoTime()
                avgTimeByAlgorithm[sortAlgorithm.class.simpleName] =
                        (avgTimeByAlgorithm[sortAlgorithm.class.simpleName] + (end - start)) / counter
            }
        }

        avgTimeByAlgorithm.each { key, value ->
            println "$key: ${value/1000000}msec"
        }
    }

    void warmUp(List<Sort> algorithms, int max) {
        (0..2).each {
            println "warm up ${it + 1}"
            algorithms.each { Sort sortAlgorithm ->
                List toSort = (0..max).collect {
                    (int) (random() * max)
                }
                sortAlgorithm.sort(toSort, ordering)
            }
        }
    }
}
