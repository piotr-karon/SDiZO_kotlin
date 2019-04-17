package structures

import structures.avl.AVLTree
import structures.bst.BST
import structures.heap.HeapSDiZO
import structures.list.ListSDiZO
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureTimeMillis

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
        val l3 = heapExtractMaxTest(num)

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

        val result = mutableListOf<String>()
        result.add("Array,add")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val arr = ArraySDiZO()

            time += measureTimeMillis {
                for (i in 0 until count)
                    arr.add(numbers[i])
            }

        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun arrayFindTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Array,contains()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val arr = ArraySDiZO.generateRandom(count, range)

            time += measureTimeMillis {
                for (i in 0 until count)
                    arr.contains(numbers[i])
            }
            
        }
        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())
        return result
    }

    fun arrayDeleteTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Array,delete()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val arr = ArraySDiZO.generateRandom(count, range)

            time += measureTimeMillis {
                for (i in 0 until count)
                    arr.delete(numbers[i])
            }
        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun listAddTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("List,add()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val list = ListSDiZO(0)

            time += measureTimeMillis {
                for (i in 0 until count)
                    list.add(numbers[i])
            }

        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun listFindTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("List,contains()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val list = ListSDiZO.generateRandom(count, range)

            time += measureTimeMillis {
                for (i in 0 until count)
                    list.contains(numbers[i])
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

        for (j in 1..times) {
            val arr = ListSDiZO.generateRandom(count, range)

            time += measureTimeMillis {
                for (i in 0 until count)
                    arr.deleteValue(numbers[i])
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

        for (j in 1..times) {
            val struct = BST()

            time += measureTimeMillis {
                for (i in 0 until count)
                    struct.insert(numbers[i])
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

        for (j in 1..times) {
            val struct = BST.generateRandom(count, range)
            time += measureTimeMillis {
                for (i in 0 until count)
                    struct.find(numbers[i])
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
        var struct: BST

        for (j in 1..times) {
            struct = BST.generateRandom(count, range)

            time += measureTimeMillis {
                struct.insert(1)
                for (i in 0 until count) {
                    try {
                        struct.delete(numbers[i])
                    } catch (e: NoSuchElementException) {
                    }
                }
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

        for (j in 1..times) {
            val struct = BST()

            time += measureTimeMillis {
                for (i in 0 until count){
                    struct.insert(numbers[i])
                    if(i%50 == 0) struct.balanceDSW()
                }

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
        var struct: BST
        for (j in 1..times) {
            struct = BST.generateRandomBalanced(count, range)

            time += measureTimeMillis {
                for (i in 0 until count) {
                    try {
                        struct.delete(numbers[i])
                        if(i%50 == 0) struct.balanceDSW()
                    } catch (e: NoSuchElementException) {
                        struct.balanceDSW()
                    }
                }
            }
        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun bstBalanceTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Bst,balance()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val struct = BST.generateRandom(count, range)

            time += measureTimeMillis {
                for (i in 0 until count)
                    struct.balanceDSW()
            }
        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun heapAddTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Heap,insert()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val struct = HeapSDiZO(count+1)

            time += measureTimeMillis {
                for (i in 0 until count)
                    struct.insert(numbers[i])
            }

        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun heapDeleteTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Heap,delete()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val struct = HeapSDiZO.generateRandom(count, range)

            time += measureTimeMillis {
                for (i in 0 until count)
                    struct.delete(numbers[i])
            }

        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun heapFindTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Heap,contains()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val struct = HeapSDiZO.generateRandom(count, range)

            time += measureTimeMillis {
                for (i in 0 until count)
                    struct.contains(numbers[i])
            }
            
        }
        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())
        return result
    }

    fun heapExtractMaxTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Heap,extractMax()")
        result.add("$times")

        var time: Long = 0
        var struct: HeapSDiZO

        for (j in 1..times) {
            struct = HeapSDiZO.generateRandom(count, range) //# WTF?!

            time += measureTimeMillis {
                struct.extractMax()
                for (i in 0 until count) {
                    try {
                        struct.extractMax()
                    } catch (e: NoSuchElementException) {
                    }
                }
            }
        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun avlAddTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Avl,insert()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val struct = AVLTree()

            time += measureTimeMillis {
                for (i in 0 until count)
                    struct.insert(numbers[i])
            }

        }

        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())

        return result
    }

    fun avlFindTest(times: Int): MutableList<String> {

        val result = mutableListOf<String>()
        result.add("Avl,contains()")
        result.add("$times")

        var time: Long = 0

        for (j in 1..times) {
            val struct = AVLTree.generateRandom(count, range)

            time += measureTimeMillis {
                for (i in 0 until count)
                    struct.find(numbers[i])
            }
            
        }
        val avg = time / times
        //result.add(avg.toString())
        result.add(time.toString())

        result.add(this.count.toString())
        return result
    }
}
