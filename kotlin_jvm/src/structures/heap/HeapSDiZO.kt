package sample.helloworld.structures.heap

import kotlin.random.Random
import kotlin.random.nextInt

class HeapSDiZO {

    var size = 0
    var arr: Array<Int> = Array(size + 10) { 0 }

    constructor(size: Int, additional: Int) {
        arr = Array(size + additional) { 0 }
    }

    constructor(size: Int, additional: Int, range: IntRange) {
        arr = Array(size + additional) { 0 }

        for (i in 0..size)
            arr[i] = Random.nextInt(range)
    }

    constructor(array: Array<Int>) {
        this.arr = array
        size = array.size
    }

    fun heapify(ind: Int) {
        val left = left(ind)
        val right = right(ind)
        var largest = 0

        largest = if (left < arr.size && arr[left] > arr[ind]) {
            left
        } else {
            ind
        }

        if (right < arr.size && arr[right] > arr[largest]) {
            largest = right
        }

        if (largest != ind) {
            swap(ind, largest)
            heapify(largest)
        }

    }

    fun build() {
        for (i in arr.size / 2 downTo 0)
            heapify(i)
    }

    fun insert(key: Int) {
        var i = ++size

        while (i > 0 && arr[parent(i)] < key) {
            arr[i] = arr[parent(i)]
            i = parent(i)
        }

        arr[i] = key
    }

    private fun swap(ind1: Int, ind2: Int) {
        val l1 = arr[ind1]
        arr[ind1] = arr[ind2]
        arr[ind2] = l1
    }

    fun extractMax(): Int {
        val max = arr[0]

        arr[0] = arr[arr.size - 1]

        heapify(0)

        return max
    }

    fun printTree() {
        printTree(0, 0)
    }

    private fun printTree(ind: Int, level: Int) {
        if (ind >= size)
            return

        printTree(right(ind), level + 1)
        if (level != 0) {
            for (i in 0 until level - 1)
                print("|\t")

            println("|---" + arr[ind])
        } else
            println(arr[ind])
        printTree(left(ind), level + 1)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        arr.forEach { builder.append(" $it") }
        return builder.toString()
    }

    companion object {
        fun generateRandom(count: Int, range: IntRange): HeapSDiZO {
            val heap = HeapSDiZO(0, count)

            for (i in 1..count)
                heap.insert(Random.nextInt(range))

            return heap
        }
    }

    private fun left(i: Int) = i * 2 + 1
    private fun right(i: Int) = i * 2 + 2
    private fun parent(i: Int) = (i - 1) / 2

}