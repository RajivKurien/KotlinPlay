package trees

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class IndependentExecutorTest {

    private val underTest = IndependentExecutor()

    @RepeatedTest(5)
    fun `runs dependent tasks beforehand`() {
        // T0 --> T1 --> T2 ...
        val size = Random.nextInt(0, 10)
        val expectedOrder = IntRange(0, size).toList()
        val completedOrder = underTest.execute(size.chainedTasks())
            .map { t -> t.id }
            .toList()

        assertEquals(expectedOrder, completedOrder)
    }

    @Test
    fun `runs tasks only once`() {
//          T2
//        /  |
//       T1  |
//        \  |
//          T0
        val root = Task.IncompleteTask(0, emptySet())
        val child1 = Task.IncompleteTask(1, setOf(root))
        val child2 = Task.IncompleteTask(2, setOf(root, child1))

        val completedTasks = underTest.execute(child2)

        assertEquals(3, completedTasks.size)
        repeat(3) {
            assertEquals(it, completedTasks[it].id)
        }
    }

    @Test
    fun `runs more tasks only once`() {
//          T4
//        / |  \
//       /  T3  \
//       |/    \|
//       T1 -> T2
//        \    /
//          T0
        val root = Task.IncompleteTask(0, emptySet())
        val child1 = Task.IncompleteTask(1, setOf(root))
        val child2 = Task.IncompleteTask(2, setOf(root, child1))
        val child3 = Task.IncompleteTask(3, setOf(child1, child2))
        val child4 = Task.IncompleteTask(4, setOf(child1, child2, child3))

        val completedTasks = underTest.execute(child4)

        assertEquals(5, completedTasks.size)
        repeat(5) {
            assertEquals(it, completedTasks[it].id)
        }
    }

    private fun Int.chainedTasks(): Task.IncompleteTask = when (this) {
        0 -> Task.IncompleteTask(0, emptySet())
        else -> Task.IncompleteTask(this, setOf((this - 1).chainedTasks()))
    }
}