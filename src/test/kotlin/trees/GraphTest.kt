package trees

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GraphTest {

    @Test
    fun `depth first search`() {
        buildExample1Graph()
        val graph = Graph(setOf(node0, node1, node2, node3, node4, node5))

        val result = graph.depthFirstSearch()

        assertEquals(listOf(0, 1, 3, 2, 4, 5), result)
    }

    @Test
    fun `depth first search simple`() {
        node0.children.addAll(setOf(node1))
        node1.children.addAll(setOf(node0))

        val graph = Graph(setOf(node0, node1))

        val result = graph.depthFirstSearch()

        assertEquals(listOf(0, 1), result)
    }

    @Test
    fun `breadth first search`() {
        buildExample1Graph()
        val graph = Graph(setOf(node0))

        val result = graph.breadthFirstSearch()

        assertEquals(listOf(0, 1, 4, 5, 3, 2), result)
    }

    @Test
    fun `does route exist between two nodes case1`() {
        buildExample1Graph()
        assertTrue(routeExistsBetweenNodes(node0, node3))
    }

    @Test
    fun `does route exist between two nodes case2`() {
        buildExample1Graph()
        assertTrue(routeExistsBetweenNodes(node0, node2))
    }

    @Test
    fun `does route exist between two nodes case3`() {
        buildExample1Graph()
        assertFalse(routeExistsBetweenNodes(node2, node5))
    }

    private fun buildExample1Graph() {
        node0.children.addAll(setOf(node1, node4, node5))
        node1.children.addAll(setOf(node3, node4))
        node2.children.addAll(setOf(node1))
        node3.children.addAll(setOf(node2, node4))
    }

    private var node0 = Node(0)
    private var node1 = Node(1)
    private var node2 = Node(2)
    private var node3 = Node(3)
    private var node4 = Node(4)
    private var node5 = Node(5)
}