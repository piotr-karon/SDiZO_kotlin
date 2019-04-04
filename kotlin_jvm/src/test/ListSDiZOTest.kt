package sample.helloworld.test

import org.junit.Test
import sample.helloworld.structures.list.Nil
import structures.list.IteratorListSDiZO
import structures.list.ListSDiZO
import kotlin.test.*

class ListSDiZOTest {

    @Test
    fun addTest() {
        val list = ListSDiZO(2)
        list.add(3)

        assertEquals(2,list.head.value)
        assertEquals(3,list.tail.value)

    }

    @Test
    fun addFirstTest() {
        val list = ListSDiZO(2)
        list.addFirst(3)

        assertEquals(3,list.head.value)
        assertEquals(2,list.tail.value)

    }

    @Test
    fun addLastTest() {
        val list = ListSDiZO(2)
        list.addLast(3)

        assertEquals(2,list.head.value)
        assertEquals(3,list.tail.value)

    }

    @Test
    fun addAtTest() {
        val list = ListSDiZO(0)
        list.addLast(1)
        list.add(2)
        list.add(3)

        list.addAt(5,3)

        assertEquals(0,list.head.value)
        assertEquals(1,list.head.successor.value)
        assertEquals(2,list.head.successor.successor.value)
        assertEquals(5,list.head.successor.successor.successor.value)
    }

    @Test
    fun deleteValueTest() {
        val list = ListSDiZO(0)
        list.addLast(1)
        list.add(2)
        list.add(3)

        assertTrue {
            list.deleteValue(2)
        }

        val itr = list.frontIterator()

        assertEquals(0, itr.next().value)
        assertEquals(1, itr.next().value)
        assertEquals(3, itr.next().value)

    }

    @Test
    fun deleteFirstTest() {
        val list = ListSDiZO(12)

        list.deleteFirst()

        assert(list.head == Nil)
        assert(list.tail == Nil)

        list.add(2)
        list.add(3)

        assertEquals(3, list.tail.value)

        list.deleteFirst()

        assertEquals(3, list.tail.value)
    }

    @Test
    fun deleteLastTest() {
        val list = ListSDiZO(12)

        list.deleteLast()

        assert(list.head == Nil)
        assert(list.tail == Nil)

        list.add(2)
        list.add(3)

        assertEquals(3, list.tail.value)

        list.deleteLast()

        assertEquals(2, list.tail.value)

    }

    @Test
    fun contains() {
        val list = ListSDiZO(0)
        list.addLast(1)
        list.add(2)
        list.add(3)

        assertTrue { list.contains(2) }
        assertFalse{ list.contains(11) }
    }

    @Test
    fun saveTest() {
        fail()
    }

    @Test
    fun loadTest() {
        fail()
    }

    @Test
    fun frontIteratorTest(){
        val list = ListSDiZO(12)
        list.add(22)

        val it = list.frontIterator()
        assertEquals(12, it.next().value)
        assertEquals(22, it.next().value)
        assert(it.next() is Nil)
    }

    @Test
    fun backIteratorTest(){
        val list = ListSDiZO(12)
        list.add(22)

        val it = list.backIterator()
        assertEquals(22, it.previous().value)
        assertEquals(12, it.previous().value)
        assert(it.next() is Nil)
    }

    @Test
    fun toStringTest(){
        val list = ListSDiZO(0)
        list.addLast(1)
        list.add(2)
        list.add(3)

        print(list.toString())
    }
}