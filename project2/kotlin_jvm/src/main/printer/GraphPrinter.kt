package main.printer

import main.Graph
import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class GraphPrinter(private val graph: Graph) {

    private var angle = 0.0
    private val angleDiff = (360 / graph.V).toDouble()

    fun print() {
        val graphContainer = GraphContainer()
        val nodes = HashMap<Int, ColorNode>()
        val edges = graph.edgesArray

        for(i in 0 until graph.V){
            nodes[i] = createNode("$i")
        }

        for(e in edges){
            graphContainer.addEdge(Edge(nodes[e.src]!!, nodes[e.dest]!!))
        }

        for( i in nodes){
            graphContainer.addNode(i.value)
        }

        val frame = MainFrame(graphContainer)
        frame.run()
    }

    private fun createNode(name: String, radius: Double = 350.0, moveX: Int = 512, moveY: Int = 512): ColorNode {
        val angle = Math.toRadians(angle)
        var x = radius / Math.sqrt(1.0 + Math.pow(Math.tan(angle), 2.0))
        var y = Math.tan(angle) * x

        if (angle in Math.toRadians(90.0)..Math.toRadians(270.0)) {
            x *= -1.0
            y *= -1.0
        }
        this.angle+=angleDiff
        return ColorNode(name, (x + moveX).toInt(), (y + moveY).toInt(), Color.RED)
    }

}


private class Edge(private var nodeFrom: ColorNode, private var nodeTo: ColorNode, private val weight: Int = 0) {
    private val font = Font("SansSerif", 1, 16)
    fun draw(g: Graphics) {
        g.color = Color.BLACK
        g.drawLine(this.nodeFrom.x, this.nodeFrom.y, this.nodeTo.x, this.nodeTo.y)
//        val frc = (g as Graphics2D).fontRenderContext
//        val bounds = font.getStringBounds(this.weight.toString(), frc)
//        g.drawString(this.weight.toString(), this.x - bounds.width.toInt() / 2, this.y + bounds.height.toInt() / 4)
    }

    override fun toString(): String {
        return "[" + this.nodeFrom + "==>" + this.nodeTo + "]"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Edge
        return (nodeFrom == other.nodeFrom || nodeFrom == other.nodeTo)
    }

    override fun hashCode(): Int {
        var result = nodeFrom.hashCode()+  nodeTo.hashCode()
        result *= 31
        return result
    }
}

private class GraphContainer {
    private val nodes: MutableSet<ColorNode>
    private val edges: MutableSet<Edge>

    init {
        this.nodes = HashSet()
        this.edges = HashSet()
    }

    fun addNode(node: ColorNode) {
        this.nodes.add(node)
    }

    fun addEdge(edge: Edge) {
        this.edges.add(edge)
    }

    fun draw(g: Graphics) {
        for (e in this.edges) {
            e.draw(g)
        }
        for (n in this.nodes) {
            n.draw(g)
        }
    }

}

private class MainFrame(graph: GraphContainer) : JFrame("Graph editor"), Runnable {
    override fun run() {
        this.isVisible = true
    }

    init {
        val dim = Dimension(1024, 1024)
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        size = dim
        preferredSize = dim

        val panel = MainPanel(this, graph)
        contentPane = panel
        isResizable = false
    }
}

private class MainPanel(private val parent: Window, var graph: GraphContainer) : JPanel() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        graph.draw(g)
    }
}

private class ColorNode(private val name: String, var x: Int, var y: Int, private val color: Color = Color.RED) {
    private var radius = 20
    private val font = Font("SansSerif", 1, 16)

    fun draw(g: Graphics) {
        g.color = this.color
        g.fillOval(this.x - radius, this.y - radius, radius * 2, radius * 2)
        g.color = Color.BLACK
        g.drawOval(this.x - radius, this.y - radius, radius * 2, radius * 2)
        g.font = font
        val frc = (g as Graphics2D).fontRenderContext
        val bounds = font.getStringBounds(this.name, frc)
        g.drawString(this.name, this.x - bounds.width.toInt() / 2, this.y + bounds.height.toInt() / 4)
    }

    override fun toString(): String {
        return String.format("%s(%d, %d, %8X)", *arrayOf(this.name, Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.color.rgb)))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ColorNode
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}



