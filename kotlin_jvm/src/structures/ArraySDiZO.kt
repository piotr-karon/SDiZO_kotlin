package sample.helloworld

import kotlin.random.Random


class ArraySDiZO {

    private var elementData: Array<Int?> = emptyArray()
    private var size = 0

    fun add(element: Int) {
        addLast(element)
    }

    fun addFirst(element: Int) {
        addAt(0, element)
    }

    fun addLast(element: Int) {
        addAt(size, element)
    }

    fun addAtRandomPosition(element: Int) {
        val rand = Random(1).nextInt(0, size)
        addAt(element, rand)
    }

    /*
        Zwraca:
        true - gdy pomyślnie dodano element na wskazanej pozycji
        false - gdy nie udało się wstawić elementu
     */
    fun addAt(index: Int, element: Int): Boolean {
        if (index > size || index < 0) return false

        elementData = elementData.copyOf(++size)

        var i = size - 1

        while (size > 1 && i > index)
            elementData[i] = elementData[--i]

        elementData[index] = element

        return true
    }

    /*
        Jeśli index jest większy niż rozmiar tablicy,
        to jest ona rozszerzana do odpowiedniej długości.
        Wstawienie elementu na wskazanej pozycji jest
        gwarantowane.
        Nowe pozycje są uzupełniane nullami.
     */
    fun forceAddAt(index: Int, element: Int) {
        if (index in 0 until size)
            addAt(index, element)
        else {
            elementData = elementData.copyOf(index + 1)
            size = elementData.size

            elementData[index] = element
        }
    }

    fun contains(element: Int): Boolean {
        return getIndexOf(element) != null
    }

    fun deleteAt(index: Int?) : Boolean{
        if (index == null || index >= size) return false

        for (i in index until (--size))
            elementData[i] = elementData[i+1]

        elementData = elementData.copyOf(size)

        return true
    }

    fun delete(element: Int): Boolean{
        return deleteAt(getIndexOf(element))
    }

    fun deleteFirst() : Boolean{
        return deleteAt(0)
    }

    fun deleteLast() : Boolean{
        return deleteAt(size-1)
    }



    fun elemAt(index: Int): Int? = if(index < size)
        elementData[index]
    else
        null

    fun getIndexOf(element: Int): Int? {
        for (i in 0 until elementData.size) {
            if (elementData[i] == element)
                return i
        }

        return null
    }

    fun toPrettyString(): String {
        val middleLine = StringBuilder("| ")
        val floorLine = StringBuilder("+")
        val wholeArray = StringBuilder("")

        elementData.forEach {
            middleLine.append("$it ")
        }
        middleLine.append("|")

        for (i in 0 until middleLine.length - 2) {
            floorLine.append("-")
        }
        floorLine.append("+")

        wholeArray.appendln(floorLine.toString())
        wholeArray.appendln(middleLine.toString())
        wholeArray.appendln(floorLine.toString())

        return wholeArray.toString()
    }

    fun getSize(): Int {
        return this.size
    }

}