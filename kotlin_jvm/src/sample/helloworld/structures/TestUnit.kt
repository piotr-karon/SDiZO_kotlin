package sample.helloworld.structures

import kotlin.random.Random
import kotlin.random.nextInt

class TestUnit(val count, val range: IntRange) {

    init{
        val numbers = IntArray(count){
            Random.nextInt(range)
        }
    }

    fun arrayTest(){

    }

    fun heapTest(){}

    fun listTest(){}

    fun bstTest(){}

    fun avlTest(){}
}
