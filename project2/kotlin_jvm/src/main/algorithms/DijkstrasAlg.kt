package main.algorithms

import main.Graph

class DijkstrasAlg(private val graph: Graph, private val startPoint: Int){

    val costArray = Array(graph.V){Int.MAX_VALUE}
    val predecessors = Array(graph.V){-1}

    val isAppended = Array(graph.V){false}



    private class VertCost(name: Int, cost:Int)
}