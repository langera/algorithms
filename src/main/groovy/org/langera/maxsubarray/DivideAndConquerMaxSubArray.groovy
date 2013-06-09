package org.langera.maxsubarray

import static java.lang.Integer.MIN_VALUE

class DivideAndConquerMaxSubArray implements MaxSubArray {

    @Override
    SubArraySum findMaxSubArray(final List<Integer> array) {
        switch (array.size()) {
            case 0:
                return null
            case 1:
                return new SubArraySum(sum: array[0], from: 0, to:  1)
            default:
                int med = array.size() / 2
                SubArraySum leftResult = findMaxSubArray(array.subList(0, med))
                SubArraySum rightResult = shift(findMaxSubArray(array.subList(med, array.size())), med)
                SubArraySum crossingResult = findMaxCrossingSubArray(array, med)
                if (leftResult.sum >= rightResult.sum &&  leftResult.sum >= crossingResult.sum) {
                    return leftResult
                }
                if (rightResult.sum >= crossingResult.sum) {
                    return rightResult
                }
                return crossingResult
        }
    }

    SubArraySum findMaxCrossingSubArray(final List<Integer> array, int crossing) {
        int sumLeft = MIN_VALUE
        int sumRight = MIN_VALUE
        int indexLeft = crossing - 1
        int indexRight = crossing
        int sum = 0
        for (int i = crossing - 1; i >= 0; i--) {
            sum += array[i]
            if (sum > sumLeft) {
                sumLeft = sum
                indexLeft = i
            }
        }
        sum = 0
        for (int i = crossing; i < array.size(); i++) {
            sum += array[i]
            if (sum > sumRight) {
                sumRight = sum
                indexRight = i
            }
        }
        return new SubArraySum(sum: sumLeft + sumRight, from: indexLeft, to: indexRight + 1)
    }

    SubArraySum shift(SubArraySum subArraySum, int index) {
        new SubArraySum(sum: subArraySum.sum, from:  subArraySum.from + index, to: subArraySum.to + index)
    }
}
