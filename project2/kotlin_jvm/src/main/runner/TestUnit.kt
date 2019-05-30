import main.Graph
import main.GraphLoader
import main.algorithms.BellmanFordAlg
import main.algorithms.DijkstrasAlg
import main.algorithms.MST
import org.junit.Test
import java.io.File
import kotlin.system.measureNanoTime

object TestUnit {

    fun runTests(testCount: Int = 30) {
        val densities = arrayOf(25, 50, 75, 99)
        val vertices = arrayOf(10, 100, 200, 400, 800, 1000, 2000)

        val kruskal = HashMap<String, Long>()
        val kruskalM = HashMap<String, Long>()
        val prim = HashMap<String, Long>()
        val primM = HashMap<String, Long>()
        val dijkstra = HashMap<String, Long>()
        val dijkstraM = HashMap<String, Long>()
        val bellman = HashMap<String, Long>()
        val bellmanM = HashMap<String, Long>()

//        for(i in 0 until testCount){
//            for((x, v) in vertices.withIndex())
//                for((j,d) in densities.withIndex()){
//                    if(!kruskal.containsKey("${v}_$d")) kruskal["${v}_$d"] = 0
//                    var temp: Long = kruskal["${v}_$d"]!!
//                    temp+= kruskalList(v,d,i)
//                    kruskal["${v}_$d"] = temp
//                    println("kruskalList,$v,$d%,$temp")
//                }
//        }
//        println()
//
//        for(i in 0 until testCount){
//            for((x, v) in vertices.withIndex())
//                for((j,d) in densities.withIndex()){
//                    if(!prim.containsKey("${v}_$d")) prim["${v}_$d"] = 0
//                    var temp: Long = prim["${v}_$d"]!!
//                    temp+= primList(v,d,i)
//                    prim["${v}_$d"] = temp
//                    println("primList,$v,$d%,$temp")
//                }
//        }
//        println()
//
//        for(i in 0 until testCount){
//            for((x, v) in vertices.withIndex())
//                for((j,d) in densities.withIndex()){
//                    if(!kruskalM.containsKey("${v}_$d")) kruskalM["${v}_$d"] = 0
//                    var temp: Long = kruskalM["${v}_$d"]!!
//                    temp+= kruskalMatrix(v,d,i)
//                    kruskalM["${v}_$d"] = temp
//                    println("KruskalM,$v,$d%,$temp")
//                }
//        }
//        println()
//
//        for(i in 0 until testCount){
//            for((x, v) in vertices.withIndex())
//                for((j,d) in densities.withIndex()){
//                    if(!primM.containsKey("${v}_$d")) primM["${v}_$d"] = 0
//                    var temp: Long = primM["${v}_$d"]!!
//                    temp+= primMatrix(v,d,i)
//                    primM["${v}_$d"] = temp
//                    println("primM,$v,$d%,$temp")
//                }
//        }
//        println()
//
//        for(i in 0 until testCount){
//            for((x, v) in vertices.withIndex())
//                for((j,d) in densities.withIndex()){
//                    if(!bellman.containsKey("${v}_$d")) dijkstra["${v}_$d"] = 0
//                    var temp: Long = dijkstra["${v}_$d"]!!
//                    temp+= dijkstraList(v,d,i)
//                    dijkstra["${v}_$d"] = temp
//                    println("dijkstraList,$v,$d%,$temp")
//                }
//        }
//        println()
//
//        for(i in 0 until testCount){
//            for((x, v) in vertices.withIndex())
//                for((j,d) in densities.withIndex()){
//                    if(!dijkstraM.containsKey("${v}_$d")) dijkstraM["${v}_$d"] = 0
//                    var temp: Long = dijkstraM["${v}_$d"]!!
//                    temp+= dijkstraMatrix(v,d,i)
//                    dijkstraM["${v}_$d"] = temp
//                    println("dijkstraM,$v,$d%,$temp")
//                }
//        }
//        println()

//        for(i in 0 until testCount){
//            for((x, v) in vertices.withIndex())
//                for((j,d) in densities.withIndex()){
//                    if(!bellman.containsKey("${v}_$d")) bellman["${v}_$d"] = 0
//                    var temp: Long = bellman["${v}_$d"]!!
//                    temp+= bellmanList(v,d,i)
//                    bellman["${v}_$d"] = temp
//                    println("bellmanList,$v,$d%,$temp")
//                }
//        }
//        println()


        for ((x, v) in vertices.withIndex())
            for ((j, d) in densities.withIndex()) {
                val gp = GraphLoader.loadFile("./gen/$v/${d}_0.txt")
                for (i in 0 until testCount) {
                    if (!bellmanM.containsKey("${v}_$d")) bellmanM["${v}_$d"] = 0
                    var temp: Long = bellmanM["${v}_$d"]!!
                    temp += bellmanMatrix(v, d, i,gp)
                    bellmanM["${v}_$d"] = temp
                    println("bellmanM,$v,$d%,$temp")
                }
            }
        println()

    }


    fun kruskalList(vertices: Int, density: Int, testCount: Int): Long {
        val path = "./gen/$vertices/${density}_$testCount.txt"
        val graph = GraphLoader.loadFile(path)

        return measureNanoTime {
            MST.kruskalUsingQueueList(graph)
        }
    }

    fun kruskalMatrix(vertices: Int, density: Int, testCount: Int): Long {
        val path = "./gen/$vertices/${density}_$testCount.txt"
        val graph = GraphLoader.loadFile(path)

        return measureNanoTime {
            MST.kruskalUsingAdjMatrixList(graph)
        }
    }

    fun primList(vertices: Int, density: Int, testCount: Int): Long {
        val path = "./gen/$vertices/${density}_$testCount.txt"
        val graph = GraphLoader.loadFile(path)

        return measureNanoTime {
            MST.primUsingAdjListList(graph)
        }
    }

    fun primMatrix(vertices: Int, density: Int, testCount: Int): Long {
        val path = "./gen/$vertices/${density}_$testCount.txt"
        val graph = GraphLoader.loadFile(path)

        return measureNanoTime {
            MST.primUsingAdjMatrixList(graph)
        }
    }

    fun dijkstraList(vertices: Int, density: Int, testCount: Int): Long {
        val path = "./gen/$vertices/${density}_$testCount.txt"
        val graph = GraphLoader.loadFile(path)
        val dij = DijkstrasAlg(graph, 0)
        return measureNanoTime {
            dij.shortestPathAdjList()
        }
    }

    fun dijkstraMatrix(vertices: Int, density: Int, testCount: Int): Long {
        val path = "./gen/$vertices/${density}_$testCount.txt"
        val graph = GraphLoader.loadFile(path)
        val dij = DijkstrasAlg(graph, 0)
        return measureNanoTime {
            dij.shortestPathAdjMatrix()
        }
    }

    fun bellmanList(vertices: Int, density: Int, testCount: Int): Long {
        val path = "./gen/$vertices/${density}_$testCount.txt"
        val graph = GraphLoader.loadFile(path)
        val bf = BellmanFordAlg(graph, 0)
        return measureNanoTime {
            bf.shortestPathUsingAdjMatrix()
        }
    }

    fun bellmanMatrix(vertices: Int, density: Int, testCount: Int, graph: Graph): Long {
        val path = "./gen/$vertices/${density}_$testCount.txt"
        //  val graph = GraphLoader.loadFile(path)
        val bf = BellmanFordAlg(graph, 0)
        return measureNanoTime {
            bf.shortestPathUsingAdjMatrix()
        }
    }

    fun generateGraphs(vertices: Int, density: Int, count: Int) {
        val path = "./gen/$vertices/$density"

        for (i in 0 until count) {
            val g = Graph.randomGraphManyVerts(vertices, density / 100.0)
            GraphLoader.saveFile(g, "${path}_$i.txt")
        }
    }

}

class Runner {

    @Test
    fun run() {
        val densities = arrayOf(25, 50, 75, 99)
        // val numbers = arrayOf(10,100,1000,10000,30000)
        val numbers = arrayOf(10000, 30000)

        for (num in numbers)
            for (dens in densities) {
                val genTime = measureNanoTime { TestUnit.generateGraphs(num, dens, 2) }
                println("Generated $num $dens % in ${genTime / 1000000.0} ms")
            }
    }
}
