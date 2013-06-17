package org.langera.similarity

class NaiveStringMatching implements StringMatching {

    @Override
    int find(final String text, final String pattern) {
        for (int i=0; i <= text.length() - pattern.length(); i++) {
            int j = 0;
            for (; j < pattern.length(); j++) {
                if (text[i+j] != pattern[j]) {
                    break;
                }
            }
            if (j == pattern.length()) {
                return i
            }
        }
        return -1
    }
}
