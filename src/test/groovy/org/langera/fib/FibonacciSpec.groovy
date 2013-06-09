package org.langera.fib

import spock.lang.Specification
import spock.lang.Unroll

class FibonacciSpec extends Specification {

    Fibonacci algorithm

    @Unroll('#algorithm.class')
    def 'run fibonacci'() {

    expect:
        algorithm.fib(0) == 0
        algorithm.fib(1) == 1
        algorithm.fib(2) == 1
        algorithm.fib(3) == 2
        algorithm.fib(4) == 3
        algorithm.fib(5) == 5
        algorithm.fib(6) == 8
        algorithm.fib(7) == 13
        algorithm.fib(10) == 55
    where:
        algorithm << [ new RecursiveFibonacci(), new DynamicProgrammingFibonacci() ]
    }
}
