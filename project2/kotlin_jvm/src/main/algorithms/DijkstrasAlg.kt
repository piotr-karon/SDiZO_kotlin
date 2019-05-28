package main.algorithms

import main.Graph

class DijkstrasAlg(private val graph: Graph, private val startPoint: Int){

    private var costArray = Array(graph.V){VertCost(it,Int.MAX_VALUE)}
    private var costQueue = Queue(costArray)
    var predecessors = Array(graph.V){-1}
        private set

    private var isAppended = Array(graph.V){false}

    fun shortestPathAdjList(){
        clear()

        costQueue.setCost(startPoint, 0)
        costArray[startPoint].cost = 0

        for(i in 0 until graph.V){
            val u = costQueue.pop()
            if(u.name < 0) break // Queue specific -> -1 element may occur

            isAppended[u.name] = true

            for(vert in graph.adjList[u.name]){
                if(isAppended[vert.name]) continue

                val wIndex = costQueue.find(vert.name) // TODO another array for holding vert's position in queue

                if(wIndex != null){
                    val otherCost = u.cost + vert.costTo

                    if(costQueue[wIndex].cost > otherCost){
                        predecessors[costQueue[wIndex].name] = u.name
                        costQueue.setCostInd(wIndex, otherCost)
                        costArray[vert.name].cost = otherCost
                    }
                }

            }
        }
    }

    fun shortestPathAdjMatrix(){
        clear()

        costQueue.setCost(startPoint, 0)
        costArray[startPoint].cost = 0

        for(i in 0 until graph.V){
            val u = costQueue.pop()
            if(u.name < 0) break // Queue specific -> -1 element may occur

            isAppended[u.name] = true

            for((x,vert) in graph.adjMatrix[u.name].withIndex()){
                if(vert == Int.MAX_VALUE || vert < 0 || isAppended[x] ) continue

                val wIndex = costQueue.find(x) // TODO another array for holding vert's position in queue

                if(wIndex != null){
                    val otherCost = u.cost + vert

                    if(costQueue[wIndex].cost > otherCost){
                        predecessors[costQueue[wIndex].name] = u.name
                        costQueue.setCostInd(wIndex, otherCost)
                        costArray[x].cost = otherCost
                    }
                }

            }

        }
    }

    fun getPaths(): Array<MutableList<String>>{
        val result = Array(graph.V){mutableListOf<String>()}

        for(i in predecessors.indices){

            var ind: Int = i

            do {
                ind = predecessors[ind]
                if(ind > -1) result[i].add("$ind")
            }while (ind > -1)
            result[i].add("cost:${costArray[i].cost}")
        }

        return result
    }

    private fun clear(){
        costArray = Array(graph.V){VertCost(it,Int.MAX_VALUE)}
        costQueue = Queue(costArray)
        predecessors = Array(graph.V){-1}
        isAppended = Array(graph.V){false}
    }

    private class VertCost(var name: Int = -1, var cost:Int = Int.MAX_VALUE): Comparable<VertCost>{
        override fun compareTo(other: VertCost): Int {
            return cost - other.cost
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as VertCost
            if(this.name != other.name) return false
            return true
        }

        override fun hashCode(): Int {
            var result = name
            result = 31 * result + cost
            return result
        }

        override fun toString(): String {
            return "$name:$cost"
        }

    }

    private class Queue {

        private var heap: Array<VertCost>
        var size: Int = 0
            private set
        private var maxsize: Int = 10

        constructor(maxsize: Int){
            this.size = 0
            this.maxsize = maxsize
            heap = Array(maxsize) { VertCost(it) }
        }

        constructor(array: Array<VertCost>) : this(array.size){
            for(edge in array)
                push(edge)
        }

        operator fun get(i: Int) = heap[i]

        fun push(element: VertCost) {
            if(size >= maxsize) throw NoSuchElementException("No space in queue!")
            heap[size] = element

            var current = size
            size++
            while (heap[current] < heap[parent(current)]) {
                swap(current, parent(current))
                current = parent(current)
            }

        }

        fun delete(key: VertCost){
            val toDel = find(key) ?: return

            if(toDel == size-1){
                heap[--size] = VertCost()
                return
            }

            swap(toDel,--size)
            heap[size] = VertCost()

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

        fun pop(): VertCost {
            val popped = heap[0]
            if(size>1){
                heap[0] = heap[--size]
                heap[size] = VertCost()
                heapify(0)
            }else{
                heap[size] = VertCost()
            }
            return popped
        }

        fun find(key: VertCost): Int?{
            for(i in 0 until size){
                if(heap[i] == key) return i
            }

            return null
        }

        fun find(vert: Int): Int?{
            for(i in 0 until size){
                if(heap[i].name == vert) return i
            }

            return null
        }

        fun setCostInd(vertCostInd: Int?, cost: Int){
            if(vertCostInd != null){
                heap[vertCostInd].cost = cost

                var current = vertCostInd

                if(heap[vertCostInd] < heap[parent(vertCostInd)]){
                    while (heap[current!!] < heap[parent(current)]) {
                        swap(current, parent(current))
                        current = parent(current)
                    }
                }else{
                    heapify(current)
                }
            }
        }

        fun setCost(vert: Int, cost: Int){
            setCostInd(find(vert), cost)
        }

        fun contains(key: VertCost): Boolean{
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
            val tmp: VertCost = heap[fpos]
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

}

