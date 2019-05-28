package test

import main.Edge
import main.structures.Queue
import org.junit.Test

class HeapQueueTest {

    @Test
    fun createAndPrint(){
        val e1 = Edge(0, 1, 10)
        val e2 = Edge(0, 2, 6)
        val e3 = Edge(0, 3, 5)
        val e4 = Edge(1, 3, 15)
        val e5 = Edge(2, 3, 4)

        val heap = Queue(5)
        with(heap){
            push(e1)
            push(e2)
            push(e3)
            push(e4)
            push(e5)
        }
        heap.printTree()

    }
}