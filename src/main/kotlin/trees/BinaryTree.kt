package trees

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


