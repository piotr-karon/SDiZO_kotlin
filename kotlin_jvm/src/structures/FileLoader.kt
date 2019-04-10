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
                avl.insert(scan.nextLine().toInt())
            }

            return avl
        }

        fun arrayOf(file : File) : ArraySDiZO{
            val arr = ArraySDiZO()

            val scan = Scanner(file)

            val size = scan.nextLine().toString()

            while (scan.hasNextLine()){
                arr.add(scan.nextLine().toInt())
            }

            return arr
        }

        fun listOf(file : File) : ListSDiZO {


            val scan = Scanner(file)

            val size = scan.nextLine().toString()
            val list = ListSDiZO(scan.nextLine().toInt())
            while (scan.hasNextLine()){
                list.add(scan.nextLine().toInt())
            }

            return list
        }

        fun heapOf(file : File, additional: Int) : HeapSDiZO {
            val scan = Scanner(file)
            val size = scan.nextLine().toInt()

            val heap = HeapSDiZO(0,size + additional)

            while (scan.hasNext()){
                heap.insert(scan.nextLine().toInt())
            }

            return heap
        }

        // Rozmiarem jest rozmiar tablicy. Pierwsza liczba z pliku pomijana.
        fun kotlinList(file : File) : MutableList<Int>{
            val scan = Scanner(file)
            val size = scan.nextLine().toInt()

            val intArr : MutableList<Int> = mutableListOf()

            while(scan.hasNextLine()){
                intArr.add(scan.nextLine().toInt())
            }

            return intArr
        }
    }

}