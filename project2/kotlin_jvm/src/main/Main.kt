import main.Graph
import main.GraphLoader
import main.Utils
import main.algorithms.BellmanFordAlg
import main.algorithms.DijkstrasAlg
import main.algorithms.MST
import org.junit.Test
import java.io.File
class Main {

    private var graph: Graph = GraphLoader.loadFile("./dane_testowe/dane_droga_BF.txt")
    private var isGenerated = false

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.isNotEmpty())
                TestUnit.runTests(args[0].toInt())
            val main = Main()
            main.menu()
        }
    }
    // TODO BELLMAN dane_BF!!

    fun menu() {

        var opt: String

        do {
            printMenu()
            opt = readOption()
            when (opt) {
                "1" -> loadGraph()
                "2" -> generateGraph()
                "3" -> printGraph()
                "4" -> prim()
                "5" -> kruskal()
                "6" -> dijkstra()
                "7" -> bellman()
            }

        } while (opt != "0")

    }

    private fun loadGraph() {
        val file = askForFile()
        if (file == null || !file.exists()) p("Plik nie istnieje")

        graph = GraphLoader.loadFile(file!!)
        isGenerated = false
        printGraph()
    }

    private fun generateGraph() {
        val vertCount = askCount("Podaj liczbę wierzchołków: ")
        val density = askCount("Podaj gęstość (0,100>: ")

        graph = Graph.randomGraph(vertCount, density.toDouble() / 100.0)
        isGenerated = true
        printGraph()
    }

    private fun printGraph() {
        println("###### Lista sąsiedztwa #####")
        println(graph.adjList)
        println("\n###### Macierz sąsiedztwa #####")
        println(graph.adjMatrix)
        print("\n")
    }

    private fun prim() {
        val list = MST.primUsingAdjList(graph)
        val matrix = MST.primUsingAdjMatrix(graph)

        println("###### Prim z użciem listy sąsiedztwa #####")
        println(list.adjList)
        println("Waga: ${list.weight()}")
        println("\n###### Prim z użyciem macierzy sąsiedztwa #####")
        println(matrix.adjList)
        println("Waga: ${matrix.weight()}")
    }

    private fun kruskal() {
        val list = MST.kruskalUsingQueue(graph)
        val matrix = MST.kruskalUsingAdjMatrix(graph)

        println("###### Kruskal z użciem listy sąsiedztwa #####")
        println(list.adjList)
        println("Waga: ${list.weight()}")
        println("\n###### Kruskal z użyciem macierzy sąsiedztwa #####")
        println(matrix.adjList)
        println("Waga: ${matrix.weight()}")
    }

    private fun dijkstra() {

        val startPoint = if (isGenerated) askCount("Podaj wierzchołek startowy: ")
        else 0

        val dij = DijkstrasAlg(graph, startPoint)

        dij.shortestPathAdjList()
        println("###### Dijkstra z użciem listy sąsiedztwa #####")
        println(Utils.arrayToStringLines(dij.getPaths()))
        println()

        dij.shortestPathAdjMatrix()
        println("\n###### Dijkstra z użyciem macierzy sąsiedztwa #####")
        println(Utils.arrayToStringLines(dij.getPaths()))
        println()

    }

    private fun bellman() {

        val startPoint = if (isGenerated) askCount("Podaj wierzchołek startowy: ")
        else 0

        val bf = BellmanFordAlg(graph, startPoint)

        if(bf.shortestPathUsingAdjList()){
            println("###### BellmanFord z użciem listy sąsiedztwa #####")
            println(Utils.arrayToStringLines(bf.getPaths()))
            println()
        }else{
            println("Niepowodzenie")
        }


        if(bf.shortestPathUsingAdjList()){
            println("\n###### BellmanFord z użyciem macierzy sąsiedztwa #####")
            println(Utils.arrayToStringLines(bf.getPaths()))
            println()
        }else{
            println("Niepowodzenie")
        }
    }

    private fun askCount(s: String): Int {
        p(s)
        return readOption().toInt()
    }


    private fun readOption(): String {
        return readLine().toString().trim()
    }

    private fun p(any: Any) {
        println(any)
    }

    private fun askForFile(): File? {
        do {
            println("Podaj nazwę pliku:")
            val path = readLine()?.trim()

            val file = File("./dane_testowe/$path")

            if (file.exists()) return file
        } while (path != "0")

        return null
    }

    private fun printMenu() {
        print(
            "=======Menu Główne=========\n" +
                    "1. Wczytaj\n" +
                    "2. Generuj\n" +
                    "3. Wyświetl\n" +
                    "4. alg. Prima\n" +
                    "5. alg. Kruskala\n" +
                    "6. alg. Dijkstry\n" +
                    "7. alg. BF\n" +
                    "0. Zakończ\n" +
                    "Podaj opcję: "
        )
    }
}