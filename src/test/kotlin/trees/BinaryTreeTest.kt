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

    @Test
    fun `minimum height binary search tree`() {
        assertEquals(
            BinaryNode(1, left = BinaryNode(0), right = BinaryNode(2)),
            minimalTree((0..2).toList())
        )

        assertEquals(
            BinaryNode(
                5,
                left = BinaryNode(
                    2, left = BinaryNode(0, right = BinaryNode(1)),
                    right = BinaryNode(3, right = BinaryNode(4))
                ),
                right = BinaryNode(
                    8,
                    left = BinaryNode(6, right = BinaryNode(7)),
                    right = BinaryNode(9, right = BinaryNode(10))
                )
            ),
            minimalTree((0..10).toList())
        )
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