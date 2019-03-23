package sample.helloworld

import kotlin.random.Random


class MyTable {

    private var elementData : Array<Int?> = emptyArray()
    private var size = 0

    fun addLast(element: Int){
        val previous : Array<Int?> = elementData.copyOf()
        elementData = arrayOfNulls(++size)

        var i = 0
        previous.forEach { e ->  elementData[i++] = e}
        elementData[size-1] = element
    }

    fun addFirst(element: Int){
        val previous : Array<Int?> = elementData.copyOf()
        elementData = arrayOfNulls(++size)

        var i = 0
        elementData[i++] = element
        previous.forEach { e ->  elementData[i++] = e}
    }

    fun addRandom(element: Int){
        val previous : Array<Int?> = elementData.copyOf()
        elementData = arrayOfNulls(++size)

        val rand =  Random(1).nextInt(0, size)
        var i = 0
        previous.forEach {
                e ->  when( i == rand) {
                        false -> elementData[i++] = e
                        true -> {
                            elementData[i++] = element
                            elementData[i++] = e
                        }
                     }
                }

    }

    fun elemAt(index: Int) : Int?{
        return elementData[index]
    }



}