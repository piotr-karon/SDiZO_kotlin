package sample.helloworld.test

import org.junit.Test
import sample.helloworld.structures.BST.BST
import sample.helloworld.structures.BST.Nil
import kotlin.test.assertEquals

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

        assert(bst.find(2) !is Nil)
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
}