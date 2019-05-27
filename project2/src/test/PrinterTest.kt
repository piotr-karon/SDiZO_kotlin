package test

import main.GraphLoader
import main.printer.GraphPrinter
import org.junit.Test

class PrinterTest {

    @Test
    fun printerTest(){
       // val graph = Graph.randomGraph(15,0.99)
        val graph = GraphLoader.loadFile("./dane_testowe/dane_droga.txt")
        val gp = GraphPrinter(graph)
        gp.print()
        while(true){

        }
    }
}