package structures.avl

import structures.bst.Nil
import kotlin.random.Random
import kotlin.random.nextInt

class AVLTree {

    var root: AbstractAVLNode = NilAVL

    fun insert(key: Int): AbstractAVLNode {

        var p: AbstractAVLNode = NilAVL
        var x: AbstractAVLNode = root
        val w = AVLNodeAVL(key, 0, NilAVL, NilAVL, NilAVL)

        while (x !is NilAVL) {
            p = x
            x = if (w.key < x.key) x.leftChild
            else x.rightChild
        }

        w.parent = p

        if (p is NilAVL) {
            root = w
            return w
        } else {
            if (w.key < p.key) p.leftChild = w
            else p.rightChild = w
        }

        balanceTree(w)

        return w
    }

    private fun balanceTree(w: AbstractAVLNode) {

        var p = w.parent

        if (p.bf != 0) {    // Ojciec nowododanego węzła ma jednego syna bf = 1 lub -1
            p.bf = 0        // Jeśl dodamy mu potomka to bf musi się wyzerować
            return
        }

        p.bf = if (p.leftChild == w) 1 else -1

        var r = p.parent //r - ojciec, p - syn

        while (r !is NilAVL) {

            if (r.bf != 0) {

                if (r.bf == -1) {
                    if (r.leftChild == p) {
                        r.bf = 0
                        return
                    }
                    if (p.bf == 1) {
                        rotateRL(r)
                    } else {
                        rotateRR(r)
                    }

                }

                if (r.rightChild == p) {
                    r.bf = 0
                    return
                }

                if (p.bf == -1) {
                    rotateLR(r)
                } else {
                    rotateLL(r)
                }

            }

            if (r.leftChild == p) {
                r.bf = 1
            } else {
                r.bf = -1
            }

            p = r
            r = r.parent

        }

    }

    fun find(key: Int): AbstractAVLNode {
        return find(root, key)
    }

    fun delete(key: Int): AbstractAVLNode{
        val x = find(key)
        return if(x !is NilAVL){
            delete(x)
        }else{
            x
        }
    }

    fun delete(toDel: AbstractAVLNode): AbstractAVLNode {
        val x = toDel
        var t : AbstractAVLNode
        var y : AbstractAVLNode
        var z : AbstractAVLNode
        val nest: Boolean

        //K01
        if(x.leftChild is NilAVL|| x.rightChild is NilAVL){

            //K05
            if(x.leftChild !is NilAVL){
                y = x.leftChild
                x.leftChild = NilAVL
            }else{
                y = x.rightChild
                x.rightChild = NilAVL
            }

            x.bf = 0
            nest = true

        }else{
            y = delete(predecessor(x))
            nest = false
        }

        //K08
        if(y !is NilAVL){

            y.parent = x.parent
            y.leftChild = x.leftChild

            if(y !is NilAVL) y.leftChild.parent = y

            y.rightChild = x.rightChild

            if(y.rightChild !is NilAVL) y.rightChild.parent = y

            y.bf = x.bf

        }

        //K15
        if(x.parent is NilAVL){
            if(x.parent.leftChild == x) x.parent.leftChild = y
            else x.parent.rightChild = y
        }else{
            root = y
        }

        if(!nest) return x

        z = y
        y = x.parent

        //K22
        while (y !is NilAVL){
            //K23
            if(y.bf != 0){

                //K26
                if( (y.bf != 1 || y.leftChild != z) && (y.bf != -1 || y.rightChild != z)){

                    //K31
                    t = if(y.leftChild == z) y.rightChild else y.leftChild

                    //K32
                    if(t.bf != 0){
                        if(y.bf == 1) rotateLL(y)
                        else rotateRR(y)
                        return x
                    }else{
                        //K35
                        if(y.bf != t.bf){
                            //K40
                            if(y.bf == 1) rotateLR(y)
                            else rotateRL(y)
                            z = y.parent
                            y = z.parent
                        }else{
                            if(y.bf == 1) rotateLL(y)
                            else rotateRR(y)
                            z = t
                            y = t.parent
                            continue
                        }
                    }

                }else{
                    y.bf = 0
                    z = y
                    y = y.parent
                    continue
                }
            }else{
                //K24
                if(y.leftChild == z) y.bf = -1
                return x
            }

        }

        return x
    }

    fun treeMinimum(AVLNode: AbstractAVLNode): AbstractAVLNode {
        var node = AVLNode

        while (node !is NilAVL)
            node = node.leftChild

        return node
    }

    fun treeMaximum(AVLNode: AbstractAVLNode): AbstractAVLNode {
        var node = AVLNode

        while (node !is NilAVL)
            node = node.rightChild

        return node
    }

    fun successor(AVLNode: AbstractAVLNode): AbstractAVLNode {
        var node = AVLNode
        if (node.rightChild !is NilAVL)
            return treeMinimum(node.rightChild)

        var y = node.parent

        while (y !is NilAVL && node == y.rightChild) {
            node = y
            y = y.parent
        }
        return y
    }

    fun predecessor(AVLNode: AbstractAVLNode): AbstractAVLNode {

        var node = AVLNode

        if (node is NilAVL) return node

        if (node.leftChild !is NilAVL) return treeMaximum(node.leftChild)

        var r = node.parent

        while (r !is NilAVL && node == r.leftChild) {
            node = r
            r = r.parent
        }

        return r
    }

    fun printTree() {
        println(" \n----------------\n ")
        printBinaryTree(root, 0)
    }

    fun rotateRR(nodeA: AbstractAVLNode) {
        if (nodeA is NilAVL || nodeA.rightChild is NilAVL) return

        val nodeB = nodeA.rightChild
        val parentA = nodeA.parent

        nodeA.rightChild = nodeB.leftChild
        if (nodeA.rightChild !is NilAVL) nodeA.rightChild.parent = nodeA
        nodeB.leftChild = nodeA
        nodeB.parent = parentA
        nodeA.parent = nodeB

        if (parentA is NilAVL) {
            root = nodeB

        } else {
            if (parentA.leftChild == nodeA) parentA.leftChild = nodeB
            else parentA.rightChild = nodeB
        }

        if (nodeB.bf == -1) {
            nodeA.bf = 0
            nodeB.bf = 0
        } else {
            nodeA.bf = -1
            nodeB.bf = 1
        }

    }

    fun rotateLL(nodeA: AbstractAVLNode) {
        if (nodeA is NilAVL || nodeA.leftChild is NilAVL) return

        val nodeB = nodeA.leftChild
        val parentA = nodeA.parent

        nodeA.leftChild = nodeB.rightChild
        if (nodeA.leftChild !is NilAVL) nodeA.leftChild.parent = nodeA
        nodeB.rightChild = nodeA
        nodeB.parent = parentA
        nodeA.parent = nodeB

        if (parentA is NilAVL) {
            root = nodeB
        } else {
            if (parentA.rightChild == nodeA) parentA.rightChild = nodeB
            else parentA.leftChild = nodeB
        }

        if (nodeB.bf == 1) {
            nodeA.bf = 0
            nodeB.bf = 0
        } else {
            nodeA.bf = 1
            nodeB.bf = -1
        }

    }

    fun rotateRL(nodeA: AbstractAVLNode) {
        if (nodeA.rightChild is NilAVL || nodeA.rightChild.leftChild is NilAVL) return

        val nodeB = nodeA.rightChild
        val nodeC = nodeB.leftChild
        val par = nodeA.parent

        nodeB.leftChild = nodeC.rightChild
        if (nodeB.leftChild !is NilAVL) nodeB.leftChild.parent = nodeB
        nodeA.rightChild = nodeC.leftChild
        if (nodeA.rightChild !is NilAVL) nodeA.rightChild.parent = nodeA

        nodeC.leftChild = nodeA
        nodeC.rightChild = nodeB
        nodeA.parent = nodeC
        nodeB.parent = nodeC
        nodeC.parent = par

        // ##TO-DO## check conditions
        if (par is NilAVL) root = nodeC
        else if (par.leftChild == nodeA) par.leftChild = nodeC
        else par.rightChild = nodeC

        if (nodeC.bf == -1) nodeA.bf = 1 else nodeA.bf = 0

        if (nodeC.bf == 1) nodeB.bf = -1 else nodeB.bf = 0

        nodeC.bf = 0

    }

    fun rotateLR(nodeA: AbstractAVLNode) {
        if (nodeA.leftChild is NilAVL || nodeA.leftChild.rightChild is NilAVL) return

        val nodeB = nodeA.leftChild
        val nodeC = nodeB.rightChild
        val par = nodeA.parent

        nodeB.rightChild = nodeC.leftChild
        if (nodeB.rightChild !is NilAVL) nodeB.rightChild.parent = nodeB
        nodeA.leftChild = nodeC.rightChild
        if (nodeA.leftChild !is NilAVL) nodeA.leftChild.parent = nodeA

        nodeC.rightChild = nodeA
        nodeC.leftChild = nodeB
        nodeA.parent = nodeC
        nodeB.parent = nodeC
        nodeC.parent = par

        if (par is NilAVL) root = nodeC
        else if (par.rightChild == nodeA) par.rightChild = nodeC
        else par.leftChild = nodeC

        if (nodeC.bf == 1) nodeA.bf = -1 else nodeA.bf = 0

        if (nodeC.bf == -1) nodeB.bf = 1 else nodeB.bf = 0

        nodeC.bf = 0

    }

    fun inOrder(): List<Int> {
        val ret = mutableListOf<Int>()
        inOrder(root, ret)
        return ret
    }

    fun inOrderNodes(): MutableList<AVLNodeAVL> {
        val list = mutableListOf<AVLNodeAVL>()
        inOrderNodesFrom(root, list)
        return list
    }

    fun preOrder(): MutableList<Int> {
        val ret = mutableListOf<Int>()
        preOrder(root, ret)
        return ret
    }

    private fun inOrderNodesFrom(AVLNode: AbstractAVLNode, list: MutableList<AVLNodeAVL>) {
        if (AVLNode !is NilAVL) {
            inOrderNodesFrom(AVLNode.leftChild, list)
            list.add(AVLNode as AVLNodeAVL)
            inOrderNodesFrom(AVLNode.rightChild, list)
        }
    }

    private fun preOrder(AVLNode: AbstractAVLNode, list: MutableList<Int>) {
        if (AVLNode !is NilAVL) {
            list.add(AVLNode.key)
            inOrder(AVLNode.leftChild, list)
            inOrder(AVLNode.rightChild, list)
        }
    }

    private fun printBinaryTree(node: AbstractAVLNode, level: Int) {
        if (node is NilAVL)
            return
        printBinaryTree(node.rightChild, level + 1)
        if (level != 0) {
            for (i in 0 until level - 1)
                print("|\t")

            println("|---${node.key}")
        } else
            println("${node.key}")
        printBinaryTree(node.leftChild, level + 1)
    }

    private fun find(AVLNode: AbstractAVLNode, key: Int): AbstractAVLNode {
        var x = AVLNode

        while (x !is NilAVL && key != x.key) {
            x = if (key < x.key) {
                x.leftChild
            } else {
                x.rightChild
            }
        }

        return if (x !is NilAVL && x.key == key) x else NilAVL

    }

    private fun inOrder(AVLNode: AbstractAVLNode, list: MutableList<Int>) {
        if (AVLNode !is NilAVL) {
            inOrder(AVLNode.leftChild, list)
            list.add(AVLNode.key)
            inOrder(AVLNode.rightChild, list)
        }
    }

    companion object {
        fun generateRandom(count: Int, range: IntRange): AVLTree {
            val avlTree = AVLTree()

            for (i in 1..count) {
                avlTree.insert(Random.nextInt(range))
            }

            return avlTree
        }
    }
}

sealed class AbstractAVLNode {
    abstract var key: Int
    abstract var bf: Int
    abstract var parent: AbstractAVLNode
    abstract var leftChild: AbstractAVLNode
    abstract var rightChild: AbstractAVLNode
}

data class AVLNodeAVL(
    override var key: Int,
    override var bf: Int,
    override var parent: AbstractAVLNode,
    override var leftChild: AbstractAVLNode,
    override var rightChild: AbstractAVLNode
) : AbstractAVLNode() {

    override fun toString(): String {
        return "V: ${this.key} H: $bf"
    }
}

object NilAVL : AbstractAVLNode() {
    override var bf: Int
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

class AvlTree {
    var root: Node? = null

    class Node(var key: Int, var parent: Node?) {
        var balance: Int = 0
        var leftChild : Node? = null
        var rightChild: Node? = null
    }

    fun insert(key: Int): Boolean {
        if (root == null)
            root = Node(key, null)
        else {
            var n: Node? = root
            var parent: Node
            while (true) {
                if (n!!.key == key) return false
                parent = n
                val goLeft = n.key > key
                n = if (goLeft) n.leftChild else n.rightChild
                if (n == null) {
                    if (goLeft)
                        parent.leftChild  = Node(key, parent)
                    else
                        parent.rightChild = Node(key, parent)
                    rebalance(parent)
                    break
                }
            }
        }
        return true
    }

    fun delete(delKey: Int) {
        if (root == null) return
        var n:       Node? = root
        var parent:  Node? = root
        var delNode: Node? = null
        var child:   Node? = root
        while (child != null) {
            parent = n
            n = child
            child = if (delKey >= n.key) n.rightChild else n.leftChild
            if (delKey == n.key) delNode = n
        }
        if (delNode != null) {
            delNode.key = n!!.key
            child = if (n.leftChild != null) n.leftChild else n.rightChild
            if (root!!.key == delKey)
                root = child
            else {
                if (parent!!.leftChild == n)
                    parent.leftChild = child
                else
                    parent.rightChild = child
                rebalance(parent)
            }
        }
    }

    private fun rebalance(n: Node) {
        setBalance(n)
        var nn = n
        if (nn.balance == -2)
            nn = if (height(nn.leftChild!!.leftChild) >= height(nn.leftChild!!.rightChild))
                rotateRight(nn)
            else
                rotateLeftThenRight(nn)
        else if (nn.balance == 2)
            nn = if (height(nn.rightChild!!.rightChild) >= height(nn.rightChild!!.leftChild))
                rotateLeft(nn)
            else
                rotateRightThenLeft(nn)
        if (nn.parent != null) rebalance(nn.parent!!)
        else root = nn
    }

    private fun rotateLeft(a: Node): Node {
        val b: Node? = a.rightChild
        b!!.parent = a.parent
        a.rightChild = b.leftChild
        if (a.rightChild != null) a.rightChild!!.parent = a
        b.leftChild = a
        a.parent = b
        if (b.parent != null) {
            if (b.parent!!.rightChild == a)
                b.parent!!.rightChild = b
            else
                b.parent!!.leftChild = b
        }
        setBalance(a, b)
        return b
    }

    private fun rotateRight(a: Node): Node {
        val b: Node? = a.leftChild
        b!!.parent = a.parent
        a.leftChild = b.rightChild
        if (a.leftChild != null) a.leftChild!!.parent = a
        b.rightChild = a
        a.parent = b
        if (b.parent != null) {
            if (b.parent!!.rightChild == a)
                b.parent!!.rightChild = b
            else
                b.parent!!.leftChild = b
        }
        setBalance(a, b)
        return b
    }

    private fun rotateLeftThenRight(n: Node): Node {
        n.leftChild = rotateLeft(n.leftChild!!)
        return rotateRight(n)
    }

    private fun rotateRightThenLeft(n: Node): Node {
        n.rightChild = rotateRight(n.rightChild!!)
        return rotateLeft(n)
    }

    private fun height(n: Node?): Int {
        if (n == null) return -1
        return 1 + Math.max(height(n.leftChild), height(n.rightChild))
    }

    private fun setBalance(vararg nodes: Node) {
        for (n in nodes) n.balance = height(n.rightChild) - height(n.leftChild)
    }

    fun printKey() {
        printKey(root)
        println()
    }

    private fun printKey(n: Node?) {
        if (n != null) {
            printKey(n.leftChild)
            print("${n.key} ")
            printKey(n.rightChild)
        }
    }

    fun printBalance() {
        printBalance(root)
        println()
    }

    private fun printBalance(n: Node?) {
        if (n != null) {
            printBalance(n.leftChild)
            print("${n.balance} ")
            printBalance(n.rightChild)
        }
    }

    fun printTree(){
        printBinaryTree(root,0)
    }

    private fun printBinaryTree(node: Node?, level: Int) {
        if( node == null ) return
        printBinaryTree(node.rightChild, level + 1)
        if (level != 0) {
            for (i in 0 until level - 1)
                print("|\t")

            println("|---${node.key}")
        } else
            println("${node.key}")
        printBinaryTree(node.leftChild, level + 1)
    }
}