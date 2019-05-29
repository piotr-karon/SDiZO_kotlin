package test

import main.Graph
import main.GraphLoader
import org.junit.Test

class GraphTest{

    @Test
    fun randomGenTest(){
        val graph = Graph.randomGraph(5,0.5)
        println(graph.adjList)
    }

    @Test
    fun randomGenManyVertsTest(){
        val graph = Graph.randomGraph(1000,0.1)
        print(graph.adjMatrix)
    }

    @Test
    fun saveGraph(){
        val graph = Graph.randomGraph(10, 0.6)
        GraphLoader.saveFile(graph, "./gen/g1.txt")
    }
}