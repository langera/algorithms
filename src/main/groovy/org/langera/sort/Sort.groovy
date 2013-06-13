package org.langera.sort

public interface Sort<T> {

    List<T> sort(List<T> a, SortOrdering<T> ordering)
}