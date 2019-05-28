package main.structures

class Subset(
    var parent: Int = 0,
    var rank: Int = 0){

    companion object{
        fun find(subsets: Array<Subset>, i: Int): Int {
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent)

            return subsets[i].parent
        }

        fun union(subsets: Array<Subset>, x: Int, y: Int) {
            val xroot = find(subsets, x)
            val yroot = find(subsets, y)
            when {
                subsets[xroot].rank < subsets[yroot].rank -> subsets[xroot].parent = yroot
                subsets[xroot].rank > subsets[yroot].rank -> subsets[yroot].parent = xroot
                else -> {
                    subsets[yroot].parent = xroot
                    subsets[xroot].rank++
                }
            }
        }
    }

}