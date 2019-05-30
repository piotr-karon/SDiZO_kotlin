package test

import main.Edge
import main.structures.AdjList
import main.structures.AdjMatrix
import org.junit.Test

class AdjMatrixAndListTest {

    @Test
    fun constructorAndPrintTestmatrix(){
        val e1 = Edge(0, 1,1)
        val e2 = Edge(0, 3,3)
        val e3 = Edge(1, 2,1)
        val e4 = Edge(3, 1,5)
        val e5 = Edge(3, 4,4)
        val e6 = Edge(4, 2,2)

        val edges = listOf(e1,e2,e3,e4,e5,e6)

        val matrix = AdjMatrix(edges,4)
        println(matrix.toString())
    }

    @Test
    fun constructorAndPrintTestList(){
        val e1 = Edge(0, 1,1)
        val e2 = Edge(0, 3,3)
        val e3 = Edge(1, 2,1)
        val e4 = Edge(3, 1,5)
        val e5 = Edge(3, 4,4)
        val e6 = Edge(4, 2,2)

        val edges = listOf(e1,e2,e3,e4,e5,e6)

        val list = AdjList(edges)
        println(list.toString())
    }
}