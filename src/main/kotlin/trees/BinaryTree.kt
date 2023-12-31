package trees

import java.util.concurrent.LinkedBlockingQueue
import kotlin.math.abs

data class LLNode(
    val id: Int,
    var next: LLNode? = null
)

data class BinaryNode constructor(
    val id: Int,
    var left: BinaryNode? = null,
    var right: BinaryNode? = null,
    var visited: Boolean = false
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

fun minimalTree(elements: List<Int>): BinaryNode? {
    fun createMinimalTree(start: Int, end: Int): BinaryNode? = if (end < start) null else {
        val middle = (start + end) / 2
        val node = BinaryNode(elements[middle])
        node.left = createMinimalTree(start, middle - 1)
        node.right = createMinimalTree(middle + 1, end)
        node
    }

    return createMinimalTree(0, elements.lastIndex)
}

fun listByDepth(root: BinaryNode): Map<Int, LLNode> {
    val queue = LinkedBlockingQueue(listOf(Pair(0, root)))
    var result = mapOf<Int, LLNode>()

    while (queue.isNotEmpty()) {
        val depthAndNode = queue.remove()
        depthAndNode.second.left?.apply {
            queue.put(Pair(depthAndNode.first + 1, this))
        }
        depthAndNode.second.right?.apply {
            queue.put(Pair(depthAndNode.first + 1, this))
        }

        val next = LLNode(depthAndNode.second.id)
        if (result.containsKey(depthAndNode.first)) result[depthAndNode.first]!!.next = next
        else result = result + Pair(depthAndNode.first, next)
    }

    return result
}

fun recursiveListByDepth(root: BinaryNode): Map<Int, LLNode> {
    fun levelList(root: BinaryNode, map: MutableMap<Int, LLNode>, depth: Int) {
        map.compute(depth) { _, node ->
            val llNode = LLNode(root.id)
            when (node) {
                null -> llNode
                else -> {
                    node.next = llNode
                    node
                }
            }
        }
        root.left?.apply { levelList(this, map, depth + 1) }
        root.right?.apply { levelList(this, map, depth + 1) }
    }

    return mutableMapOf<Int, LLNode>().apply { levelList(root, this, depth = 0) }
}

fun BinaryNode.height(): Int =
    if (right == null && left == null) 1
    else maxOf(
        left?.run { height() + 1 } ?: 0,
        right?.run { height() + 1 } ?: 0
    )

fun BinaryNode.isBalanced(): Boolean = abs((left?.height() ?: 0) - (right?.height() ?: 0)) <= 1


