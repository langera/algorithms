package org.langera.similarity

class RabinKarpStringMatching implements StringMatching {

    @Override
    int find(final String text, final String pattern) {
        int radix = ((int) 'z'[0]) - ((int) 'a'[0]) + 1

        long p = pattern.inject(0) { acc, character ->
            (acc * radix) + valueOf(character)
        }

        long[] textValues = new long[text.length() - pattern.length() + 1]
        textValues[0] = text.substring(0, pattern.length()).inject(0) { acc, character ->
            (acc * radix) + valueOf(character)
        }
        long h = (1..pattern.length() - 1).inject(1) { acc, _ -> acc * radix }
        for (int i = 1; i < textValues.length; i++) {
            if (pattern.length() == 1) {
                textValues[i] = valueOf(text[i])
            }
            else {
                textValues[i] = radix * (textValues[i - 1] - (h * valueOf(text[i - 1]))) +
                        valueOf(text[i + pattern.length() - 1])
            }
        }

        for (int i = 0; i < textValues.length; i++) {
            if (textValues[i] == p) {
                return i
            }
        }
        return -1
    }

    int valueOf(String s) {
        ((int) s) - ((int) 'a')
    }
}
