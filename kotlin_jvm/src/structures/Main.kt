package structures

import sample.helloworld.structures.FileLoader
import structures.bst.BST
import structures.heap.HeapSDiZO
import structures.avl.AVLTree
import structures.bst.Nil
import structures.list.ListSDiZO

import java.io.File


fun main() {

    var opt: String

    do{
        printMenu()
        opt = readOption()
        when(opt){
            "1" -> handleArray()
            "2" -> handleList()
            "3" -> handleHeap()
            "4" -> handleBST()
            "5" -> handleAVL()
            "6" -> doTests()
            "666" -> startAllTests()
        }

    }while(opt != "0")

}

private fun initTests(): TestUnit {
    p("Podaj rozmiar:")
    val size = readOption().toInt()
    p("Podaj dolną granicę wartości:")
    val down = readOption().toInt()
    p("Podaj górną granicę wartości:")
    val up = readOption().toInt()

    return TestUnit(size, IntRange(down, up))
}

private fun startAllTests() {
    do{
        p("+++TESTS ALL+++\n End: input 0")

        val testUnit = initTests()
        val count = askCount()

        testUnit.arrayTest(count)
        testUnit.listTest(count)
        testUnit.heapTest(count)
        testUnit.bstTest(count)
        testUnit.avlTest(count)

    }while (count != 0)
}

private fun doTests(){
    var opt: String
    var testU = initTests()
    do{
        p("+++TEST+++")
        p(testsMenu())
        p("Podaj opcję:")
        opt = readOption()

        when(opt){
            "1" -> testU.arrayTest(askCount())
            "2" -> testU.listTest(askCount())
            "3" -> testU.heapTest(askCount())
            "4" -> testU.bstTest(askCount())
            "5" -> testU.avlTest(askCount())
            "6" -> {
                testU = initTests()
                testU.randomize()
            }
        }
    }while (opt != "0")
}

private fun askCount() : Int{
    p("Ile razy wykonac?")
    return readOption().toInt()
}

private fun handleList() {
    var opt: String

    var structure = ListSDiZO.generateRandom(5,IntRange(0,10))

    do{
        subMenuBase("List", arrListAdditionalOpts())
        opt = readOption()

        try{
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
                    p(structure.toReverseString())
                }
                "7" -> {
                    println("Podaj pozycję:")
                    val ind = readOption().toInt()
                    structure.deleteAt(ind)
                }
            }
        }catch (e: Exception){
            println("Zły argument")
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
                    structure = FileLoader.heapOf(file, size)
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

    var structure = BST()

    do{
        subMenuBase("bst", "" +
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
                val found = if(structure.find(key) != Nil) " " else " nie "
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

    var structure = AVLTree()

    do{
        subMenuBase("AVL", "")
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

private fun printMenu(){
    print("=======Menu Główne=========\n" +
            "1. Tablica\n" +
            "2. Lista\n" +
            "3. Kopiec\n" +
            "4. bst\n" +
            "5. Drzewo AVL\n" +
            "6. Testy\n" +
            "0. Wyjście\n" +
            "Podaj opcję: ")
}

private fun testsMenu() =
        "1. Tablica\n" +
        "2. Lista\n" +
        "3. Kopiec\n" +
        "4. bst\n" +
        "5. Drzewo AVL\n" +
        "6. Zmien zakresy\n"

private fun arrListAdditionalOpts() ="" +
        "7. Usuń z pozycji\n"
