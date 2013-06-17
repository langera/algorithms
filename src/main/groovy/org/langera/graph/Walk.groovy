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
            walk << v
            v.neighbours.reverseEach { Graph.Vertex n ->
                if (!n.visited) {
                    n.visited = true
                    queue.push(n)
                }
            }
        }
        return walk
    }

}
