package main

class Edge (
    var src: Int = 0,
    var dest: Int = 0,
    var weight: Int = Int.MAX_VALUE
): Comparable<Edge> {

    override fun compareTo(other: Edge): Int {
        return this.weight - other.weight
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Edge

        if (src != other.src) return false
        if (dest != other.dest) return false
        if (weight != other.weight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = src
        result = 31 * result + dest
        result = 31 * result + weight
        return result
    }

    override fun toString(): String {
        return "($src:$dest:$weight)"
    }
}
