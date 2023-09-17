package trees

import java.util.Objects
import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

data class Graph(val nodes: Set<Node>)
class Node(
    val id: Int,
    val children: MutableSet<Node> = mutableSetOf(),
    var visited: Boolean = false
) {
    override fun equals(other: Any?): Boolean = when (other) {
        is Node -> other.id == id
        else -> false
    }

    override fun hashCode(): Int = Objects.hash(this.id)
}

fun Graph.depthFirstSearch(): List<Int> {
    fun search(node: Node): List<Int> = when {
        node.visited -> emptyList()
        else -> {
            node.visited = true
            listOf(node.id) + node.children.flatMap { search(it) }
        }
    }

    return nodes.flatMap { search(it) }
}

fun Graph.breadthFirstSearch(): List<Int> {
    fun search(queue: Queue<Node>): List<Int> = mutableListOf<Int>().apply {
        while (queue.isNotEmpty()) queue.forEach {
            when {
                it.visited -> queue.remove(it)
                else -> {
                    it.visited = true
                    queue.addAll(it.children)
                    add(it.id)
                }
            }
        }
    }

    return search(LinkedBlockingQueue(nodes));
}

fun routeExistsBetweenNodes(one: Node, another: Node): Boolean {
    fun bfsRouteExists(nodes: Set<Node>, target: Node): Boolean {
        val queue = LinkedBlockingQueue(nodes)
        while (queue.isNotEmpty()) {
            queue.forEach {
                if (it == target) return true
                if (it.visited) queue.remove(it)
                else {
                    it.visited = true
                    queue.addAll(it.children)
                }
            }
        }
        return false
    }

    // BFS starting from both nodes
    return bfsRouteExists(one.children, target = another) or bfsRouteExists(another.children, target = one)
}
