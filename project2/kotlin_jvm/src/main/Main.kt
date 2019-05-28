package main

import main.algorithms.DijkstrasAlg

class Main {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
//            val graf1 = GraphLoader.loadFile("./dane_testowe/dane_mst.txt")
//            val kruskalMst = MST.kruskalUsingQueue(graf1)
//            val kruskalMst2 = MST.primUsingAdjList(graf1)
//            val gp1 = GraphPrinter(graf1)
//            val gp2 = GraphPrinter(kruskalMst)
//            val gp3 = GraphPrinter(kruskalMst2)
//
//            println(graf1.weight())
//            println(kruskalMst.weight())
//            println(kruskalMst2.weight())
//            gp1.print()
//            gp2.print()
//            gp3.print()

            val graf1 = GraphLoader.loadFile("./dane_testowe/usfca.txt")
            // poprawne -1,0,0,1,2,3,1,5
            println(graf1.adjList)
           // val gp4 = GraphPrinter(graf1).print()
            val dij = DijkstrasAlg(graf1, 0)
            dij.shortestPathAdjList()
            println(Utils.arrayToString(dij.predecessors))
            println(Utils.arrayToStringLines(dij.getPaths()))
            dij.shortestPathAdjMatrix()
            println(Utils.arrayToString(dij.predecessors))
            println(Utils.arrayToStringLines(dij.getPaths()))

            val graf22= GraphLoader.loadFile("./dane_testowe/dane_droga_BF.txt")
            val dij22 = DijkstrasAlg(graf22, graf22.startVert)
            dij22.shortestPathAdjList()
            println(Utils.arrayToString(dij22.predecessors))
        }
    }
}
