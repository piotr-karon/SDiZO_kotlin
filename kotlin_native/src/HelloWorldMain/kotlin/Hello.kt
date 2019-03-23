package sample.helloworld

fun hello(): String = "Hello, Kotlin/Native!"

fun main() {
    println(hello())

    //val list:MyList<Int> = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))

//    println(list.head)
//    println(list.tail.head)
//    println(list.count())

    val table = MyTable()

    table.addFirst(2)
    table.addFirst(3)


}
