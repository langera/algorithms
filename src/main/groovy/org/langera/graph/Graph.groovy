package org.langera.graph

class Graph {

    Vertex vertex

    static class Vertex {

        boolean visited = false
        String id
        List<Edge> neighbours = []

        @Override
        String toString() {
            id
        }
    }

    static class Edge {
        int weight
        Vertex node
    }

}
