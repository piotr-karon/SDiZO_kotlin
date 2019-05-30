package test

import main.Graph
import main.GraphLoader
import org.junit.Test
import kotlin.system.measureTimeMillis

class GraphTest{

    @Test
    fun randomGenTest(){
        val graph = Graph.randomGraph(50,1.0)
       // GraphPrinter(graph).print()
        println(graph.adjMatrix)
    }

    @Test
    fun randomGenManyVertsTest(){
        val graph = Graph.randomGraph(1000, 0.99)
        print(graph.adjMatrix)
    }

    @Test
    fun saveGraph(){
        val graph = Graph.randomGraph(10, 0.6)
        GraphLoader.saveFile(graph, "./gen/g1.txt")
    }

    @Test
    fun loaderOverloadTest(){
        val t = measureTimeMillis {
            val graph = GraphLoader.loadFile("./gen/2000/75_0.txt")
        }
        print(t)
    }
}