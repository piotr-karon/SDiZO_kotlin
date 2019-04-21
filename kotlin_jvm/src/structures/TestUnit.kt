package structures

import structures.avl.AVLTree
import structures.bst.BST
import structures.heap.HeapSDiZO
import structures.list.ListSDiZO
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureNanoTime

class TestUnit(private val count: Int, private val range: IntRange) {

    private var numbers = IntArray(count) {
        Random.nextInt(range)
    }

    fun arrayTest(num: Int) {
        val l1 = arrayAddTest(num)
        val l2 = arrayFindTest(num)
        val l3 = arrayDeleteTest(num)

        println("=====Tablica wyniki====")
        sumUp(l1)
        sumUp(l2)
        sumUp(l3)
        println("=====Tablica wyniki====\n")
    }

    fun heapTest(num: Int) {
        val l1 = heapAddTest(num)
        val l2 = heapFindTest(num)
        val l3 = heapDeleteTest(num)

        println("=====Kopiec wyniki====")
        sumUp(l1)
        sumUp(l2)
        sumUp(l3)
        println("=====Kopiec wyniki====\n")
    }

    fun listTest(num: Int) {
        val l1 = listAddTest(num)
        val l2 = listFindTest(num)
        val l3 = listDeleteTest(num)

        println("=====Lista wyniki====")
        sumUp(l1)
        sumUp(l2)
        sumUp(l3)
        println("=====Lista wyniki====\n")
    }

    fun bstTest(num: Int) {
        val l3 = bstDeleteTest(num)
        val l1 = bstAddTest(num)
        val l2 = bstFindTest(num)
        val l4 = bstAddFixTest(num)
        val l5 = bstDeleteFixTest(num)
        val l6 = bstBalanceTest(num)

        println("=====BST wyniki====")
        sumUp(l1)
        sumUp(l2)
        sumUp(l3)
        sumUp(l4)
        sumUp(l5)
        sumUp(l6)
        println("=====BST wyniki====\n")
    }

    fun avlTest(num: Int) {
        val l1 = avlAddTest(num)
        val l2 = avlFindTest(num)

        println("=====AVL wyniki====")
        sumUp(l1)
        sumUp(l2)
        println("=====AVL wyniki====\n")
    }

    fun randomize() {
        this.numbers = IntArray(count) {
            Random.nextInt(range)
        }
    }

    fun sumUp(list: MutableList<String>) {
        sumUp(list[0], list[1], list[2])
    }

    fun sumUp(name: String, num: String, avg: String) {
        print(
            "Operacja: $name wykonana $num razy dla ${this.count} elementów\n" +
                    "Średni czas: $avg ms\n"
        )
    }

    fun arrayAddTest(times: Int): MutableList<String> {

        var time: Long = 0
        val rand = ArraySDiZO.generateRandom(count, range)
        var arr: ArraySDiZO

        for (j in 1..times) {
            arr = rand
            time += measureNanoTime {
                arr.add(numbers[1])
            }
        }

        val result = mutableListOf<String>()
        result.add("Array,add")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())

        return result
    }

    fun arrayFindTest(times: Int): MutableList<String> {

        var time: Long = 0
        val rand = ArraySDiZO.generateRandom(count, range)
        var arr: ArraySDiZO

        for (j in 1..times) {
            arr = rand
            time += measureNanoTime {
                arr.contains(numbers[1])
            }

        }

        val result = mutableListOf<String>()
        result.add("Array,contains()")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())
        return result
    }

    fun arrayDeleteTest(times: Int): MutableList<String> {
        var time: Long = 0
        val rand = ArraySDiZO.generateRandom(count, range)
        var arr: ArraySDiZO

        for (j in 1..times) {
            arr = rand
            time += measureNanoTime {
                arr.delete(numbers[1])
            }
        }

        val result = mutableListOf<String>()
        result.add("Array,delete()")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())

        return result
    }

    fun listAddTest(times: Int): MutableList<String> {
        var time: Long = 0
        val rand = ListSDiZO.generateRandom(count, range)
        var list: ListSDiZO

        for (j in 1..times) {
            list = rand
            time += measureNanoTime {
                list.add(numbers[1])
            }

        }
        val result = mutableListOf<String>()
        result.add("List,add()")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())

        return result
    }

    fun listFindTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("List,contains()")
        result.add("$times")

        var time: Long = 0

        val rand = ListSDiZO.generateRandom(count, range)
        var list: ListSDiZO

        for (j in 1..times) {
            list = rand
            time += measureNanoTime {
                list.contains(numbers[1])
            }

        }
        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())
        return result
    }

    fun listDeleteTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("List,deleteValue")
        result.add("$times")

        var time: Long = 0

        val rand = ListSDiZO.generateRandom(count, range)
        var list: ListSDiZO

        for (j in 1..times) {
            list = rand
            time += measureNanoTime {
                try {
                    list.deleteValue(numbers[1])
                } catch (e: NoSuchElementException) {

                }
            }
        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun bstAddTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Bst,insert()")
        result.add("$times")

        var time: Long = 0
        val rand = BST.generateRandomBalanced(count, range)
        var struct: BST
        for (j in 1..times) {
            struct = BST.generateRandomBalanced(count, range)
            time += measureNanoTime {
                struct.insert(numbers[1])
            }

        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun bstFindTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Bst,contains()")
        result.add("$times")

        var time: Long = 0
        val rand = BST.generateRandomBalanced(count, range)
        var struct: BST
        for (j in 1..times) {
            struct = rand
            time += measureNanoTime {
                struct.find(numbers[1])
            }

        }
        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())
        return result
    }

    fun bstDeleteTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Bst,delete(random)")
        result.add("$times")

        var time: Long = 0

        val rand = BST.generateRandomBalanced(count, range)
        var struct: BST
        for (j in 1..times) {
            struct = rand
            time += measureNanoTime {
                struct.delete(numbers[1])
            }
        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun bstAddFixTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Bst,insertAndFix()")
        result.add("$times")

        var time: Long = 0

        val rand = BST.generateRandom(count, range)
        var struct: BST
        for (j in 1..times) {
            struct = rand
            time += measureNanoTime {
                struct.insert(numbers[1])
                if (j % 10 == 0) struct.balanceDSW()
            }

        }


        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun bstDeleteFixTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Bst,extractAndFix(random)")
        result.add("$times")

        var time: Long = 0
        val rand = BST.generateRandom(count, range)
        var struct: BST
        for (j in 1..times) {
            struct = rand
            time += measureNanoTime {
                struct.delete(numbers[1])
                if (j % 10 == 0) struct.balanceDSW()
            }
        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun bstBalanceTest(times: Int): MutableList<String> {
        var time: Long = 0
        val rand = BST.generateRandom(count, range)
        var struct: BST
        for (j in 1..times) {
            struct = rand
            time += measureNanoTime {
                struct.balanceDSW()
            }
        }

        val result = mutableListOf<String>()
        result.add("Bst,balance()")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())

        return result
    }

    fun heapAddTest(times: Int): MutableList<String> {
        var time: Long = 0
        var struct: HeapSDiZO

        for (j in 1..times) {
            struct = HeapSDiZO.generateRandom(count , range)
            time += measureNanoTime {
                struct.insert(numbers[1])
            }
        }

        val result = mutableListOf<String>()
        result.add("Heap,insert()")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())

        return result
    }

    fun heapDeleteTest(times: Int): MutableList<String> {
        var time: Long = 0
        val rand = HeapSDiZO.generateRandom(count, range)
        var struct: HeapSDiZO

        for (j in 1..times) {
            struct = rand
            time += measureNanoTime {
                struct.delete(numbers[1])
            }
        }

        val result = mutableListOf<String>()
        result.add("Heap,delete()")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())

        return result
    }

    fun heapFindTest(times: Int): MutableList<String> {
        var time: Long = 0
        val rand = HeapSDiZO.generateRandom(count, range)
        var struct: HeapSDiZO
        for (j in 1..times) {
            struct = rand
            time += measureNanoTime {
                struct.contains(numbers[1])
            }
        }

        val result = mutableListOf<String>()
        result.add("Heap,contains()")
        result.add("$times")
        result.add(time.toString())

        result.add(this.count.toString())
        return result
    }

    fun avlAddTest(times: Int): MutableList<String> {
        var time: Long = 0

        var struct: AVLTree
        for (j in 1..times) {
            struct = AVLTree.generateRandom(count, range)
            time += measureNanoTime {
                struct.insert(numbers[1])
            }
        }

        val result = mutableListOf<String>()
        result.add("Avl,insert()")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())

        return result
    }

    fun avlFindTest(times: Int): MutableList<String> {
        var time: Long = 0

        val rand = AVLTree.generateRandom(count, range)
        var struct: AVLTree

        for (j in 1..times) {
            struct = rand
            time += measureNanoTime {
                struct.find(numbers[1])
            }
        }

        val result = mutableListOf<String>()
        result.add("Avl,contains()")
        result.add("$times")
        result.add(time.toString())
        result.add(this.count.toString())
        return result
    }
}
