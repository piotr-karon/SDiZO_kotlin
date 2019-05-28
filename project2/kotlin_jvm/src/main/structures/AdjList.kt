package main.structures

import main.Edge
import main.Utils

class AdjList (edges: Collection<Edge>, maxVerticeNumber: Int)  {

    private var vertexNumber : Int = maxVerticeNumber + 1
    var matrices : Array<MutableList<AdjListVertex>> = Array(vertexNumber){ MutableList(0){ AdjListVertex(-1,-1) }}

    init {
        for (edge in edges){
            val dest = edge.dest
            val src = edge.src
            val adjVertSrc = AdjListVertex(src, edge.weight)
            val adjVertDest = AdjListVertex(dest, edge.weight)

            if(dest != src) {
                if (adjVertDest !in matrices[src]) matrices[src].add(adjVertDest)
                if (adjVertSrc !in matrices[dest]) matrices[dest].add(adjVertSrc)
            }
        }
    }

    constructor(edges: Collection<Edge>) : this(edges, Utils.getVerticesMaxNumber(edges))

    operator fun get(i: Int) : MutableList<AdjListVertex> {
        return matrices[i]
    }

    override fun toString(): String {
        var str = ""

        var i = 0
        for (list in matrices){
            str+= "${i++}:$list \n"
        }

        return str
    }
}

class AdjListVertex(val name: Int, val costTo: Int){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdjListVertex

        if (name != other.name) return false
        if (costTo != other.costTo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name
        result = 31 * result + costTo
        return result
    }

    override fun toString(): String {
        return "($name:$costTo)"
    }

}