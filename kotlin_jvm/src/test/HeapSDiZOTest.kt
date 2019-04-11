package sample.helloworld.test

import org.junit.Test
import structures.heap.HeapSDiZO

class HeapSDiZOTest {

    @Test
    fun heapTest() {
        val heap = HeapSDiZO(10, 10, IntRange(0, 10))

        heap.arr.forEach { print("$it ") }
        println()
        heap.build()
        heap.arr.forEach { print("$it ") }
        println()

        heap.insert(99)
        heap.insert(22)
        heap.arr.forEach { print("$it ") }
        println()

    }

}


