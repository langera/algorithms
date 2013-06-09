package org.langera.maxsubarray

class DynamicProgrammingMaxSubArray implements MaxSubArray {

    @Override
    SubArraySum findMaxSubArray(final List<Integer> array) {
        List<PartialResult> cache = []
        array.eachWithIndex { int value, int index ->
            if (cache.empty) {
                SubArraySum init = new SubArraySum(sum: value, from: index, to: index + 1)
                cache << new PartialResult(currentMax: init, touchingCurrentIndexMax: init)
            }
            else {
                PartialResult result = cache.last()
                SubArraySum newTouchingMax = (result.touchingCurrentIndexMax.sum <= 0) ?
                        new SubArraySum(sum: value, from: index, to: index + 1) :
                        new SubArraySum(sum: result.touchingCurrentIndexMax.sum  + value, from: result.touchingCurrentIndexMax.from, to: index + 1)

                SubArraySum newCurrentMax = (result.currentMax.sum > newTouchingMax.sum) ? result.currentMax : newTouchingMax

                cache << new PartialResult(currentMax: newCurrentMax, touchingCurrentIndexMax: newTouchingMax)
            }
        }
        return cache.last().currentMax
    }

    private static class PartialResult {

        SubArraySum currentMax
        SubArraySum touchingCurrentIndexMax
    }
}
