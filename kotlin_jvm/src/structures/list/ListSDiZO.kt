package structures.list

import sample.helloworld.structures.list.ListAbstractElement
import sample.helloworld.structures.list.ListElement
import sample.helloworld.structures.list.Nil

/*
* Konstruktor wymaga podania pierwszej wartości liczby.
* To obostrzenie, choć niezbyt wygodne, pozwala na
* zachowanie "null-safety" w późniejszych częściach programu.
*/

class ListSDiZO(value: Int) : IterableListSDiZO {
    var head: ListAbstractElement = Nil

    var tail: ListAbstractElement = Nil

    init {
        add(value)
    }

    fun add(value: Int) {
        when (tail is Nil) {
            true -> {
                head = ListElement(value, Nil, Nil)
                tail = head
            }

            false -> {
                tail.successor = ListElement(value, tail, Nil)
                this.tail = tail.successor
            }
        }

    }

    fun addFirst(value: Int) {
        when (head is Nil) {
            true -> add(value)

            false -> {
                head.predecessor = ListElement(value, Nil, head)
                this.head = head.predecessor
            }
        }
    }

    fun addLast(value: Int) {
        add(value)
    }

    fun addAt(value: Int, position: Int): Boolean {

        if (position == 0) addFirst(value)

        val itr = frontIterator()
        var i = 0
        var element = head

        while (itr.hasNext() && i < position) {
            element = itr.next()
            i--
        }
        if (element is Nil) return false

        val newElem = ListElement(value, element.predecessor, element)
        element.predecessor.successor = newElem
        element.predecessor = newElem

        return true
    }

    fun deleteFirst() {
        if (head is Nil) return

        // Przypadek listy jednoelementowej
        if (head == tail) {
            val nil = Nil
            head = nil
            tail = nil
            return
        } else {
            head.successor.predecessor = Nil
            this.head = head.successor
        }
    }

    fun deleteLast() {
        if (tail is Nil) return

        // Przypadek listy jednoelementowej
        if (head == tail) {
            deleteFirst()
        } else {
            tail.predecessor.successor = Nil
            this.tail = tail.predecessor
        }
    }

    fun deleteAt(ind: Int){
        if(head is Nil) return

        if(ind == 0) {
            deleteFirst()
            return
        }

        val itr = frontIterator()
        var i = 0
        while (i < ind && itr.hasNext()){
            itr.next()
            i++
        }

        if(i>0){
            val delElement = itr.next()
            val predDel = delElement.predecessor
            val succDel = delElement.successor

            predDel.successor = succDel
            if(succDel !is Nil) {
                succDel.predecessor = predDel
            }
        }


    }

    fun deleteValue(value: Int): Boolean {

        val it = frontIterator()
        var elem = head

        while (it.hasNext() && elem.value != value) {
            elem = it.next()
        }

        if (elem is Nil) return false

        elem.predecessor.successor = elem.successor
        elem.successor.predecessor = elem.predecessor

        return true
    }

    fun contains(value: Int): Boolean {
        val itr = frontIterator()
        while (itr.hasNext()) {
            if (itr.next().value == value) return true
        }

        return false
    }


    override fun frontIterator(): IteratorListSDiZO {
        return IteratorSDiZO(head)
    }

    override fun backIterator(): IteratorListSDiZO {
        return IteratorSDiZO(tail)
    }

    private class IteratorSDiZO(
        var current: ListAbstractElement
    ) : IteratorListSDiZO {

        override fun next(): ListAbstractElement {
            val ret = current
            when {
                hasNext() -> {
                    current = current.successor
                }
                else -> current = Nil
            }

            return ret
        }

        override fun hasNext(): Boolean {
            return current !is Nil
        }

        override fun previous(): ListAbstractElement {
            val ret = current
            when {
                hasPrevious() -> {
                    current = current.predecessor
                }
                else -> current = Nil
            }

            return ret
        }

        override fun hasPrevious(): Boolean {
            return current !is Nil
        }

    }

    override fun toString(): String {
        val builder = StringBuilder("")
        val itr = frontIterator()

        while (itr.hasNext()) {
            builder.append(" ${itr.next().value}")
        }

        return builder.toString()
    }

}
