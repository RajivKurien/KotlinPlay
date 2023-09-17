package trees

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BinaryTreeTest {

    @Test
    fun `in order traversal`() {
        val result = inOrderTraversal(binaryTree)
        assertEquals(listOf(0, 1, 3, 4, 5, 6, 7), result)
    }

    @Test
    fun `pre order traversal`() {
        val result = preOrderTraversal(binaryTree)
        assertEquals(listOf(1, 0, 6, 4, 3, 5, 7), result)
    }

    @Test
    fun `post order traversal`() {
        val result = postOrderTraversal(binaryTree)
        assertEquals(listOf(0, 3, 5, 4, 7, 6, 1), result)
    }

    @Test
    fun `bottom most element`() {
        assertEquals(BinaryNode(7), binaryTree.bottom())
    }

    private val binaryTree = BinaryNode(
        id = 1,
        left = BinaryNode(0),
        right = BinaryNode(
            id = 6,
            left = BinaryNode(
                id = 4,
                left = BinaryNode(3),
                right = BinaryNode(5)
            ),
            right = BinaryNode(7)
        )
    )
}