package sample.helloworld.structures.heap

import kotlin.random.Random
import kotlin.random.nextInt

class HeapSDiZO(private val maxsize: Int) {
    private val heap: IntArray
    private var size: Int = 0

    init {
        this.size = 0
        heap = IntArray(this.maxsize + 1)
    }

    private fun left(i: Int) = i * 2 + 1
    private fun right(i: Int) = i * 2 + 2
    private fun parent(i: Int) = (i - 1) / 2

    private fun isLeaf(pos: Int): Boolean {
        return pos >= (size / 2) && pos <= size
    }

    private fun swap(fpos: Int, spos: Int) {
        val tmp: Int = heap[fpos]
        heap[fpos] = heap[spos]
        heap[spos] = tmp
    }

    private fun maxHeapify(pos: Int) {
        if (isLeaf(pos))
            return

        if (heap[pos] < heap[left(pos)] || heap[pos] < heap[right(pos)]) {

            if (heap[left(pos)] > heap[right(pos)]) {
                swap(pos, left(pos))
                maxHeapify(left(pos))
            } else {
                swap(pos, right(pos))
                maxHeapify(right(pos))
            }
        }
    }

    // Inserts a new element to max heap
    fun insert(element: Int) {
        heap[++size] = element

        // Traverse up and fix violated property
        var current = size
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current))
            current = parent(current)
        }
    }

    fun delete(key: Int){
        val toDel = find(key)

        if(toDel == size-1){
            heap[--size] = 0
            return
        }

        swap(toDel,--size)

        var current = toDel
        if( heap[toDel] > heap[parent(toDel)]){
            while (heap[current] > heap[parent(current)]) {
                swap(current, parent(current))
                current = parent(current)
            }
        }else{
            maxHeapify(current)
        }

    }

    fun findFarthestRight(ind: Int) : Int{

        var elem = ind

        while (!isLeaf(elem)){
            elem = left(elem)
        }

        elem = parent(elem)

        return right(elem)

    }

    // Remove an element from max heap
    fun extractMax(): Int {
        val popped = heap[1]
        heap[1] = heap[size--]
        maxHeapify(1)
        return popped
    }

    fun find(key: Int): Int{
        for(i in 0 until size){
            if(heap[i] == key) return i
        }

        return Integer.MAX_VALUE
    }

    fun contains(key: Int): Boolean{
        for(i in 0 until size){
            if(heap[i] == key) return true
        }

        return false
    }

    companion object {

        @JvmStatic
        fun main(arg: Array<String>) {
            println("The Max heap is ")
            val maxHeap = HeapSDiZO(15)

            with(maxHeap) {
                insert(7)
                insert(5)
                insert(9)
                insert(6)
                insert(7)
                insert(8)
                insert(10)
                insert(1)
                insert(11)

                printTree(0, 0)
            }
        }

        fun generateRandom(count: Int, range: IntRange): HeapSDiZO {
            val heap = HeapSDiZO(count+10)

            for (i in 1 until count)
                heap.insert(Random.nextInt(range))

            return heap
        }
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

            println("|---" + heap[ind])
        } else
            println(heap[ind])
        printTree(left(ind), level + 1)
    }

}
