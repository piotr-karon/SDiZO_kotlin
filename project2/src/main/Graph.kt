package main

import main.structures.AdjList
import main.structures.AdjMatrix
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt

class Graph() {
    var V: Int = 0
        private set
    var E: Int = 0
        private set

    var startVert: Int = -1
    var endVert: Int = -1

    var edgesArray: Array<Edge> = arrayOf(Edge())
        private set
    var adjMatrix: AdjMatrix = AdjMatrix(mutableListOf())
        private set
    var adjList = AdjList(mutableListOf())
        private set

    constructor(edges: Collection<Edge>) : this(edges,Utils.getVerticesCount(edges),edges.size )

    constructor(edges: Collection<Edge>, v: Int, e:Int) : this() {
        V = v
        E = e

        edgesArray = edges.toTypedArray()
        adjMatrix = AdjMatrix(edges)
        adjList = AdjList(edges)
    }

    fun addEdge(edge: Edge) {
        edgesArray.plus(edge)
        adjMatrix = AdjMatrix(edgesArray.asList())
        adjList = AdjList(edgesArray.asList())
    }

    override fun toString(): String {
        return adjList.toString()
    }

    companion object{
        // AdjListVertex indexing starts at 0
        fun randomMST(noOfVertices: Int) : MutableList<Edge>{
            val edges = mutableListOf<Edge>()
            val range = IntRange(0, noOfVertices-1)
            for(i in 0 until noOfVertices){
                var dest : Int
                do { dest = rand(range)} while (i == dest)
                edges.add(Edge(i,dest, weight = -1))
            }
            return edges
        }

        fun randomGraph(noOfVertices: Int, density: Double) : Graph{
            val edges = randomMST(noOfVertices)
            val maxNoOfEdges = noOfVertices * (noOfVertices - 1) / 2
            val iterations = (maxNoOfEdges * density).roundToInt() //- edges.size
            val range = IntRange(0, noOfVertices)
            for(i in 0 until iterations){
                val src = rand(range)
                var dest : Int
                do { dest = rand(range)} while (src == dest)
                edges.add(Edge(src,dest,rand(range)))
            }
            return Graph(edges)
        }

        private fun rand(range: IntRange) = Random.nextInt(range)
    }
}
