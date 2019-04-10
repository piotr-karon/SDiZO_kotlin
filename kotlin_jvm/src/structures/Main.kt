package sample.helloworld.structures

import java.io.File
import kotlin.random.Random

fun main(){

   // print()

    val f = File("hello.txt")
    f.delete()
    f.createNewFile()

    for(i in 0..50){
        f.appendText("${Random.nextInt(0,50)}\n")
    }
//
//    val bst = FileLoader.bstOf(f)
//
// //   bst.printTree()
//
//    val avl = FileLoader.avlOf(f)
//    val arr = FileLoader.arrayOf(f)
//    val list = FileLoader.listOf(f)
    val heap = FileLoader.heapOf(f,100)
//
//    avl.printTree()
//    println()
//    println(arr.toPrettyString())
//    println(list.toString())
    println(heap.toString())

heap.printTree()
//    val bst2 = BST()
//
//    for(i in 0..100){
//        bst2.insert(Random.nextInt(1,1000))
//        bst2.balanceDSW()
//    }
//
//    bst2.printTree()
}