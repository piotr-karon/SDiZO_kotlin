package main

object Utils {

    fun getVerticesMaxNumber(edges: Collection<Edge>): Int {
        var max = 0
        for (edge in edges)
            max = Math.max(Math.max(edge.src, edge.dest), max)

        return max
    }

    fun getVerticesCount(edges: Collection<Edge>): Int {
        val vertSet = HashSet<Int>()
        for (edge in edges) {
            vertSet.add(edge.src)
            vertSet.add(edge.dest)
        }
        return vertSet.count()
    }

    fun arrayToString(array: Array<Int>): String {
        var string = "["
        for (any in array) {
            string += "$any,"
        }
        string = string.dropLast(1) + "]"

        return string
    }

}