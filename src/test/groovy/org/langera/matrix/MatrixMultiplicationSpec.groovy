package org.langera.matrix

import spock.lang.Specification
import spock.lang.Unroll

class MatrixMultiplicationSpec extends Specification {

    MatrixMultiplication algorithm

    @Unroll('#algorithm.class')
    def 'run matrix multiplication'() {
    given:
        int[][] a =
               [[-1, 7, 0, 3],
                [1, 7, 0, 3],
                [-1, 7, 0, 3],
                [1, 7, 0, 3]]
        int[][] b =
               [[1, 0, 1, 0],
                [0, 1, 0, 0],
                [0, 0, 1, 0],
                [1, 0, 0, 1]]
        int[][] identity =
               [[1, 0, 0, 0],
                [0, 1, 0, 0],
                [0, 0, 1, 0],
                [0, 0, 0, 1]]
        int[][] c =
               [[2, 7, -1, 3],
                [4, 7, 1, 3],
                [2, 7, -1, 3],
                [4, 7, 1, 3]]
    expect:
        algorithm.multiply(a, b) == c
        algorithm.multiply(a, identity) == a
    where:
        algorithm << [new SquareMatrixMultiplication()]
    }
}
