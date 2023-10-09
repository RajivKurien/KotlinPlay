package trees

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

    @Test
    fun `list of nodes at each depth`() {
        val resultMap1 = listByDepth(binaryTree)

        assertEquals(4, resultMap1.keys.size)
        assertEquals(LLNode(1), resultMap1[0])
        assertEquals(LLNode(0, LLNode(6)), resultMap1[1])
        assertEquals(LLNode(4, LLNode(7)), resultMap1[2])
        assertEquals(LLNode(3, LLNode(5)), resultMap1[3])

        assertEquals(resultMap1, recursiveListByDepth(binaryTree))
    }

    @Test
    fun `check binary tree is balanced`() {
        assertFalse(binaryTree.isBalanced())
        assertTrue(BinaryNode(5).isBalanced())
        assertTrue(BinaryNode(5, BinaryNode(4), BinaryNode(3)).isBalanced())
    }

    @Test
    fun `sub tree height`() {
        assertEquals(4, binaryTree.height())
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