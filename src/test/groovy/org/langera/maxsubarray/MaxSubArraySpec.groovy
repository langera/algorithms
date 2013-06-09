package org.langera.maxsubarray

import spock.lang.Specification
import spock.lang.Unroll

class MaxSubArraySpec extends Specification {

    MaxSubArray algorithm

    @Unroll('#algorithm.class')
    def 'run max sub-array'() {

    expect:
        algorithm.findMaxSubArray([0, 1]) == new SubArraySum(sum: 1, from: 1, to: 2)
        algorithm.findMaxSubArray([1, -4, 3, -4]) == new SubArraySum(sum: 3, from: 2, to: 3)
        algorithm.findMaxSubArray([13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7]) ==
                    new SubArraySum(sum: 43, from: 7, to: 11)
    where:
        algorithm << [ new BruteForceMaxSubArray(), new DivideAndConquerMaxSubArray() ]
    }
}
