package main

import java.io.File
import java.util.*

object GraphLoader {

    fun loadFile(path: String): Graph{
        val file = File(path)
        return loadFile(file)
    }

    fun loadFile(file: File): Graph{
        val scan = Scanner(file)

        val firstLine = scan.nextLine().trim()
        val split = firstLine.split(" ")

        val edgeCount = split[0].toInt()
        val vertCount = split[1].toInt()

        val edgesList = mutableListOf<Edge>()

        val startVert = if(split.size > 2) split[2].toInt() else 0
        val endVert = if(split.size > 3) split[3].toInt() else vertCount-1

        while (scan.hasNextLine()){
            val line = scan.nextLine().trim()
            if(line.isBlank() || line.isEmpty()) continue

            val cut = line.split( " ")
            edgesList.add(Edge(cut[0].toInt(), cut[1].toInt(), cut[2].toInt()))
        }

        val graph = Graph(edgesList, vertCount, edgeCount)
        graph.startVert = startVert
        graph.endVert = endVert

        return graph
    }

    fun saveFile(graph: Graph, name: String){
        val file = File(name)
        file.createNewFile()

        val fileWriter = file.printWriter()

        fileWriter.println("${graph.E} ${graph.V}")

        for(e in graph.edgesArray)
            fileWriter.println("${e.src} ${e.dest} ${e.weight}")

        fileWriter.close()

    }

}