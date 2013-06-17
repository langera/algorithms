package org.langera.graph

import spock.lang.Specification
import spock.lang.Subject

class GraphSpec extends Specification {

    @Subject Graph graph = new Graph()

    def setup() {
        Graph.Vertex a = new Graph.Vertex(id: 'a')
        Graph.Vertex b = new Graph.Vertex(id: 'b')
        Graph.Vertex c = new Graph.Vertex(id: 'c')
        Graph.Vertex d = new Graph.Vertex(id: 'd')
        Graph.Vertex e = new Graph.Vertex(id: 'e')
        Graph.Vertex f = new Graph.Vertex(id: 'f')
        Graph.Vertex g = new Graph.Vertex(id: 'g')
        Graph.Vertex h = new Graph.Vertex(id: 'h')
        Graph.Vertex i = new Graph.Vertex(id: 'i')
        Graph.Vertex j = new Graph.Vertex(id: 'j')
        Graph.Vertex k = new Graph.Vertex(id: 'k')
        Graph.Vertex l = new Graph.Vertex(id: 'l')
        graph.vertex = a
        a.neighbours += [b, c, d]
        b.neighbours += [e, f]
        c.neighbours += []
        d.neighbours += [g, h]
        e.neighbours += [i, j]
        f.neighbours += []
        g.neighbours += [k, l]
        h.neighbours += []
        i.neighbours += []
        j.neighbours += []
        k.neighbours += []
        l.neighbours += []
    }

    def 'bfs walk'() {
    expect:
        graph.bfs().collect { it.id }.join(',') == 'a,b,c,d,e,f,g,h,i,j,k,l'
    }

    def 'dfs walk (with stack)'() {
    expect:
        graph.dfsWithStack().collect { it.id }.join(',') == 'a,b,e,i,j,f,c,d,g,k,l,h'
    }


    def 'dfs walk (recursive)'() {
    expect:
        graph.dfsRecursive().collect { it.id }.join(',') == 'a,b,e,i,j,f,c,d,g,k,l,h'
    }

    def 'check for cycles'() {
    given:
        Graph graphWithCycles = new Graph()
        graphWithCycles.vertex = new Graph.Vertex(id: 'A')
        graphWithCycles.vertex.neighbours << new Graph.Vertex(id: 'B')
        graphWithCycles.vertex.neighbours[0].neighbours << graphWithCycles.vertex
    expect:
        !graph.hasCycle()
        graphWithCycles.hasCycle()
    }
}
