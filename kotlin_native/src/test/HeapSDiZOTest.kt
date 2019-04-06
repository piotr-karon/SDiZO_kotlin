package sample.helloworld.test

import org.junit.Test
import sample.helloworld.structures.heap.HeapSDiZO
import kotlin.test.assertEquals

class HeapSDiZOTest{

    @Test
    fun heapQuickTest(){
        val heap = HeapSDiZO()

        heap.push(1)
        heap.push(2)

        assertEquals(2, heap.pop())
        assertEquals(1, heap.pop())
    }
}