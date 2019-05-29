
import main.Graph
import main.GraphLoader
import org.junit.Test
import kotlin.system.measureNanoTime

object TestUnit {

    fun runTests() {

    }

    fun testKruskalList(vertices: Int, density: Int, testCount: Int) : Long{


        return measureNanoTime {

        }

    }

    fun generateGraphs(vertices: Int, density: Int, count: Int){
        val path = "./gen/${vertices}/${density}"

        for (i in 0.. count){
            val g = Graph.randomGraph(vertices,density)
            GraphLoader.saveFile(g, "${path}_$i.txt")
        }
    }

}

class Runner{

    @Test
    fun run(){
        val densities = arrayOf(25,50,75,99)
        val numbers = arrayOf(10,100,1000,10000,30000)

        for (num in numbers)
            for (dens in densities){
               val genTime = measureNanoTime { TestUnit.generateGraphs(num,dens,50) }
                println("Generated $num $dens % in ${genTime/1000.0} ms")
            }

    }
}
