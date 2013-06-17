package org.langera.similarity

import spock.lang.Specification

class StringMatchingSpec extends Specification {



    def 'test string matching'() {
    expect:
        algorithm.find('abcdefg', 'hij') == -1
        algorithm.find('abcdefg', 'gij') == -1
        algorithm.find('abcdefg', 'abc') == 0
        algorithm.find('abcdefg', 'abcdefg') == 0
        algorithm.find('abcdefg', 'de') == 3
        algorithm.find('abcdefg', 'f') == 5
        algorithm.find('abcdefg', 'fg') == 5
    where:
        algorithm << [ new NaiveStringMatching(),
                    new RabinKarpStringMatching(),
                    new KMPStringMatching(),
        ]
    }
}
