package test

import structures.TestUnit
import kotlin.test.Test


class TestUnitTest {

    @Test
    fun t() {

        val test = TestUnit(5, IntRange(1, 5))
        val num = 5
        with(test) {
            arrayTest(num)
            listTest(num)
            heapTest(num)
            bstTest(num)
            avlTest(num)
        }
    }
}