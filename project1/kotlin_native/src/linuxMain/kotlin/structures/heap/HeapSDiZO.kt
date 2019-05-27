package structures.heap

import kotlin.random.Random
import kotlin.random.nextInt

class HeapSDiZO(private val maxsize: Int) {

    var heap: IntArray
    var size: Int = 0

    init {
        this.size = 0
        heap = IntArray(this.maxsize)
    }

    fun insert(element: Int) {
        heap[size] = element

        var current = size
        size++
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current))
            current = parent(current)
        }

    }

    //# uswanie 9 błąd z testowania z1
    fun delete(key: Int){
        val toDel = find(key) ?: return

        if(toDel == size-1){
            heap[--size] = 0
            return
        }

        swap(toDel,--size)
        heap[size] = 0

        var current = toDel

        if( heap[toDel] > heap[parent(toDel)]){
            while (heap[current] > heap[parent(current)]) {
                swap(current, parent(current))
                current = parent(current)
            }
        }else{
            heapify(current)
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

    fun extractMax(): Int {
        val popped = heap[1]
        heap[1] = heap[size--]
        heapify(1)
        return popped
    }

    fun find(key: Int): Int?{
        for(i in 0 until size){
            if(heap[i] == key) return i
        }

        return null
    }

    fun contains(key: Int): Boolean{
        for(i in 0 until size){
            if(heap[i] == key) return true
        }

        return false
    }

    fun printTree() {
        printTree(0, 0)
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

    fun heapify(pos: Int) {
        if (isLeaf(pos))
            return

        if (heap[pos] < heap[left(pos)] || heap[pos] < heap[right(pos)]) {

            if (heap[left(pos)] > heap[right(pos)]) {
                swap(pos, left(pos))
                heapify(left(pos))
            } else {
                swap(pos, right(pos))
                heapify(right(pos))
            }
        }
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

    companion object {

        fun generateRandom(count: Int, range: IntRange): HeapSDiZO {
            val heap = HeapSDiZO(count+100)

            for (i in 1 until count)
                heap.insert(Random.nextInt(range))

            return heap
        }
    }
}
