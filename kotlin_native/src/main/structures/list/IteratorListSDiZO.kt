package structures.list

import sample.helloworld.structures.list.ListAbstractElement

interface IteratorListSDiZO {

    fun next() : ListAbstractElement
    fun hasNext() : Boolean
    fun previous() : ListAbstractElement
    fun hasPrevious() : Boolean

}