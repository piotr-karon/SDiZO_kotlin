package sample.helloworld

sealed class MyList<out A> {

    abstract val head: A

    abstract val tail: MyList<A>

    fun count(): Int {
        return 0;
    }

}


data class Cons<out T>(override val head: T, override val tail: MyList<T>) : MyList<T>()


object Nil : MyList<Nothing>() {

    override val head: Nothing
        get() {
            throw NoSuchElementException("head of an empty list")
        }

    override val tail: MyList<Nothing>
        get() {
            throw NoSuchElementException("tail of an empty list")
        }

}