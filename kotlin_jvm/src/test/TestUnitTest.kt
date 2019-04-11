package sample.helloworld.test

import org.junit.Test
import structures.TestUnit


class TestUnitTest {

    @Test
    fun t(){

        val test = TestUnit(5, IntRange(1, 5))
        val num = 5
        with(test){
            arrayTest(num)
            listTest(num)
            heapTest(num)
            bstTest(num)
            avlTest(num)
        }
    }
}