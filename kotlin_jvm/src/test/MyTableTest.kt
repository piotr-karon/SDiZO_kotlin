package sample.helloworld.test

import org.junit.Test
import sample.helloworld.MyTable
import kotlin.test.assertEquals
import kotlin.test.fail

class MyTableTest{

    @Test
    fun addTest(){
        val table = MyTable()
        table.add(2)

        assert(table.contains(2))
    }

    @Test
    fun addFirstTest(){
        val table = MyTable()
        table.addFirst(2)
        table.addFirst(3)
        assert( table.elemAt(0) == 3)
        assert( table.elemAt(1) == 2)
    }

    @Test
    fun addLastTest(){
        val table = MyTable()
        table.addLast(2)
        table.addLast(3)
        assert( table.elemAt(0) == 2)
        assert( table.elemAt(1) == 3)
    }

    @Test
    fun addAtTest(){
        val table = MyTable()
        table.addLast(3)
        table.addLast(3)

        assert(table.addAt(2, 6))
        assert(!table.addAt(222,666))
        assert(table.addAt(1,22))
        assertEquals(22,table.elemAt(1))

    }

    @Test
    fun forceAddAtTest() {
        val table = MyTable()
        table.addLast(3)
        table.addLast(3)

        table.forceAddAt(10, 22)

        assertEquals(22, table.elemAt(10))
        assert(table.elemAt(9) == null)
    }

    @Test
    fun deleteTest(){
        fail()
    }

    @Test
    fun deleteFirstTest(){
        fail()
    }

    @Test
    fun deleteLast(){
        fail()
    }

    @Test
    fun deleteAtTest(){
        fail()
    }

    @Test
    fun containsTest(){
        val table = MyTable()
        table.addLast(2)

        assert(table.contains(2))
    }

    @Test
    fun containsNotTest(){
        val table = MyTable()
        table.addLast(2)

        assert(!table.contains(3))
    }


    @Test
    fun containsNotWhileEmptyArrayTest(){
        val table = MyTable()

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
        val table = MyTable()
        table.add(2)

        assertEquals(table.getSize(), 1)
    }

    @Test
    fun getIndexOfTest(){
        val table = MyTable()
        table.addLast(2)
        table.addLast(3)

        assertEquals(1, table.getIndexOf(3))
    }




}
