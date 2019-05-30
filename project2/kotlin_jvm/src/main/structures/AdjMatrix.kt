package main.structures

import main.Edge

class AdjMatrix(edges: Collection<Edge>, maxVerticeNumber: Int) {

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


    operator fun get(i: Int) : Array<Int>{
        return adjMatrix[i];
    }

    fun toEdgesCollection(): Collection<Edge>{
        val edges = hashSetOf<Edge>()

        for((i, row) in adjMatrix.withIndex()){
            for(x in (i+1) until row.size){
                if(x != Int.MAX_VALUE && x > 0)
                    edges.add(Edge(i,x, row[x]))
            }
        }
        return edges
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for(edgesRow in adjMatrix){
            for (element in edgesRow){
                if (element == Int.MAX_VALUE) builder.append(" - ")
                else builder.append(" $element ")
            }
            builder.append("\n")
        }

        return builder.toString()
    }

}