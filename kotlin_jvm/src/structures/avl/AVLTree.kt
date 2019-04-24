package structures.avl

import kotlin.random.Random
import kotlin.random.nextInt

class AVLTree {

    var root: AbstractAVLNode = NilAVL

    fun insert(key: Int): Boolean {
        if (root is NilAVL)
            root = AVLNode(key, 0, NilAVL, NilAVL, NilAVL)
        else {
            var n: AbstractAVLNode = root
            var parent: AbstractAVLNode
            while (true) {
                if (n !is NilAVL && n.key == key) return false
                parent = n
                val goLeft = n.key > key
                n = if (goLeft) n.leftChild else n.rightChild

                if (n is NilAVL) {
                    if (goLeft) parent.leftChild = AVLNode(key = key, parent = parent)
                    else parent.rightChild = AVLNode(key = key, parent = parent)

                    rebalance(parent)
                    break
                }
            }
        }
        return true
    }

    fun delete(delKey: Int) {
        if (root is NilAVL) return
        var n: AbstractAVLNode = root
        var parent: AbstractAVLNode = root
        var delNode: AbstractAVLNode = NilAVL
        var child: AbstractAVLNode = root

        //Find node to delete
        while (child !is NilAVL) {
            parent = n
            n = child
            child = if (delKey >= n.key) n.rightChild else n.leftChild
            if (delKey == n.key) delNode = n
        }

        if (delNode != NilAVL) {
            delNode.key = n.key
            child = if (n.leftChild !is NilAVL) n.leftChild else n.rightChild
            if (root.key == delKey) root = child
            else {
                if (parent.leftChild == n)
                    parent.leftChild = child
                else
                    parent.rightChild = child
                rebalance(parent)
            }
        }
    }

    fun find(key: Int): AbstractAVLNode{
        var node = root
        while(node !is NilAVL && key != node.key){
            node = if(key > node.key) node.rightChild
                    else node.leftChild
        }

        return node
    }

    private fun rebalance(n: AbstractAVLNode) {
        if (n is NilAVL) return

        updateBalance(n)
        var node = n
        if (node.balance == -2)
            node = if (height(node.leftChild.leftChild) >= height(node.leftChild.rightChild))
                rotateRight(node)
            else
                rotateLeftThenRight(node)

        else if (node.balance == 2)
            node = if (height(node.rightChild.rightChild) >= height(node.rightChild.leftChild))
                rotateLeft(node)
            else
                rotateRightThenLeft(node)

        if (node.parent !is NilAVL) rebalance(node.parent)
        else root = node
    }

    private fun rotateLeft(a: AbstractAVLNode): AbstractAVLNode {
        if (a is NilAVL) return a

        val b: AbstractAVLNode = a.rightChild
        if (b is NilAVL) return b

        b.parent = a.parent
        a.rightChild = b.leftChild
        if (a.rightChild !is NilAVL) a.rightChild.parent = a

        b.leftChild = a
        a.parent = b
        if (b.parent !is NilAVL) {
            if (b.parent.rightChild == a)
                b.parent.rightChild = b
            else
                b.parent.leftChild = b
        }
        updateBalance(a, b)
        return b
    }

    private fun rotateRight(a: AbstractAVLNode): AbstractAVLNode {
        if (a is NilAVL) return a

        val b: AbstractAVLNode = a.leftChild
        if (b is NilAVL) return b

        b.parent = a.parent
        a.leftChild = b.rightChild
        if (a.leftChild !is NilAVL) a.leftChild.parent = a
        b.rightChild = a
        a.parent = b
        if (b.parent !is NilAVL) {
            if (b.parent.rightChild == a)
                b.parent.rightChild = b
            else
                b.parent.leftChild = b
        }
        updateBalance(a, b)
        return b
    }

    private fun rotateLeftThenRight(n: AbstractAVLNode): AbstractAVLNode {
        if (n is NilAVL) return n
        n.leftChild = rotateLeft(n.leftChild)
        return rotateRight(n)
    }

    private fun rotateRightThenLeft(n: AbstractAVLNode): AbstractAVLNode {
        if (n is NilAVL) return n
        n.rightChild = rotateRight(n.rightChild)
        return rotateLeft(n)
    }

    private fun height(n: AbstractAVLNode): Int {
        if (n is NilAVL) return -1
        return 1 + Math.max(height(n.leftChild), height(n.rightChild))
    }

    private fun updateBalance(vararg nodes: AbstractAVLNode) {
        for (n in nodes) {
            if (n !is NilAVL) n.balance = height(n.rightChild) - height(n.leftChild)
        }
    }

    fun printKey() {
        printKey(root)
        println()
    }

    private fun printKey(n: AbstractAVLNode) {
        if (n !is NilAVL) {
            printKey(n.leftChild)
            print("${n.key} ")
            printKey(n.rightChild)
        }
    }

    fun printBalance() {
        printBalance(root)
        println()
    }

    private fun printBalance(n: AbstractAVLNode) {
        if (n !is NilAVL) {
            printBalance(n.leftChild)
            print("${n.balance} ")
            printBalance(n.rightChild)
        }
    }

    fun printTree() {
        printBinaryTree(root, 0)
    }

    private fun printBinaryTree(node: AbstractAVLNode, level: Int) {
        if (node is NilAVL) return
        printBinaryTree(node.rightChild, level + 1)
        if (level != 0) {
            for (i in 0 until level - 1)
                print("|\t")

            println("|---${node.key}")
        } else
            println("${node.key}")
        printBinaryTree(node.leftChild, level + 1)
    }

    companion object{
        fun generateRandom(size : Int, range : IntRange) : AVLTree{
            val tree = AVLTree()
            for(i in 1..size)
                tree.insert(Random.nextInt(range))
            return tree
        }
    }
}

sealed class AbstractAVLNode {
    abstract var key: Int
    abstract var balance: Int
    abstract var parent: AbstractAVLNode
    abstract var leftChild: AbstractAVLNode
    abstract var rightChild: AbstractAVLNode
}

data class AVLNode(
    override var key: Int,
    override var balance: Int = 0,
    override var parent: AbstractAVLNode = NilAVL,
    override var leftChild: AbstractAVLNode = NilAVL,
    override var rightChild: AbstractAVLNode = NilAVL
) : AbstractAVLNode() {

    override fun toString(): String {
        return "V: ${this.key} H: $balance"
    }
}

object NilAVL : AbstractAVLNode() {
    override var balance: Int
        get() = throw NoSuchElementException("NilAVL")
        set(value) {}
    override var parent: AbstractAVLNode
        get() = throw NoSuchElementException("NilAVL")
        set(value) {}
    override var key: Int
        get() = throw NoSuchElementException("NilAVL")
        set(value) {}
    override var leftChild: AbstractAVLNode
        get() = throw NoSuchElementException("NilAVL")
        set(value) {}
    override var rightChild: AbstractAVLNode
        get() = throw NoSuchElementException("NilAVL")
        set(value) {}
}
