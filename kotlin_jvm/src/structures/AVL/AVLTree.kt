package sample.helloworld.structures

class AVLTree {

    var root: AbstractNode = Nil

    fun insert(key: Int) : AbstractNode{

        var p: AbstractNode = Nil
        var x: AbstractNode = root
        val w = Node(key, 0, Nil, Nil, Nil)

        while (x !is Nil) {
            p = x
            x = if (w.key < x.key) x.leftChild
            else x.rightChild
        }

        w.parent = p

        if (p is Nil) {
            root = w
            return w
        } else {
            if (w.key < p.key) p.leftChild = w
            else p.rightChild = w
        }

        balanceTree(w)

        return w
    }

    private fun balanceTree(w : AbstractNode){

        var p = w.parent

        if (p.bf != 0) {    // Ojciec nowododanego węzła ma jednego syna bf = 1 lub -1
            p.bf = 0        // Jeśl dodamy mu potomka to bf musi się wyzerować
            return
        }

        p.bf = if (p.leftChild == w) 1 else -1

        var r = p.parent //r - ojciec, p - syn

        while(r !is Nil){

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

    fun find(key: Int): AbstractNode {
        return find(root, key)
    }

    fun extract(key: Int): AbstractNode {
        val nodeDel = find(key)

        val y: AbstractNode

        y = if (nodeDel.leftChild is Nil || nodeDel.rightChild is Nil) {
            nodeDel
        } else {
            treeSuccessor(nodeDel)
        }

        val x = if (y.leftChild !is Nil) {
            y.leftChild
        } else {
            y.rightChild
        }

        if (x !is Nil) x.parent = y.parent

        if (y.parent is Nil) {
            root = x
        } else if (y == y.parent.leftChild) {
            y.parent.leftChild = x
        } else {
            y.parent.rightChild = x
        }

        if (y != nodeDel)
            nodeDel.key = y.key

        return y
    }

    fun treeMinimum(node: AbstractNode): AbstractNode {
        var node = node

        while (node !is Nil)
            node = node.leftChild

        return node
    }

    fun treeMaximum(): AbstractNode {
        var node = root

        while (node !is Nil)
            node = node.rightChild

        return node
    }

    fun treeSuccessor(node: AbstractNode): AbstractNode {
        var node = node
        if (node.rightChild !is Nil)
            return treeMinimum(node.rightChild)

        var y = node.parent

        while (y !is Nil && node == y.rightChild) {
            node = y
            y = y.parent
        }
        return y
    }

    fun printTree() {
        println(" \n----------------\n ")
        printBinaryTree(root, 0)
    }

    fun rotateRR(nodeA: AbstractNode) {
        if (nodeA is Nil || nodeA.rightChild is Nil) return

        val nodeB = nodeA.rightChild
        val parentA = nodeA.parent

        nodeA.rightChild = nodeB.leftChild
        if (nodeA.rightChild !is Nil) nodeA.rightChild.parent = nodeA
        nodeB.leftChild = nodeA
        nodeB.parent = parentA
        nodeA.parent = nodeB

        if (parentA is Nil) {
            root = nodeB

        }else{
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

    fun rotateLL(nodeA: AbstractNode) {
        if (nodeA is Nil || nodeA.leftChild is Nil) return

        val nodeB = nodeA.leftChild
        val parentA = nodeA.parent

        nodeA.leftChild = nodeB.rightChild
        if (nodeA.leftChild !is Nil) nodeA.leftChild.parent = nodeA
        nodeB.rightChild = nodeA
        nodeB.parent = parentA
        nodeA.parent = nodeB

        if (parentA is Nil) {
            root = nodeB
        }else{
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

    fun rotateRL(nodeA: AbstractNode) {
        val nodeB = nodeA.rightChild
        val nodeC = nodeB.leftChild
        val par = nodeA.parent

        nodeB.leftChild = nodeC.rightChild
        if (nodeB.leftChild !is Nil) nodeB.leftChild.parent = nodeB
        nodeA.rightChild = nodeC.leftChild
        if (nodeA.rightChild !is Nil) nodeA.rightChild.parent = nodeA

        nodeC.leftChild = nodeA
        nodeC.rightChild = nodeB
        nodeA.parent = nodeC
        nodeB.parent = nodeC
        nodeC.parent = par

        // ##TO-DO## check conditions
        if (par is Nil) root = nodeC
        else if (par.leftChild == nodeA) par.leftChild = nodeC
        else par.rightChild = nodeC

        if (nodeC.bf == -1) nodeA.bf = 1 else nodeA.bf = 0

        if (nodeC.bf == 1) nodeB.bf = -1 else nodeB.bf = 0

        nodeC.bf = 0

    }

    fun rotateLR(nodeA: AbstractNode) {
        val nodeB = nodeA.leftChild
        val nodeC = nodeB.rightChild
        val par = nodeA.parent

        nodeB.rightChild = nodeC.leftChild
        if (nodeB.rightChild !is Nil) nodeB.rightChild.parent = nodeB
        nodeA.leftChild = nodeC.rightChild
        if (nodeA.leftChild !is Nil) nodeA.leftChild.parent = nodeA

        nodeC.rightChild = nodeA
        nodeC.leftChild = nodeB
        nodeA.parent = nodeC
        nodeB.parent = nodeC
        nodeC.parent = par

        // ##TO-DO## check conditions
        if (par is Nil) root = nodeC
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

    fun inOrderNodes(): MutableList<Node> {
        val list = mutableListOf<Node>()
        inOrderNodesFrom(root, list)
        return list
    }

    fun preOrder(): MutableList<Int> {
        val ret = mutableListOf<Int>()
        preOrder(root, ret)
        return ret
    }

    private fun inOrderNodesFrom(node: AbstractNode, list: MutableList<Node>) {
        if (node !is Nil) {
            inOrderNodesFrom(node.leftChild, list)
            list.add(node as Node)
            inOrderNodesFrom(node.rightChild, list)
        }
    }

    private fun preOrder(node: AbstractNode, list: MutableList<Int>) {
        if (node !is Nil) {
            list.add(node.key)
            inOrder(node.leftChild, list)
            inOrder(node.rightChild, list)
        }
    }

    private fun printBinaryTree(root: AbstractNode, level: Int) {
        if (root is Nil)
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

        while (temp !is Nil) {
            temp = if (temp.leftChild !is Nil) {
                rotateLL(temp)
                temp.parent
            } else {
                temp.rightChild
            }
        }
    }

    private fun find(node: AbstractNode, key: Int): AbstractNode {
        var x = node

        while (x !is Nil && key != x.key) {
            x = if (key < x.key) {
                x.leftChild
            } else {
                x.rightChild
            }
        }

        return x

    }

    private fun inOrder(node: AbstractNode, list: MutableList<Int>) {
        if (node !is Nil) {
            inOrder(node.leftChild, list)
            list.add(node.key)
            inOrder(node.rightChild, list)
        }
    }
}

sealed class AbstractNode {
    abstract var key: Int
    abstract var bf: Int
    abstract var parent: AbstractNode
    abstract var leftChild: AbstractNode
    abstract var rightChild: AbstractNode
}

data class Node(
    override var key: Int,
    override var bf: Int,
    override var parent: AbstractNode,
    override var leftChild: AbstractNode,
    override var rightChild: AbstractNode
) : AbstractNode() {

    override fun toString(): String {
        return "V: ${this.key} H: $bf"
    }
}

object Nil : AbstractNode() {
    override var bf: Int
        get() = throw NoSuchElementException("Nil")
        set(value) {}
    override var parent: AbstractNode
        get() = throw NoSuchElementException("Nil")
        set(value) {}
    override var key: Int
        get() = throw NoSuchElementException("Nil")
        set(value) {}
    override var leftChild: AbstractNode
        get() = throw NoSuchElementException("Nil")
        set(value) {}
    override var rightChild: AbstractNode
        get() = throw NoSuchElementException("Nil")
        set(value) {}
}