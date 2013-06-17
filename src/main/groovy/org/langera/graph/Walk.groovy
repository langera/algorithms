package org.langera.graph

class Walk {


    List<Graph.Vertex> bfs(Graph graph) {
        List<Graph.Vertex> walk = []
        graph.vertex.visited = true
        Queue<Graph.Vertex> queue = new LinkedList<Graph.Vertex>()
        queue.addLast(graph.vertex)
        while (!queue.isEmpty()) {
            Graph.Vertex v = queue.removeFirst()
            walk << v
            v.neighbours.each { Graph.Vertex n ->
                if (!n.visited) {
                    n.visited = true
                    queue.addLast(n)
                }
            }
        }
        return walk
    }

    List<Graph.Vertex> dfs(Graph graph) {
        List<Graph.Vertex> walk = []
        graph.vertex.visited = true
        Queue<Graph.Vertex> queue = new LinkedList<Graph.Vertex>()
        queue.push(graph.vertex)
        while (!queue.isEmpty()) {
            Graph.Vertex v = queue.pop()
            if (v.neighbours.isEmpty() || !v.neighbours.find { Graph.Vertex n -> n.visited }) {
                walk << v
            }
            Graph.Vertex next = v.neighbours.find { Graph.Vertex n -> !n.visited }
            if (next) {
                if (v.neighbours.find { Graph.Vertex n -> !n.visited }) {
                    queue.push(v)
                }
                next.visited = true
                queue.push(next)
            }
        }
        return walk
    }

}
