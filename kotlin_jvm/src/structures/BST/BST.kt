package sample.helloworld.structures.BST

import kotlin.math.floor
import kotlin.math.log2
import kotlin.math.pow

class BST {

    var root: AbstractNode = Nil

    fun insert(key: Int) {

        var y: AbstractNode = Nil
        var x: AbstractNode = root
        val z = Node(key, Nil, Nil, Nil)

        while (x !is Nil) {
            y = x
            x = if (z.value < x.value) {
                x.leftChild
            } else {
                x.rightChild
            }
        }

        z.parent = y

        if (y is Nil) {
            root = z
        } else {
            if (z.value < y.value) {
                y.leftChild = z
            } else {
                y.rightChild = z
            }
        }

    }

    fun find(key: Int) : AbstractNode{
        return find(root, key)
    }

    fun extract(key: Int) : AbstractNode{
       val nodeDel = find(key)

        val y : AbstractNode

        y = if(nodeDel.leftChild is Nil || nodeDel.rightChild is Nil){
            nodeDel
        }else{
            treeSuccessor(nodeDel)
        }

        val x = if(y.leftChild !is Nil){
            y.leftChild
        }else{
            y.rightChild
        }

        if(x !is Nil) x.parent = y.parent

        if(y.parent is Nil){
            root = x
        }else if( y == y.parent.leftChild){
            y.parent.leftChild = x
        }else{
            y.parent.rightChild = x
        }

        if(y != nodeDel)
            nodeDel.value = y.value

        return y
    }

    fun insertAndFix(key: Int){
        insert(key)
        balanceDSW()
    }

    fun deleteAndFix(key: Int){
        extract(key)
        balanceDSW()
    }

    fun treeMinimum(node:AbstractNode) : AbstractNode{
        var node = node

        while (node !is Nil)
            node = node.leftChild

        return node
    }

    fun treeMaximum() : AbstractNode{
        var node = root

        while (node !is Nil)
            node = node.rightChild

        return node
    }

    fun treeSuccessor(node: AbstractNode) : AbstractNode{
        var node = node
        if(node.rightChild !is Nil)
            return treeMinimum(node.rightChild)

        var y = node.parent

        while (y !is Nil && node == y.rightChild){
            node = y
            y = y.parent
        }
        return y
    }

    fun balanceDSW(){
        backbone()

        val count = inOrder().size
     //   var s = count + 1 - log2(count + 1.0)
        var p = root

        var s = floor(log2(count+0.0)+1) - count

        for(i in 0 until s.toInt()){
            rotateLeft(p)
            p = p.parent.rightChild
        }

        s = count.toDouble()

        while(s > 1){
            s/=2
            p = root
            for(i in 0 until s.toInt()){
                rotateLeft(p)
                p = p.parent.rightChild
            }
        }
    }

    fun printTree(){
        println(" \n----------------\n ")
        printBinaryTree(root, 0)
    }

    fun rotateLeft(nodeA: AbstractNode){
        if (nodeA is Nil || nodeA.rightChild is Nil) return

        val nodeB = nodeA.rightChild
        val parentA = nodeA.parent

        nodeA.rightChild = nodeB.leftChild
        if(nodeA.rightChild !is Nil) nodeA.rightChild.parent = nodeA
        nodeB.leftChild = nodeA
        nodeB.parent = parentA
        nodeA.parent = nodeB

        if(parentA is Nil){
            root = nodeB
            return
        }

        if(parentA.leftChild == nodeA) parentA.leftChild = nodeB
        else parentA.rightChild = nodeB

    }

    fun rotateRight(nodeA: AbstractNode){
        if (nodeA is Nil || nodeA.leftChild is Nil) return

        //nodeA as AVLNodeAVL

        val nodeB = nodeA.leftChild
        val parentA = nodeA.parent

        nodeA.leftChild = nodeB.rightChild
        if(nodeA.leftChild !is Nil) nodeA.leftChild.parent = nodeA
        nodeB.rightChild = nodeA
        nodeB.parent = parentA
        nodeA.parent = nodeB

        if(parentA is Nil){
            root = nodeB
            return
        }

        if(parentA.rightChild == nodeA) parentA.rightChild = nodeB
        else parentA.leftChild = nodeB

    }

    fun inOrder(): List<Int> {
        val ret = mutableListOf<Int>()
        inOrder(root, ret)
        return ret
    }

    fun inOrderNodes() : MutableList<Node>{
        val list = mutableListOf<Node>()
        inOrderNodesFrom(root, list)
        return list
    }

    fun preOrder() : MutableList<Int> {
        val ret = mutableListOf<Int>()
        preOrder(root, ret)
        return ret
    }

    private fun inOrderNodesFrom(node: AbstractNode, list: MutableList<Node>){
        if (node !is Nil) {
            inOrderNodesFrom(node.leftChild, list)
            list.add(node as Node)
            inOrderNodesFrom(node.rightChild, list)
        }
    }

    private fun preOrder(node: AbstractNode, list: MutableList<Int>) {
        if (node !is Nil) {
            list.add(node.value)
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

            println("|---" + root.value)
        } else
            println(root.value)
        printBinaryTree(root.leftChild, level + 1)
    }

    private fun backbone(){
        var temp = root

        while (temp !is Nil){
            temp = if (temp.leftChild !is Nil){
                rotateRight(temp)
                temp.parent
            }else{
                temp.rightChild
            }
        }
    }

    private fun find(node: AbstractNode, key: Int) : AbstractNode{
        var x = node

        while( x !is Nil && key != x.value){
            x = if (key < x.value){
                x.leftChild
            }else{
                x.rightChild
            }
        }

        return x

    }

    private fun inOrder(node: AbstractNode, list: MutableList<Int>) {
        if (node !is Nil) {
            inOrder(node.leftChild, list)
            list.add(node.value)
            inOrder(node.rightChild, list)
        }
    }
}

sealed class AbstractNode {
    abstract var value: Int
    abstract var parent: AbstractNode
    abstract var leftChild: AbstractNode
    abstract var rightChild: AbstractNode
}

data class Node(
    override var value: Int,
    override var parent: AbstractNode,
    override var leftChild: AbstractNode,
    override var rightChild: AbstractNode
) : AbstractNode(){

    override fun toString(): String {
        return "V: ${this.value}"
    }
}

object Nil : AbstractNode() {
    override var parent: AbstractNode
        get() = throw NoSuchElementException("Nil")
        set(value) {}
    override var value: Int
        get() = throw NoSuchElementException("Nil")
        set(value) {}
    override var leftChild: AbstractNode
        get() = throw NoSuchElementException("Nil")
        set(value) {}
    override var rightChild: AbstractNode
        get() = throw NoSuchElementException("Nil")
        set(value) {}
}