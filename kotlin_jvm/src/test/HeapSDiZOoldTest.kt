package sample.helloworld.test

import org.junit.Test
import sample.helloworld.structures.FileLoader
import structures.heap.HeapSDiZOold
import java.io.File

class HeapSDiZOoldTest {

    @Test
    fun heapTest() {
        val heap = HeapSDiZOold(10, 10, IntRange(0, 10))

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

    @Test
    fun testowanieZ1(){
        val heap = FileLoader.heapOf(File("heap1.txt"))

        heap.printTree()
    }

}


