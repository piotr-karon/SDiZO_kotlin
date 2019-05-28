package main.structures

import main.Edge
import main.Utils

class AdjMatrix(edges: Collection<Edge>,private var maxVerticeNumber: Int) {

    private var vertexNumber : Int = maxVerticeNumber + 1
    private var adjMatrix : Array<Array<Int>> = Array(vertexNumber){Array(vertexNumber){ Int.MAX_VALUE}}
    var size: Int = 0
        private set
    init {
        for (edge in edges){
            adjMatrix[edge.src][edge.dest] = edge.weight
            adjMatrix[edge.dest][edge.src] = edge.weight
        }
        size = adjMatrix.size
    }

    constructor(edges: Collection<Edge>) : this(edges, Utils.getVerticesMaxNumber(edges))

    operator fun get(i: Int) : Array<Int>{
        return adjMatrix[i];
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for(edgesRow in adjMatrix)
            builder.append(Utils.arrayToString(edgesRow) + "\n")

        return builder.toString()
    }

}