package sample.helloworld.test

import org.junit.Test
import sample.helloworld.structures.list.ListElement
import sample.helloworld.structures.list.Nil
import structures.list.ListSDiZO
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ListIteratorTest {

    @Test
    fun givenEmptyListFrontIteratorShouldReturnNil(){
        val list = ListSDiZO()

        val frontItr = list.frontIterator()

        assertTrue(frontItr.next() is Nil)
        assertTrue(frontItr.previous() is Nil)
        assertFalse(frontItr.hasNext())
        assertFalse(frontItr.hasPrevious())
    }

    @Test
    fun givenEmptyListBackIteratorShouldReturnNil(){
        val list = ListSDiZO()

        val backItr = list.backIterator()

        assertTrue(backItr.next() is Nil)
        assertTrue(backItr.previous() is Nil)
        assertFalse(backItr.hasNext())
        assertFalse(backItr.hasPrevious())
    }

    @Test
    fun givenListWithOneFrontIteratorElementShouldNotReturnNil(){
        val list = ListSDiZO()
        val elem = ListElement(0,Nil,Nil)
        list.head = elem
        list.tail = elem

        val frontItr = list.frontIterator()
        val backItr = list.backIterator()

        assertEquals(frontItr.next(), elem)
        assertFalse(frontItr.hasNext())

        assertTrue(frontItr.previous() is Nil)
        assertFalse(frontItr.hasPrevious())

        assertTrue(backItr.next() !is Nil)
        assertTrue(backItr.previous() is Nil)
        assertFalse(backItr.hasNext())
        assertFalse(backItr.hasPrevious())
    }


    @Test
    fun givenListWithTwoElementsShouldReturnThem(){
        val list = ListSDiZO()
        val elem1 = ListElement(0, Nil, Nil)
        val elem2 = ListElement(0, Nil, Nil)
        list.head = elem1
        elem1.successor = elem2
        list.tail = elem2
        elem2.predecessor = elem1

        val frontItr = list.frontIterator()

        assertEquals(frontItr.next(), elem1)
        assertEquals(frontItr.next(), elem2)
    }

    @Test
    fun givenListWithManyElementsShouldReturnThem(){
        val list = ListSDiZO()
        val elem1 = ListElement(0, Nil, Nil)
        val elem2 = ListElement(1, Nil, Nil)
        val elem3 = ListElement(2, Nil, Nil)
        val elem4 = ListElement(3, Nil, Nil)

        list.head = elem1
        elem1.successor = elem2
        elem2.predecessor = elem1
        elem2.successor = elem3
        elem3.predecessor = elem2
        elem3.successor = elem4
        elem4.predecessor = elem3

        list.tail = elem4

        val frontItr = list.frontIterator()

        assertEquals(frontItr.next(), elem1)
        assertEquals(frontItr.next(), elem2)
        assertEquals(frontItr.next(), elem3)
        assertEquals(frontItr.next(), elem4)
        assertEquals(frontItr.next(), Nil)
        assertEquals(frontItr.next(), Nil)
    }
}