package sample.helloworld.test

import structures.bst.BST
import structures.bst.Nil
import structures.bst.Node
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BSTTest {

    @Test
    fun bstTest() {
        val bst = BST()

        println(bst.inOrder())

        with(bst) {
            insert(5)
            insert(2)
            insert(8)
            println(inOrder())
        }
    }

    @Test
    fun bstFindTest() {
        val bst = BST()

        with(bst) {
            insert(5)
            insert(2)
            insert(8)
        }

        assertTrue(bst.find(2) !is Nil)
    }

    @Test
    fun bstNotFindTest() {
        val bst = BST()

        with(bst) {
            insert(5)
            insert(2)
            insert(8)
        }

        assertTrue(bst.find(22) is Nil)
    }


    @Test
    fun bstRotateTest() {
        val bst = BST()

        with(bst) {
            insert(4)
            insert(2)
            insert(5)

            assertEquals(4, root.value)
            assertEquals(2, root.leftChild.value)
            assertEquals(5, root.rightChild.value)
        }

    }

    @Test
    fun bstExtractTest() {
        val bst = BST()

        with(bst) {
            insert(5)
            insert(6)
            insert(9)
            insert(4)
            insert(3)
            insert(2)

            bst.extract(6)

            printTree()
        }
    }

    @Test
    fun bstExtractSingleTest(){
        val bst = BST()
        bst.root = Node(10, Nil, Nil, Nil)
        assertTrue(bst.extract(10) !is Nil)
    }

    @Test
    fun shouldNotExtractBecauseNotExists(){
        val bst = BST()
        bst.root = Node(10, Nil, Nil, Nil)
        assertTrue(bst.extract(20) is Nil)
    }

    @Test
    fun bstExtract2Test() {
        val bst = BST.generateRandom(1000, IntRange(1,20000))
        for(i in 0..10)
            bst.extract(Random.nextInt(1,20000))
    }

    @Test
    fun testDSW() {
        val bst = BST()

        with(bst) {
            insert(9)
            insert(5)
            insert(7)
            insert(18)
            insert(19)
            insert(15)
            insert(10)

            bst.balanceDSW()

            assertEquals(10, root.value)
            assertEquals(7, root.leftChild.value)
            assertEquals(18, root.rightChild.value)
            assertEquals(19, root.rightChild.rightChild.value)
            assertEquals(15, root.rightChild.leftChild.value)
            assertEquals(5, root.leftChild.leftChild.value)
            assertEquals(9, root.leftChild.rightChild.value)
        }

    }

    @Test
    fun test2DSW() {
        val bst = BST()

        with(bst) {
            for (i in 0..100)
                insert(Random.nextInt(0, 100))

            balanceDSW()
            printTree()
        }

    }

    @Test
    fun rotateLeftTest() {
        val bst = BST()

        val node1 = Node(1, Nil, Nil, Nil)
        val node2 = Node(2, node1, Nil, Nil)
        val node3 = Node(3, node2, Nil, Nil)

        node1.rightChild = node2
        node2.rightChild = node3
        bst.root = node1

        bst.printTree()
        println("\n--------\n")
        bst.rotateLeft(node2)
        bst.printTree()

        with(bst) {
            assertEquals(root, node1)
            assertEquals(root.rightChild, node3)
            assertEquals(root.rightChild.leftChild, node2)
        }
    }
}