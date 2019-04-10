package sample.helloworld.structures

import sample.helloworld.structures.BST.BST
import sample.helloworld.structures.heap.HeapSDiZO
import structures.list.ListSDiZO
import java.io.File
import java.util.*

class FileLoader{

    companion object {

        fun bstOf(file : File) : BST{
            val bst = BST()
            val scan = Scanner(file)

            val size = scan.nextLine()

            while (scan.hasNextLine()){
              //  bst.insert(scan.nextLine().toInt())
                bst.insertAndFix(scan.nextLine().toInt())
            }

            bst.balanceDSW()
            return bst
        }

        fun avlOf(file : File) : AVLTree{
            val avl = AVLTree()
            val scan = Scanner(file)

            val size = scan.nextLine()

            while (scan.hasNextLine()){
                avl.insert(scan.nextInt())
            }

            return avl
        }

        fun arrayOf(file : File) : ArraySDiZO{
            val arr = ArraySDiZO()

            val scan = Scanner(file)

            val size = scan.nextLine().toString()

            while (scan.hasNextLine()){
                arr.add(scan.nextInt())
            }

            return arr
        }

        fun listOf(file : File) : ListSDiZO {


            val scan = Scanner(file)

            val size = scan.nextLine().toString()
            val list = ListSDiZO(scan.nextInt())
            while (scan.hasNextLine()){
                list.add(scan.nextInt())
            }

            return list
        }

        fun heapOf(file : File) : HeapSDiZO {
            val scan = Scanner(file)
            val size = scan.nextInt()

            val heap = HeapSDiZO(0,size)

            while (scan.hasNext()){
                heap.insert(scan.nextInt())
            }

            return heap
        }
    }

}