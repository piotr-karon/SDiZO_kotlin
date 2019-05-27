package test

import structures.heap.HeapSDiZO
import kotlin.test.Test
import kotlin.test.assertEquals

class HeapSDiZOTest {

    var heap = HeapSDiZO.generateRandom(1000, IntRange(0, 2000))

//    @Before
//    fun setUp(){
//        heap = FileLoader.heapOf(File("heap1.txt"))
//
//    }

    @Test
    fun loadTest() {
        //val heap = FileLoader.heapOf(File("heap1.txt"))

        heap.printTree()
    }

    @Test
    fun findTest() {
        //   assertNotEquals(Integer.MAX_VALUE, heap.find(7))
    }

    @Test
    fun findFarthestRight() {
        assertEquals(8, heap.findFarthestRight(0))
    }

    @Test
    fun deleteTest() {
        heap.printTree()
        println()
        heap.delete(10)
        heap.printTree()

        heap.delete(11)
        println()
        heap.printTree()
    }

    @Test
    fun testowanieZ1() {
        with(heap) {
            printTree()
            println()

            delete(11)
            printTree()
            println("Del 11\n")

            delete(1)
            printTree()
            println("Del 1\n")

            delete(9)
            printTree()
            println("Del 9\n")

            insert(11)
            printTree()
            println("Add 11\n")

            insert(12)
            printTree()
            println("Add 12\n")
        }
    }

//    @Test
//    fun heap2(){
//        heap = FileLoader.heapOf(File("heap2.txt"))
//
//        with(heap){
//            printTree()
//            println("--------------------------------")
//
//            delete(7)
//            printTree()
//            println("--------------------------------")
//
//            delete(33)
//            printTree()
//            println("--------------------------------")
//        }
//    }


}