package main

import main.structures.AdjList
import main.structures.AdjMatrix
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt

class Graph(private var edges: Collection<Edge>, v: Int, e:Int) {
    var V: Int = 0
        private set
    var E: Int = 0
        private set

    var startVert: Int = 0
    var endVert: Int

    var edgesArray: Array<Edge> = arrayOf(Edge())
        private set
    var adjMatrix: AdjMatrix = AdjMatrix(mutableListOf())
        private set
    var adjList = AdjList(mutableListOf())
        private set

    init {
        V = v
        E = e
        endVert = V-1
        edgesArray = edges.toTypedArray()
        adjMatrix = AdjMatrix(edges)
        adjList = AdjList(edges)
    }

    constructor(edges: Collection<Edge>) : this(edges,Utils.getVerticesCount(edges),edges.size )



    fun addEdge(edge: Edge) {
        edgesArray.plus(edge)
        adjMatrix = AdjMatrix(edgesArray.asList())
        adjList = AdjList(edgesArray.asList())
    }

    fun weight() : Int{
        var weight = 0
        edges.forEach{ weight+= if(it.weight != Int.MAX_VALUE) it.weight else 0}
        return weight
    }


    override fun toString(): String {
        return adjList.toString()
    }

    companion object{
        // AdjListVertex indexing starts at 0
        private fun randomMST(noOfVertices: Int) : MutableList<Edge>{
            val edges = mutableListOf<Edge>()
            val range = IntRange(0, noOfVertices-1)
            for(i in 0 until noOfVertices){
                var dest : Int
                do { dest = rand(range)} while (i == dest)
                edges.add(Edge(i,dest, weight = 0))
            }
            return edges
        }

        fun randomGraph(noOfVertices: Int, density: Double) : Graph{
            if(density<=0 || density > 1) return randomGraph(noOfVertices, 0.5)

            val edges = randomMST(noOfVertices)

            val edgesSet = hashSetOf<Edge>()
            edgesSet.addAll(edges)

            val maxNoOfEdges = noOfVertices * (noOfVertices - 1) / 2
            val iterations = (maxNoOfEdges * density).roundToInt() //- edges.size
            val range = IntRange(0, noOfVertices)
            for(i in 0 until iterations){
                do{
                    val src = rand(range)
                    var dest : Int
                    do { dest = rand(range)} while (src == dest)
                   // edges.add(Edge(src,dest,rand(range)))
                }while(edgesSet.add(Edge(src,dest,rand(range))))

            }
            return Graph(edgesSet)
        }

        fun randomGraph(noOfVertices: Int, density: Int) : Graph {
            return randomGraph(noOfVertices, density / 100.0)
        }


        private fun rand(range: IntRange) = Random.nextInt(range)
    }
}
