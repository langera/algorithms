package org.langera.graph

import spock.lang.Specification
import spock.lang.Subject

class WalkSpec extends Specification {

    @Subject Walk walk = new Walk()
    Graph graph

    def setup() {
        Graph.Vertex r = new Graph.Vertex(id: 'r')
        Graph.Vertex s = new Graph.Vertex(id: 's')
        Graph.Vertex t = new Graph.Vertex(id: 't')
        Graph.Vertex u = new Graph.Vertex(id: 'u')
        Graph.Vertex v = new Graph.Vertex(id: 'v')
        Graph.Vertex w = new Graph.Vertex(id: 'w')
        Graph.Vertex x = new Graph.Vertex(id: 'x')
        Graph.Vertex y = new Graph.Vertex(id: 'y')
        graph = new Graph()
        graph.vertex = r
        r.neighbours += [v, s]
        s.neighbours += [r, w]
        t.neighbours += [u,w,x]
        u.neighbours += [t,x,y]
        v.neighbours += [r]
        w.neighbours += [s,t,x]
        x.neighbours += [w,t,u,y]
        y.neighbours += [u,x]
    }

    def 'bfs walk'() {
        expect:
            walk.bfs(graph).collect { it.id }.join(',') == 'r,v,s,w,t,x,u,y'
    }

    def 'dfs walk'() {
    expect:
        walk.dfs(graph).collect { it.id }.join(',') == 'r,v,s,w,t,u,y,x'
    }
}
