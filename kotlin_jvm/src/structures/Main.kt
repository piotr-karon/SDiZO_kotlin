package sample.helloworld.structures

import structures.list.ListSDiZO
import java.io.File


fun main() {

    var opt: String

    do{
        print("=======Menu Główne=========\n" +
                "1. Tablica\n" +
                "2. Lista\n" +
                "3. Kopiec\n" +
                "4. BST\n" +
                "5. Drzewo AVL\n" +
                "0. Wyjście\n" +
                "Podaj opcję: ")

        opt = readOption()

        when(opt){
            "1" -> handleArray()
            "2" -> handleList()
//            "3" -> handleHeap()
//            "4" -> handleBST()
//            "5" -> handleAVL()
        }

    }while(opt != "0")

}

fun handleList() {
    var opt: String = ""

    //var structure = ListSDiZO()

    do{
//        subMenuBase("Array", arrListAdditionalOpts())
//        opt = readOption()
//
//        when(opt){
//
//            "1" ->{
//                val file = askForFile()
//                if( file != null){
//                    structure = FileLoader.listOf(file)
//                    println(structure.toString())
//                }
//            }
//            "2" ->{
//                p("Podaj wartość do usnięcia")
//                val ind = readOption().toInt()
//                structure.delete(ind)
//            }
//            "3" ->{
//                println("Podaj pozycję:")
//                val ind = readOption().toInt()
//                println("Podaj wartośc:")
//                val key = readOption().toInt()
//                structure.addAt(ind,key)
//            }
//            "4" ->{
//                p("Podaj wartość")
//                val key = readOption().toInt()
//                val found = if(structure.contains(key)) " " else " nie "
//                p("Wartość w zbiorze${found}istnieje")
//            }
//            "5" ->{
//                p("Podaj rozmiar:")
//                val size = readOption().toInt()
//                p("Podaj dolną granicę wartości:")
//                val down = readOption().toInt()
//                p("Podaj górną granicę wartości:")
//                val up = readOption().toInt()
//                structure = ArraySDiZO.generateRandom(size, IntRange(down, up))
//            }
//            "6" ->{
//                p(structure.toPrettyString())
//            }
//            "7" ->{
//                //  TestUnit.arrayTest()
//            }
//            "8" -> {
//                println("Podaj pozycję:")
//                val ind = readOption().toInt()
//                structure.deleteAt(ind)
//            }
//        }
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
            "7" ->{
              //  TestUnit.arrayTest()
            }
            "8" -> {
                println("Podaj pozycję:")
                val ind = readOption().toInt()
                arr.deleteAt(ind)
            }
        }
    }while(opt != "0")
}

private fun askForFile() : File? {

    do{
        println("Podaj ścieżkę pliku:")
        var path = readLine()?.trim()

        var file = File(path)

        if (file.exists()) return file
    }while(path != "0")

    return null
}

private fun arrListAdditionalOpts() ="" +
        "8. Usuń z pozycji\n"

private fun subMenuBase(name: String, additionalOptions: String) {
    print(      "${name.toUpperCase()}\n" +
                "1. Wczytaj z pliku \n" +
                "2. Usuń \n" +
                "3. Dodaj \n" +
                "4. Znajdź \n" +
                "5. Generuj losowo \n" +
                "6. Wyświetl \n" +
                "7. Testy \n" +
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

//private fun handleArray(){
//    var opt: String
//
//    var arr: ArraySDiZO
//
//    do{
//        subMenuBase("Array", arrListAdditionalOpts())
//        opt = readOption()
//
//        when(opt){
//
//            "1" ->{
//                val file = askForFile()
//                if( file != null){
//                    arr = FileLoader.arrayOf(file)
//                    println(arr.toPrettyString())
//                }
//            }
//
//            "2" ->{
//
//            }
//            "3" ->{
//
//            }
//            "4" ->{
//
//            }
//            "5" ->{
//
//            }
//            "6" ->{
//
//            }
//            "7" ->{
//
//            }
//
//
//        }
//
//    }while(opt != "0")
//}