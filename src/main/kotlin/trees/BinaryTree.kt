package trees

import java.util.concurrent.LinkedBlockingQueue

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
    val queue = LinkedBlockingQueue(mutableListOf(Pair(0, root)))
    val result = mutableMapOf<Int, LLNode>()

    while (queue.isNotEmpty()) {
        val depthAndNode = queue.remove()
        depthAndNode.second.left?.apply {
            queue.put(Pair(depthAndNode.first + 1, this))
        }
        depthAndNode.second.right?.apply {
            queue.put(Pair(depthAndNode.first + 1, this))
        }

        result.compute(depthAndNode.first) { _, node ->
            val next = LLNode(depthAndNode.second.id)
            when (node) {
                null -> next
                else -> {
                    node.next = next;
                    node
                }
            }
        }
    }

    return result
}


