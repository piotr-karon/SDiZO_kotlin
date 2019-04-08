package sample.helloworld.test

import sample.helloworld.MyTable
import kotlin.test.Test

class MyTableTest{

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
        assert( table.elemAt(0) == 3)
        assert( table.elemAt(1) == 2)
    }

}