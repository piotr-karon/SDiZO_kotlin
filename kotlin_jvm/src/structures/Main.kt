package sample.helloworld.structures

import sample.helloworld.structures.BST.BST
import java.io.File
import kotlin.random.Random

fun main(){

//    val f = File("hello.txt")
//
//    f.createNewFile()
//
//    for(i in 0..100){
//        f.appendText("${Random.nextInt(0,1000)}\n")
//    }
//
//    val bst = FileLoader.bstOf(f)
//
//    bst.printTree()


    val bst2 = BST()

    bst2.insert(Random.nextInt(1,1000))
    bst2.insert(Random.nextInt(1,1000))
    bst2.insert(Random.nextInt(1,1000))
    bst2.insert(Random.nextInt(1,1000))


    for(i in 0..100){
        bst2.insert(Random.nextInt(1,1000))
        bst2.balanceDSW()
    }

    bst2.printTree()
}