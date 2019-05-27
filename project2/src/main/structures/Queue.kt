package main.structures

import main.Edge

class Queue {

    private var heap: Array<Edge>
    var size: Int = 0
        private set
    private var maxsize: Int = 10

    constructor(maxsize: Int){
        this.size = 0
        heap = Array(maxsize) { Edge() }
    }

    constructor(array: Array<Edge>) : this(array.size){
        for(edge in array)
            push(edge)
    }

    fun push(element: Edge) {
        if(size >= maxsize) throw NoSuchElementException("No space in queue!")
        heap[size] = element

        var current = size
        size++
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current))
            current = parent(current)
        }

    }

    fun delete(key: Edge){
        val toDel = find(key) ?: return

        if(toDel == size-1){
            heap[--size] = Edge()
            return
        }

        swap(toDel,--size)
        heap[size] = Edge()

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

    fun pop(): Edge {
        val popped = heap[0]
        if(size>1){
            heap[0] = heap[--size]
            heap[size] = Edge()
            heapify(0)
        }else{
            heap[size] = Edge()
        }
        return popped
    }

    fun find(key: Edge): Int?{
        for(i in 0 until size){
            if(heap[i] == key) return i
        }

        return null
    }

    fun contains(key: Edge): Boolean{
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
        val tmp: Edge = heap[fpos]
        heap[fpos] = heap[spos]
        heap[spos] = tmp
    }

    private fun heapify(pos: Int) {
        if (isLeaf(pos))
            return

        if (heap[pos] > heap[left(pos)] || heap[pos] > heap[right(pos)]) {

            if (heap[left(pos)] < heap[right(pos)]) {
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

            println("|-------" + heap[ind])
        } else
            println(heap[ind])
        printTree(left(ind), level + 1)
    }

}
