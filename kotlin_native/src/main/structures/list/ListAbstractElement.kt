package sample.helloworld.structures.list


sealed class ListAbstractElement{

    abstract var value: Int

    abstract var predecessor: ListAbstractElement

    abstract var successor: ListAbstractElement

}

data class ListElement(
    override var value: Int,
    override var predecessor: ListAbstractElement,
    override var successor: ListAbstractElement
): ListAbstractElement()

object Nil : ListAbstractElement(){
    override var value: Int
        get() {
            throw NoSuchElementException(" no value. End of list.")
        }
        set(x){
            throw NoSuchElementException(" Cannot do this on NIL")
        }
    override var predecessor: ListAbstractElement
        get() {
            throw NoSuchElementException(" no predecessor. End of list.")
        }
        set(x){
            throw NoSuchElementException(" Cannot do this on NIL")
        }
    override var successor: ListAbstractElement
        get() {
            throw NoSuchElementException(" no successor. End of list.")
        }
        set(x){
            throw NoSuchElementException(" Cannot do this on NIL")
        }

}
