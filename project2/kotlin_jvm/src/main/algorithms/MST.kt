package main.algorithms

import main.Edge
import main.Graph
import main.structures.Queue
import main.structures.Subset

class MST {
    companion object {

        fun kruskalUsingQueue(graph: Graph): Graph {
            val V = graph.V
            val edgesQueue = Queue(graph.edgesArray)
            val result = MutableList(V) { Edge() }
            var e = 0

            val subsets = Array(V) { Subset() }

            for (v in 0 until V) {
                subsets[v].parent = v
                subsets[v].rank = 0
            }

            while (e < V - 1) {
                val nextEdge = edgesQueue.pop()
                val x = Subset.find(subsets, nextEdge.src)
                val y = Subset.find(subsets, nextEdge.dest)
                if (x != y) {
                    result[e++] = nextEdge
                    Subset.union(subsets, x, y)
                }
            }

            return Graph(result)
        }

        fun kruskalUsingAdjMatrix(graph: Graph) : Graph {
            val V = graph.V
            val result = mutableListOf<Edge>()

            val adjMatrix = graph.adjMatrix
            val subsets = Array(V) { Subset() }
            for (v in 0 until V) {
                subsets[v].parent = v
                subsets[v].rank = 0
            }

            var edgeCount = 0
            while (edgeCount < V - 1) {
                var min = Int.MAX_VALUE
                var a = -1
                var b = -1

                for (i in 0 until V) {
                    for (j in 0 until V) {
                        if (Subset.find(subsets, i) != Subset.find(subsets, j) && adjMatrix[i][j] < min) {
                            min = adjMatrix[i][j]
                            a = i
                            b = j
                        }
                    }
                }
                result.add(Edge(a,b, min))
                Subset.union(subsets, a, b)
                edgeCount++
            }

            return Graph(result)
        }

        fun primUsingAdjMatrix(graph: Graph) : Graph {
            val queue = Queue(graph.V)
            val visited = Array(graph.V){false}
            val edges = mutableListOf<Edge>()
            val adjMatrix = graph.adjMatrix

            var vert = 0
            visited[vert] = true

            for(i in 1 until graph.V){
                for(j in 0 until graph.V){
                    if(adjMatrix[vert][j] < Int.MAX_VALUE){
                        if(!visited[j]) queue.push(Edge(vert,j,adjMatrix[vert][j]))
                    }
                }

                var minEdge : Edge
                do{
                    minEdge = queue.pop()
                }while(visited[minEdge.dest])

                edges.add(minEdge)
                visited[minEdge.dest] = true
                vert = minEdge.dest
            }

            return Graph(edges)
        }

        fun primUsingAdjList(graph: Graph) : Graph {
            val queue = Queue(graph.V)
            val visited = Array(graph.V){false}
            val edges = mutableListOf<Edge>()
            val adjList = graph.adjList

            var vert = 0
            visited[vert] = true

            for(i in 1 until graph.V){
                for(e in adjList[vert]){
                    if(!visited[e.name]) queue.push(Edge(vert,e.name, e.costTo))
                }

                var minEdge : Edge
                do{
                    minEdge = queue.pop()
                }while(visited[minEdge.dest])

                edges.add(minEdge)
                visited[minEdge.dest] = true
                vert = minEdge.dest
            }

            return Graph(edges)
        }
    }
}