package trees

import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

data class Graph(val nodes: Set<Node>)
class Node(
    val id: Int,
    val children: MutableSet<Node> = mutableSetOf(),
    var visited: Boolean = false
)

data class BinaryNode constructor(
    val id: Int,
    var left: BinaryNode? = null,
    var right: BinaryNode? = null
) {
    fun bottom(): BinaryNode {
        return right?.bottom() ?: this
    }
}

fun inOrderTraversal(node: BinaryNode?): List<Int> = mutableListOf<Int>().apply {
    when {
        node != null -> {
            addAll(inOrderTraversal(node.left))
            add(node.id)
            addAll(inOrderTraversal(node.right))
        }
    }
}

fun preOrderTraversal(node: BinaryNode?): List<Int> = mutableListOf<Int>().apply {
    when {
        node != null -> {
            add(node.id)
            addAll(preOrderTraversal(node.left))
            addAll(preOrderTraversal(node.right))
        }
    }
}

fun postOrderTraversal(node: BinaryNode?): List<Int> = mutableListOf<Int>().apply {
    when {
        node != null -> {
            addAll(postOrderTraversal(node.left))
            addAll(postOrderTraversal(node.right))
            add(node.id)
        }
    }
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