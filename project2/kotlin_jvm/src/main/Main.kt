package main

import main.algorithms.MST
import main.printer.GraphPrinter

class Main {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val graf1 = GraphLoader.loadFile("./dane_testowe/dane_mst.txt")
            val kruskalMst = MST.kruskalUsingQueue(graf1)
            val kruskalMst2 = MST.primUsingAdjList(graf1)
            val gp1 = GraphPrinter(graf1)
            val gp2 = GraphPrinter(kruskalMst)
            val gp3 = GraphPrinter(kruskalMst2)

            gp1.print()
            gp2.print()
            gp3.print()
        }
    }
}
