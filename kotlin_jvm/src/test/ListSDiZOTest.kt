package sample.helloworld.test

import org.junit.Before
import org.junit.Test
import sample.helloworld.structures.FileLoader
import sample.helloworld.structures.list.Nil
import structures.list.ListSDiZO
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ListSDiZOTest {

    private lateinit var listSDiZO: ListSDiZO

    @Before
    fun setUp(){
        listSDiZO = ListSDiZO()
        with(listSDiZO){
            add(2)
            add(4)
            add(6)
            add(8)
            add(10)
        }
    }

    @Test
    fun givenEmptyListShouldCorrectlyAddValue() {
        val list = ListSDiZO()

        list.add(2)

        assertTrue(list.head !is Nil)
        assertTrue(list.tail !is Nil)
        assertEquals(list.head, list.tail)
        assertTrue(list.head.predecessor is Nil)
        assertTrue(list.head.successor is Nil)

    }

    @Test
    fun givenEmptyListShouldCorrectlyAddTwoValues() {
        val list = ListSDiZO()

        with(list) {
            add(2)
            add(3)

            assertTrue(head !is Nil)
            assertTrue(tail !is Nil)
            assertTrue(head != tail)

            assertTrue(head.predecessor is Nil)
            assertEquals(head.successor, tail)
            assertEquals(tail.predecessor, head)
            assertTrue(tail.successor is Nil)

            assertEquals(2, head.value)
            assertEquals(3, head.successor.value)
            assertTrue( head.successor.successor is Nil)

        }
    }

    @Test
    fun givenEmptyListShouldCorrectlyAddThreeValues() {
        val list = ListSDiZO()

        with(list) {
            add(2)
            add(3)
            add(4)

            assertEquals(2, head.value)
            assertEquals(3, head.successor.value)
            assertEquals(4, head.successor.successor.value)

            assertEquals(4, tail.value)
            assertEquals(3, tail.predecessor.value)
            assertEquals(2, tail.predecessor.predecessor.value)

            assertEquals(3, size)
        }
    }

    @Test
    fun givenListShouldFindElement() {
        val list = ListSDiZO()

        list.add(2)

        assertEquals(list.head, list.find(2))
    }

    @Test
    fun givenListShouldNotFindElement() {
        val list = ListSDiZO()

        list.add(2)

        assertEquals(Nil, list.find(22))
    }

    @Test
    fun givenListShouldDeleteValue(){

        with(listSDiZO){
            deleteValue(4)

            assertEquals(2, head.value)
            assertEquals(6, head.successor.value)
            assertEquals(8, head.successor.successor.value)
            assertEquals(10, head.successor.successor.successor.value)

            assertEquals(10, tail.value)
            assertEquals(8, tail.predecessor.value)
            assertEquals(6, tail.predecessor.predecessor.value)
            assertEquals(2, tail.predecessor.predecessor.predecessor.value)
        }

    }

    @Test
    fun givenOneElementListShouldDeleteValue(){
        listSDiZO = ListSDiZO()

        with(listSDiZO){
            add(2)
            assertTrue(head == tail)
            assertEquals(2, head.value)

            deleteValue(2)
            assertEquals(head, tail)
            assertEquals(Nil, head)

        }
    }

    @Test
    fun givenTwoElementListShouldDeleteTail(){
        listSDiZO = ListSDiZO()

        with(listSDiZO){
            add(2)
            add(3)

            deleteValue(3)
            assertEquals(2, head.value)
            assertEquals(Nil, head.successor)
            assertEquals(1, size)
        }
    }

    @Test
    fun givenListShouldNotDelete(){
        assertFalse(listSDiZO.deleteValue(100))
    }

    @Test
    fun testowanieZ1() {
        val list = FileLoader.listOf(File("tab1.txt"))

        var itr = list.frontIterator()
        assertEquals(2, itr.next().value)
        assertEquals(4, itr.next().value)
        assertEquals(6, itr.next().value)
        assertEquals(8, itr.next().value)
        assertEquals(10, itr.next().value)
        list.print()


        list.deleteAt(2)
        itr = list.frontIterator()
        assertEquals(2, itr.next().value)
        assertEquals(4, itr.next().value)
        assertEquals(8, itr.next().value)
        assertEquals(10, itr.next().value)
        list.print()


        list.deleteAt(3)
        itr = list.frontIterator()
        assertEquals(2, itr.next().value)
        assertEquals(4, itr.next().value)
        assertEquals(8, itr.next().value)
        list.print()

        list.deleteAt(0)
        itr = list.frontIterator()
        assertEquals(4, itr.next().value)
        assertEquals(8, itr.next().value)
        list.print()

        list.deleteAt(0)
        itr = list.frontIterator()
        assertEquals(8, itr.next().value)
        list.print()

        list.deleteAt(0)
        itr = list.frontIterator()
        assertEquals(Nil, itr.next())
        list.print()

    }

    @Test
    fun toStringAndReverseTest(){
        println(listSDiZO.toString())
        println(listSDiZO.toReverseString())
    }



//    @Test
//    fun addAtTest() {
//        val list = ListSDiZO(0)
//        list.addLast(1)
//        list.add(2)
//        list.add(3)
//
//        list.addAt(5, 3)
//
//        assertEquals(0, list.head.value)
//        assertEquals(1, list.head.successor.value)
//        assertEquals(2, list.head.successor.successor.value)
//        assertEquals(5, list.head.successor.successor.successor.value)
//    }
//
//    @Test
//    fun deleteValueTest() {
//        val list = ListSDiZO(0)
//        list.addLast(1)
//        list.add(2)
//        list.add(3)
//
//        assertTrue {
//            list.deleteValue(2)
//        }
//
//        val itr = list.frontIterator()
//
//        assertEquals(0, itr.next().value)
//        assertEquals(1, itr.next().value)
//        assertEquals(3, itr.next().value)
//
//    }
//
//    @Test
//    fun deleteAtValueTest() {
//        val list = ListSDiZO(0)
//        list.addLast(1)
//        list.add(2)
//        list.add(3)
//        list.add(4)
//
//        list.deleteAt(1)
//
//
//        val itr = list.frontIterator()
//
//        assertEquals(0, itr.next().value)
//        assertEquals(2, itr.next().value)
//        assertEquals(3, itr.next().value)
//        assertEquals(4, itr.next().value)
//
//    }
//
//    @Test
//    fun deleteAtFirstValueTest() {
//        val list = ListSDiZO(0)
//        list.addLast(1)
//        list.add(2)
//        list.add(3)
//        list.add(4)
//
//        list.deleteAt(0)
//
//
//        val itr = list.frontIterator()
//
//        assertEquals(1, itr.next().value)
//        assertEquals(2, itr.next().value)
//        assertEquals(3, itr.next().value)
//        assertEquals(4, itr.next().value)
//
//    }
//
//    @Test
//    fun deleteAtLastValueTest() {
//        val list = ListSDiZO(0)
//        list.addLast(1)
//        list.add(2)
//        list.add(3)
//        list.add(4)
//
//        list.deleteAt(4)
//
//
//        val itr = list.frontIterator()
//
//        assertEquals(0, itr.next().value)
//        assertEquals(1, itr.next().value)
//        assertEquals(2, itr.next().value)
//        assertEquals(3, itr.next().value)
//        assertFalse(itr.hasNext())
//    }
//
//    @Test
//    fun deleteFirstTest() {
//        val list = ListSDiZO(12)
//
//        list.deleteFirst()
//
//        assert(list.head == Nil)
//        assert(list.tail == Nil)
//
//        list.add(2)
//        list.add(3)
//
//        assertEquals(3, list.tail.value)
//
//        list.deleteFirst()
//
//        assertEquals(3, list.tail.value)
//    }
//
//    @Test
//    fun deleteLastTest() {
//        val list = ListSDiZO(12)
//
//        list.deleteLast()
//
//        assert(list.head == Nil)
//        assert(list.tail == Nil)
//
//        list.add(2)
//        list.add(3)
//
//        assertEquals(3, list.tail.value)
//
//        list.deleteLast()
//
//        assertEquals(2, list.tail.value)
//
//    }
//
//    @Test
//    fun contains() {
//        val list = ListSDiZO(0)
//        list.addLast(1)
//        list.add(2)
//        list.add(3)
//
//        assertTrue { list.contains(2) }
//        assertFalse { list.contains(11) }
//    }
//
//    @Test
//    fun saveTest() {
//        fail()
//    }
//
//    @Test
//    fun loadTest() {
//        fail()
//    }
//
//    @Test
//    fun frontIteratorTest() {
//        val list = ListSDiZO(12)
//        list.add(22)
//
//        val it = list.frontIterator()
//        assertEquals(12, it.next().value)
//        assertEquals(22, it.next().value)
//        assert(it.next() is Nil)
//    }
//
//    @Test
//    fun backIteratorTest() {
//        val list = ListSDiZO(12)
//        list.add(22)
//
//        val it = list.backIterator()
//        assertEquals(22, it.previous().value)
//        assertEquals(12, it.previous().value)
//        assert(it.next() is Nil)
//    }
//
//    @Test
//    fun toStringTest() {
//        val list = ListSDiZO(0)
//        list.addLast(1)
//        list.add(2)
//        list.add(3)
//
//        print(list.toString())
//    }
}