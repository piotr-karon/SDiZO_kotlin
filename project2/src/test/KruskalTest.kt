package test

import main.Edge
import main.Graph
import main.algorithms.MST
import org.junit.Test

class KruskalTest {

    @Test
    fun kruskalMstListTest() {
        val e1 = Edge(0, 1, 10)
        val e2 = Edge(0, 2, 6)
        val e3 = Edge(0, 3, 5)
        val e4 = Edge(1, 3, 15)
        val e5 = Edge(2, 3, 4)

        val list = mutableListOf<Edge>(e1,e2,e3,e4,e5)

        val graphIn = Graph(list)

        val graphOutList = MST.kruskalUsingQueue(graphIn)
        val graphOutMatrix = MST.kruskalUsingAdjMatrix(graphIn)

        println(graphIn)
        println(graphOutList)
        println(graphOutMatrix)
    }
}