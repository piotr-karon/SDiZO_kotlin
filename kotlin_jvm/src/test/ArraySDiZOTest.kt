package sample.helloworld.test

import org.junit.Test
import sample.helloworld.ArraySDiZO
import kotlin.test.assertEquals
import kotlin.test.fail

class ArraySDiZOTest{

    @Test
    fun addTest(){
        val table = ArraySDiZO()
        table.add(2)

        assert(table.contains(2))
    }

    @Test
    fun addFirstTest(){
        val table = ArraySDiZO()
        table.addFirst(2)
        table.addFirst(3)
        assert( table.elemAt(0) == 3)
        assert( table.elemAt(1) == 2)
    }

    @Test
    fun addLastTest(){
        val table = ArraySDiZO()
        table.addLast(2)
        table.addLast(3)
        assert( table.elemAt(0) == 2)
        assert( table.elemAt(1) == 3)
    }

    @Test
    fun addAtTest(){
        val table = ArraySDiZO()
        table.addLast(3)
        table.addLast(3)

        assert(table.addAt(2, 6))
        assert(!table.addAt(222,666))
        assert(table.addAt(1,22))
        assertEquals(22,table.elemAt(1))

    }

    @Test
    fun forceAddAtTest() {
        val table = ArraySDiZO()
        table.addLast(3)
        table.addLast(3)

        table.forceAddAt(10, 22)

        assertEquals(22, table.elemAt(10))
        assert(table.elemAt(9) == null)
    }

    @Test
    fun deleteElemTest(){
        val table = ArraySDiZO()
        table.add(2)

        assert(table.delete(2))
        assertEquals(0,table.getSize())
    }

    @Test
    fun deleteFirstTest(){
        val table = ArraySDiZO()
        table.add(2)
        table.add(3)

        assert(table.deleteLast())
        assertEquals(1,table.getSize())
        assertEquals(2,table.elemAt(0))
    }

    @Test
    fun deleteLast(){
        val table = ArraySDiZO()
        table.add(2)
        table.add(3)

        assert(table.deleteFirst())
        assertEquals(1,table.getSize())
        assertEquals(3,table.elemAt(0))
    }

    @Test
    fun deleteAtTest(){
        val table = ArraySDiZO()
        table.add(1)
        table.add(2)
        table.add(3)
        table.add(4)
        table.add(5)

        table.deleteAt(2)

        assertEquals(table.getSize(), 4)
        assertEquals(1,table.elemAt(0))
        assertEquals(2,table.elemAt(1))
        assertEquals(4,table.elemAt(2))
        assertEquals(5,table.elemAt(3))
    }

    @Test
    fun containsTest(){
        val table = ArraySDiZO()
        table.addLast(2)

        assert(table.contains(2))
    }

    @Test
    fun containsNotTest(){
        val table = ArraySDiZO()
        table.addLast(2)

        assert(!table.contains(3))
    }


    @Test
    fun containsNotWhileEmptyArrayTest(){
        val table = ArraySDiZO()

        assert(!table.contains(3))
    }

    @Test
    fun saveTest(){
        fail()
    }

    @Test
    fun loadTest(){
        fail()
    }

    @Test
    fun getSizeTest(){
        val table = ArraySDiZO()
        table.add(2)

        assertEquals(table.getSize(), 1)
    }

    @Test
    fun getIndexOfTest(){
        val table = ArraySDiZO()
        table.addLast(2)
        table.addLast(3)

        assertEquals(1, table.getIndexOf(3))
    }




}
