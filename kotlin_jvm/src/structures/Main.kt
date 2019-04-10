package sample.helloworld.structures

import org.junit.Test
import sample.helloworld.structures.BST.BST
import sample.helloworld.structures.heap.HeapSDiZO
import structures.avl.AVLTree
import structures.list.ListSDiZO
import java.io.File


fun main() {

    var opt: String

    do{
        printMenu("=======Menu Główne=========")

        opt = readOption()

        when(opt){
            "1" -> handleArray()
            "2" -> handleList()
            "3" -> handleHeap()
            "4" -> handleBST()
            "5" -> handleAVL()
            "6" -> doTests()
        }

    }while(opt != "0")

}

private fun doTests(){
    
    p("Podaj rozmiar:")
    val size = readOption().toInt()
    p("Podaj dolną granicę wartości:")
    val down = readOption().toInt()
    p("Podaj górną granicę wartości:")
    val up = readOption().toInt()

    var tu = TestUnit(size, IntRange(up, down))

}

private fun handleList() {
    var opt: String

    var structure = ListSDiZO(0)

    do{
        subMenuBase("List", arrListAdditionalOpts())
        opt = readOption()

        when(opt){

            "1" ->{
                val file = askForFile()
                if( file != null){
                    structure = FileLoader.listOf(file)
                    println(structure.toString())
                }
            }
            "2" ->{
                p("Podaj wartość do usnięcia")
                val ind = readOption().toInt()
                structure.deleteValue(ind)
            }
            "3" ->{
                println("Podaj pozycję:")
                val ind = readOption().toInt()
                println("Podaj wartośc:")
                val key = readOption().toInt()
                structure.addAt(ind,key)
            }
            "4" ->{
                p("Podaj wartość")
                val key = readOption().toInt()
                val found = if(structure.contains(key)) " " else " nie "
                p("Wartość w zbiorze${found}istnieje")
            }
            "5" ->{
                p("Podaj rozmiar:")
                val size = readOption().toInt()
                p("Podaj dolną granicę wartości:")
                val down = readOption().toInt()
                p("Podaj górną granicę wartości:")
                val up = readOption().toInt()
                structure = ListSDiZO.generateRandom(size, IntRange(down, up))
            }
            "6" ->{
                p(structure.toString())
            }
            "7" -> {
                println("Podaj pozycję:")
                val ind = readOption().toInt()
                structure.deleteAt(ind)
            }
        }
    }while(opt != "0")
}

private fun handleArray(){
    var opt: String

    var arr = ArraySDiZO()

    do{
        subMenuBase("Array", arrListAdditionalOpts())
        opt = readOption()

        when(opt){

            "1" ->{
                val file = askForFile()
                if( file != null){
                    arr = FileLoader.arrayOf(file)
                    println(arr.toPrettyString())
                }
            }
            "2" ->{
                p("Podaj wartość do usnięcia")
                val ind = readOption().toInt()
                arr.delete(ind)
            }
            "3" ->{
                println("Podaj pozycję:")
                val ind = readOption().toInt()
                println("Podaj wartośc:")
                val key = readOption().toInt()
                arr.addAt(ind,key)
            }
            "4" ->{
                p("Podaj wartość")
                val key = readOption().toInt()
                val found = if(arr.contains(key)) " " else " nie "
                p("Wartość w zbiorze${found}istnieje")
            }
            "5" ->{
                p("Podaj rozmiar:")
                val size = readOption().toInt()
                p("Podaj dolną granicę wartości:")
                val down = readOption().toInt()
                p("Podaj górną granicę wartości:")
                val up = readOption().toInt()
                arr = ArraySDiZO.generateRandom(size, IntRange(down, up))
            }
            "6" ->{
                p(arr.toPrettyString())
            }
            "7" -> {
                println("Podaj pozycję:")
                val ind = readOption().toInt()
                arr.deleteAt(ind)
            }
        }
    }while(opt != "0")
}

private fun handleHeap(){
    var opt: String

    var structure = HeapSDiZO(0,100, IntRange(1,500))

    do{
        subMenuBase("Heap", "")
        opt = readOption()

        when(opt){

            "1" ->{
                val file = askForFile()
                p("Podaj nadmiarową liczbę pól:")
                val size = readOption().toInt()
                if( file != null){
                    structure = FileLoader.heapOf(file,size)
                    structure.printTree()
                }
            }
            "2" ->{
                p("Możliwe usunięcie tylko elem max: ${structure.extractMax()}")
            }
            "3" ->{
                println("Podaj klucz:")
                val key = readOption().toInt()
                structure.insert(key)
            }
            "4" ->{
                p("Podaj wartość")
                val key = readOption().toInt()
                val found = if(structure.contains(key)) " " else " nie "
                p("Wartość w zbiorze${found}istnieje")
            }
            "5" ->{
                p("Podaj rozmiar:")
                val size = readOption().toInt()
                p("Podaj dolną granicę wartości:")
                val down = readOption().toInt()
                p("Podaj górną granicę wartości:")
                val up = readOption().toInt()
                structure = HeapSDiZO.generateRandom(size, IntRange(down, up))
            }
            "6" ->{
                structure.printTree()
            }
        }

    }while(opt != "0")
}

private fun handleBST(){
    var opt: String

    var structure = BST.generateRandom(10, IntRange(1,100))

    do{
        subMenuBase("BST", "" +
                "8. Dodaj i równoważ DSW \n" +
                "9. Usuń i równoważ DSW\n" +
                "10. Równoważ DSW\n")
        opt = readOption()

        when(opt){

            "1" ->{
                val file = askForFile()
                if( file != null){
                    structure = FileLoader.bstOf(file)
                    structure.printTree()
                }
            }
            "2" ->{
                p("Podaj wartość do usunięcia")
                val ind = readOption().toInt()
                structure.extract(ind)
            }
            "3" ->{
                println("Podaj klucz:")
                val key = readOption().toInt()
                structure.insert(key)
            }
            "4" ->{
                p("Podaj wartość")
                val key = readOption().toInt()
                val found = if(structure.find(key) != null) " " else " nie "
                p("Wartość w zbiorze${found}istnieje")
            }
            "5" ->{
                p("Podaj rozmiar:")
                val size = readOption().toInt()
                p("Podaj dolną granicę wartości:")
                val down = readOption().toInt()
                p("Podaj górną granicę wartości:")
                val up = readOption().toInt()
                structure = BST.generateRandom(size, IntRange(down, up))
            }
            "6" ->{
                structure.printTree()
            }
            "7" ->{
                println("Podaj klucz:")
                val key = readOption().toInt()
                structure.insert(key)
                structure.balanceDSW()
            }
            "8" ->{
                p("Podaj wartość do usunięcia")
                val ind = readOption().toInt()
                structure.extract(ind)
                structure.balanceDSW()
            }
            "9" ->{
                structure.balanceDSW()
            }
        }

    }while(opt != "0")
}

private fun handleAVL(){
    var opt: String

    var structure = AVLTree.generateRandom(10,IntRange(1,100))

    do{
        subMenuBase("BST", "")
        opt = readOption()

        when(opt){

            "1" ->{
                val file = askForFile()
                if( file != null){
                    structure = FileLoader.avlOf(file)
                    structure.printTree()
                }
            }
            "2" ->{
                p("Podaj wartość do usunięcia")
                val ind = readOption().toInt()
                structure.extract(ind)
            }
            "3" ->{
                println("Podaj klucz:")
                val key = readOption().toInt()
                structure.insert(key)
            }
            "4" ->{
                p("Podaj wartość")
                val key = readOption().toInt()
                val found = if(structure.find(key) != null) " " else " nie "
                p("Wartość w zbiorze${found}istnieje")
            }
            "5" ->{
                p("Podaj rozmiar:")
                val size = readOption().toInt()
                p("Podaj dolną granicę wartości:")
                val down = readOption().toInt()
                p("Podaj górną granicę wartości:")
                val up = readOption().toInt()
                structure = AVLTree.generateRandom(size, IntRange(down, up))
            }
            "6" ->{
                structure.printTree()
            }
        }

    }while(opt != "0")
}

private fun subMenuBase(name: String, additionalOptions: String) {
    print(      "${name.toUpperCase()}\n" +
                "1. Wczytaj z pliku \n" +
                "2. Usuń \n" +
                "3. Dodaj \n" +
                "4. Znajdź \n" +
                "5. Generuj losowo \n" +
                "6. Wyświetl \n" +
                additionalOptions +
                "0. Powrót do menu \n" +
                "Podaj opcję: "
    )
}

private fun arrListAdditionalOpts() ="" +
        "7. Usuń z pozycji\n"

private fun readOption() : String{
    return readLine().toString().trim()
}

private fun p(any: Any){
    println(any)
}

private fun askForFile() : File? {

    do{
        println("Podaj ścieżkę pliku:")
        val path = readLine()?.trim()

        val file = File(path)

        if (file.exists()) return file
    }while(path != "0")

    return null
}

private fun printMenu(name: String){
    print("$name\n" +
            "1. Tablica\n" +
            "2. Lista\n" +
            "3. Kopiec\n" +
            "4. BST\n" +
            "5. Drzewo AVL\n" +
            "6. Testy\n" +
            "0. Wyjście\n" +
            "Podaj opcję: ")
}
