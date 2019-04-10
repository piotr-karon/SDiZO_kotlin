package sample.helloworld.structures

class AVLTree {

    var root: AbstractAVLNode = NilAVL

    fun insert(key: Int) : AbstractAVLNode{

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

    private fun balanceTree(w : AbstractAVLNode){

        var p = w.parent

        if (p.bf != 0) {    // Ojciec nowododanego węzła ma jednego syna bf = 1 lub -1
            p.bf = 0        // Jeśl dodamy mu potomka to bf musi się wyzerować
            return
        }

        p.bf = if (p.leftChild == w) 1 else -1

        var r = p.parent //r - ojciec, p - syn

        while(r !is NilAVL){

            if(r.bf !=0){

                if(r.bf == -1){
                    if(r.leftChild == p){
                        r.bf = 0
                        return
                    }
                    if(p.bf == 1){
                        rotateRL(r)
                    }else{
                        rotateRR(r)
                    }

                }

                if(r.rightChild == p){
                    r.bf = 0
                    return
                }

                if(p.bf==-1){
                    rotateLR(r)
                }else{
                    rotateLL(r)
                }

            }

            if(r.leftChild == p){
                r.bf = 1
            }else{
                r.bf = -1
            }

            p = r
            r = r.parent

        }

    }

    fun find(key: Int): AbstractAVLNode {
        return find(root, key)
    }

    fun extract(key: Int): AbstractAVLNode {
        val x = find(key)

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

    fun predecessor(AVLNode: AbstractAVLNode) :AbstractAVLNode{

        var node = AVLNode

        if(node is NilAVL) return node

        if(node.leftChild !is NilAVL) return treeMaximum(node.leftChild)

        var r = node.parent

        while(r !is NilAVL && node == r.leftChild){
            node = r
            r = r.parent
        }

        return r
    }

    fun printTree() {
        println(" \n----------------\n ")
        printBinaryTree(root, 0)
    }

    fun rotateRR(AVLNodeA: AbstractAVLNode) {
        if (AVLNodeA is NilAVL || AVLNodeA.rightChild is NilAVL) return

        val nodeB = AVLNodeA.rightChild
        val parentA = AVLNodeA.parent

        AVLNodeA.rightChild = nodeB.leftChild
        if (AVLNodeA.rightChild !is NilAVL) AVLNodeA.rightChild.parent = AVLNodeA
        nodeB.leftChild = AVLNodeA
        nodeB.parent = parentA
        AVLNodeA.parent = nodeB

        if (parentA is NilAVL) {
            root = nodeB

        }else{
            if (parentA.leftChild == AVLNodeA) parentA.leftChild = nodeB
            else parentA.rightChild = nodeB
        }

        if (nodeB.bf == -1) {
            AVLNodeA.bf = 0
            nodeB.bf = 0
        } else {
            AVLNodeA.bf = -1
            nodeB.bf = 1
        }

    }

    fun rotateLL(AVLNodeA: AbstractAVLNode) {
        if (AVLNodeA is NilAVL || AVLNodeA.leftChild is NilAVL) return

        val nodeB = AVLNodeA.leftChild
        val parentA = AVLNodeA.parent

        AVLNodeA.leftChild = nodeB.rightChild
        if (AVLNodeA.leftChild !is NilAVL) AVLNodeA.leftChild.parent = AVLNodeA
        nodeB.rightChild = AVLNodeA
        nodeB.parent = parentA
        AVLNodeA.parent = nodeB

        if (parentA is NilAVL) {
            root = nodeB
        }else{
            if (parentA.rightChild == AVLNodeA) parentA.rightChild = nodeB
            else parentA.leftChild = nodeB
        }

        if (nodeB.bf == 1) {
            AVLNodeA.bf = 0
            nodeB.bf = 0
        } else {
            AVLNodeA.bf = 1
            nodeB.bf = -1
        }

    }

    fun rotateRL(AVLNodeA: AbstractAVLNode) {
        val nodeB = AVLNodeA.rightChild
        val nodeC = nodeB.leftChild
        val par = AVLNodeA.parent

        nodeB.leftChild = nodeC.rightChild
        if (nodeB.leftChild !is NilAVL) nodeB.leftChild.parent = nodeB
        AVLNodeA.rightChild = nodeC.leftChild
        if (AVLNodeA.rightChild !is NilAVL) AVLNodeA.rightChild.parent = AVLNodeA

        nodeC.leftChild = AVLNodeA
        nodeC.rightChild = nodeB
        AVLNodeA.parent = nodeC
        nodeB.parent = nodeC
        nodeC.parent = par

        // ##TO-DO## check conditions
        if (par is NilAVL) root = nodeC
        else if (par.leftChild == AVLNodeA) par.leftChild = nodeC
        else par.rightChild = nodeC

        if (nodeC.bf == -1) AVLNodeA.bf = 1 else AVLNodeA.bf = 0

        if (nodeC.bf == 1) nodeB.bf = -1 else nodeB.bf = 0

        nodeC.bf = 0

    }

    fun rotateLR(AVLNodeA: AbstractAVLNode) {
        val nodeB = AVLNodeA.leftChild
        val nodeC = nodeB.rightChild
        val par = AVLNodeA.parent

        nodeB.rightChild = nodeC.leftChild
        if (nodeB.rightChild !is NilAVL) nodeB.rightChild.parent = nodeB
        AVLNodeA.leftChild = nodeC.rightChild
        if (AVLNodeA.leftChild !is NilAVL) AVLNodeA.leftChild.parent = AVLNodeA

        nodeC.rightChild = AVLNodeA
        nodeC.leftChild = nodeB
        AVLNodeA.parent = nodeC
        nodeB.parent = nodeC
        nodeC.parent = par

        // ##TO-DO## check conditions
        if (par is NilAVL) root = nodeC
        else if (par.rightChild == AVLNodeA) par.rightChild = nodeC
        else par.leftChild = nodeC

        if (nodeC.bf == 1) AVLNodeA.bf = -1 else AVLNodeA.bf = 0

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

    private fun printBinaryTree(root: AbstractAVLNode, level: Int) {
        if (root is NilAVL)
            return
        printBinaryTree(root.rightChild, level + 1)
        if (level != 0) {
            for (i in 0 until level - 1)
                print("|\t")

            println("|---${root.key}:${root.bf}")
        } else
            println("${root.key}:${root.bf}")
        printBinaryTree(root.leftChild, level + 1)
    }

    private fun backbone() {
        var temp = root

        while (temp !is NilAVL) {
            temp = if (temp.leftChild !is NilAVL) {
                rotateLL(temp)
                temp.parent
            } else {
                temp.rightChild
            }
        }
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

        return x

    }

    private fun inOrder(AVLNode: AbstractAVLNode, list: MutableList<Int>) {
        if (AVLNode !is NilAVL) {
            inOrder(AVLNode.leftChild, list)
            list.add(AVLNode.key)
            inOrder(AVLNode.rightChild, list)
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