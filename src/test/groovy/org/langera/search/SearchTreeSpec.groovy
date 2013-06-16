package org.langera.search

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static java.lang.ClassLoader.getSystemResourceAsStream

class SearchTreeSpec extends Specification {

    private static final Comparator<String> comparator = { String o1, String o2 -> o1.compareTo(o2) } as Comparator

    @Shared List<SearchTree> algorithms = [
            new SearchTreeRoot(comparator, { _, item, comparator -> new RecursiveBinarySearchTree(item, comparator) } as SearchTreeFactory),
            new SearchTreeRoot(comparator, { _, item, comparator -> new IterativeBinarySearchTree(item, comparator) } as SearchTreeFactory),
            new SearchTreeRoot(comparator, { root, item, comparator ->
                def tree = new RedBlackTree(root, item, comparator)
                tree.makeBlack()
                return tree
            } as SearchTreeFactory),
            new SearchTreeRoot(null, { _, item, __ ->
                    def tree = new LexicographicRadixTree()
                    tree.insert(item)
                    return tree
            } as SearchTreeFactory)
    ]

    @Unroll('#algorithm')
    def 'run search'() {
    given:
        insertBook(algorithm)
        println "$algorithm: size=${algorithm.size()} depth=${algorithm.maxDepth()}"
    expect:
        algorithm.contains('rabbit')
        !algorithm.contains('doesNotAppearInThisBook')
    where:
        algorithm << algorithms
    }

    def 'quickly hacked perf test search'() {
    given:
        Map<SearchTree, Long> avgInsertTimeByAlgorithm = [:]
        Map<SearchTree, Long> avgQueryTimeByAlgorithm = [:]
        algorithms.each { SearchTree searchTree ->
            avgInsertTimeByAlgorithm[searchTree] = 0
            avgQueryTimeByAlgorithm[searchTree] = 0
        }
    expect:
        warmUp(algorithms)

        (1..4).each { counter ->
            println "run $counter"
            algorithms.each { SearchTree searchTree ->
                long start = System.nanoTime()
                insertBook(searchTree)
                long afterInsert = System.nanoTime()
                searchTree.contains('doesNotAppearInThisBook')
                searchTree.contains('doesNotAppearInThisBook')
                searchTree.contains('doesNotAppearInThisBook')
                searchTree.contains('rabbit')
                searchTree.contains('alice')
                searchTree.contains('rabbit')
                searchTree.contains('doesNotAppearInThisBook')
                searchTree.contains('doesNotAppearInThisBook')
                searchTree.contains('doesNotAppearInThisBook')
                long end = System.nanoTime()

                avgInsertTimeByAlgorithm[searchTree] =
                    (avgQueryTimeByAlgorithm[searchTree] + (afterInsert - start)) / counter
                avgQueryTimeByAlgorithm[searchTree] =
                    (avgQueryTimeByAlgorithm[searchTree] + (end - afterInsert)) / counter
            }
        }

        avgInsertTimeByAlgorithm.each { key, value ->
            println "insert time $key: ${value/1000000}msec"
        }
        avgQueryTimeByAlgorithm.each { key, value ->
            println "query time $key: ${value/1000000}msec"
        }
    }

    void insertBook(SearchTreeRoot<String> searchTree) {
        searchTree.clear()
        getSystemResourceAsStream('alice30.txt').withReader('UTF-8') { Reader reader ->
            reader.eachLine { String line ->
                line.split(/\W/).each {
                    if (it) {
                        searchTree.insert(it)
                    }
                }
            }
        }
    }

    void warmUp(List<SearchTree> searchTrees) {
        (0..2).each {
            println "warm up ${it + 1}"
            searchTrees.each { SearchTreeRoot searchTree ->
                insertBook(searchTree)
                searchTree.contains('rabbit')
                searchTree.contains('doesNotAppearInThisBook')
            }
        }
    }
}
