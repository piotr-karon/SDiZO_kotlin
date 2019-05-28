package test

import main.Graph
import org.junit.Test

class GraphTest{

    @Test
    fun randomGenTest(){
        val graph = Graph.randomGraph(5,0.5)
        println(graph.adjList)
    }
}