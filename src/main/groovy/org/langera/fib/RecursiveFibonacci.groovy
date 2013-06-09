package org.langera.fib

class RecursiveFibonacci implements Fibonacci {

    @Override
    int fib(final int i) {
        if (i == 0 || i == 1) {
            return i
        }
        return fib(i - 1) + fib(i - 2)
    }
}
