package structures.list

import sample.helloworld.structures.list.ListAbstractElement
import sample.helloworld.structures.list.ListElement
import sample.helloworld.structures.list.Nil
import kotlin.random.Random
import kotlin.random.nextInt


class ListSDiZO() : IterableListSDiZO {
    var head: ListAbstractElement = Nil
    var tail: ListAbstractElement = head
    var size = 0

    constructor(num: Int) : this(){
        add(num)
    }

    fun add(value: Int) {
        if (tail == head){
            // Przypadek pustej listy
            if(tail is Nil) {
                    tail = ListElement(value, Nil, Nil)
                    head = tail
            }else{
            // Lista jednoelementowa
                tail = ListElement(value, head, Nil)
                head.successor = tail
            }
        }else{
            tail.successor = ListElement(value, tail, Nil)
            tail = tail.successor
        }
        size++
    }

    fun addAt(value: Int, position: Int): Boolean {
        var elem = ListElement(value, Nil, Nil)

        when {
            position > size -> return false

            position == size -> {
                add(value)
                return true
            }

            position == 0 -> {
                elem.successor = head
                head.predecessor = elem
                head = elem
            }

            else -> {
                val itr = frontIterator()
                var found : ListAbstractElement = Nil

                for(i in 0 .. position)
                    found = itr.next()

                found.predecessor.successor = elem

                elem.predecessor = found.predecessor
                elem.successor = found

                found.predecessor = elem

            }
        }

        size++
        return true
    }

    fun deleteAt(ind: Int): Boolean{
        if(size == 0 || ind >= size || ind < 0) return false

        else if(size == 1){
            head = Nil
            tail = head
            size--
            return true
        }else if(ind == 0){
            head = head.successor
            head.predecessor = Nil
        }else if(ind == size-1){
            tail = tail.predecessor
            tail.successor = Nil
        }else{

            var elem: ListAbstractElement = Nil
            val itr = frontIterator()

            for(i in 0..ind){
                elem = itr.next()
            }

            if(elem is Nil) return false

            elem.predecessor.successor = elem.successor
            elem.successor.predecessor = elem.predecessor
        }

        size--
        return true
    }

    fun deleteValue(value: Int): Boolean {

        val toDel = find(value)

        if(toDel is Nil) return false

        //Przypadek listy jednoelementowej
        when {
            head == tail -> {

                head = Nil
                tail = head

            }
            toDel == head -> {

                head = toDel.successor
                head.predecessor = Nil

            }
            toDel == tail -> {

                tail = toDel.predecessor
                tail.successor = Nil

            }
            else -> {

                val pred = toDel.predecessor
                val succ = toDel.successor

                pred.successor = succ
                succ.predecessor = pred
            }
        }
        size--
        return true

    }

    fun contains(value: Int): Boolean {
        val itr = frontIterator()
        while (itr.hasNext()) {
            if (itr.next().value == value) return true
        }

        return false
    }

    fun find(value: Int): ListAbstractElement {
        val itr = frontIterator()
        while (itr.hasNext()) {
            val next = itr.next()
            if (next.value == value) return next
        }

        return Nil
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

        var next = if(current !is Nil) current.successor else Nil
        var ret = current
        var prev = if(current !is Nil) current.predecessor else Nil

        override fun next(): ListAbstractElement {

            if(current is Nil) return current

            ret = current

            prev = current
            current = next
            next = if(next !is Nil) next.successor else Nil

            return ret
        }

        override fun hasNext(): Boolean {
            return current !is Nil
        }

        override fun previous(): ListAbstractElement {
            if(current is Nil) return current

            ret = current

            next = current
            current = prev
            prev = if(prev !is Nil) prev.predecessor else Nil

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

    fun toReverseString(): String {
        val builder = StringBuilder("")
        val itr = backIterator()

       while(itr.hasPrevious())
           builder.append(" ${itr.previous().value}")

        return builder.toString()
    }

    fun print(){
        println(toString())
        println(toReverseString())
        println()
    }

    companion object {
        fun generateRandom(count: Int, range: IntRange): ListSDiZO {
            val list = ListSDiZO()

            for (i in 1 until count)
                list.add(Random.nextInt(range))
            return list

        }
    }
}
