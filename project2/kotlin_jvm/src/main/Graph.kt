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
    var adjMatrix: AdjMatrix
        private set

    var adjList: AdjList
        private set

    init {
        V = v
        E = e
        endVert = V-1
        edgesArray = edges.toTypedArray()
        adjList = AdjList(edges, V)
        adjMatrix = AdjMatrix(edges,V)
    }

    constructor(edges: Collection<Edge>) : this(edges,Utils.getVerticesCount(edges),edges.size )

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

            var src : Int
            var dest: Int

            for(i in 0 until iterations){
                do{
                    src = rand(range)
                    do {
                        dest = rand(range)}
                    while (src == dest)

                }while(! edgesSet.add(Edge(src,dest,rand(IntRange(1,99)))) )

            }
            return Graph(edgesSet, noOfVertices, edgesSet.size)
        }

        fun randomGraphManyVerts(noOfVertices: Int, density: Double):Graph{
            val edges = hashSetOf<Edge>()
            edges.addAll(randomMST(noOfVertices))

            val max = noOfVertices
            for(i in 0 until max){
                for(x in i+1 until max){
                    val random = Random.nextDouble(0.0,1.0)
                    if(random < density){
                        edges.add(Edge(i,x, rand(IntRange(1,noOfVertices))))
                    }
                }
            }

            return Graph(edges, noOfVertices, edges.size )
        }

        fun randomGraph(noOfVertices: Int, density: Int) : Graph {
            return randomGraph(noOfVertices, density / 100.0)
        }


        private fun rand(range: IntRange) = Random.nextInt(range)
    }
}
