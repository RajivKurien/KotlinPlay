package trees


interface TaskExecutor {
    fun execute(task: Task.IncompleteTask): List<Task.CompletedTask>
}

class IndependentExecutor : TaskExecutor {

    private val completedTasks: MutableMap<Int, Task.CompletedTask> = mutableMapOf()

    override fun execute(task: Task.IncompleteTask): List<Task.CompletedTask> {
        task.dependencies
            .filterNot { completedTasks.keys.contains(it.id) }
            .map { t -> execute(t) }

        return completedTasks.run {
            put(task.id, task.run())
            values.toList()
        }
    }
}

sealed class Task(open val id: Int) {
    data class CompletedTask(override val id: Int) : Task(id)
    data class IncompleteTask(
        override val id: Int,
        val dependencies: Set<IncompleteTask>,
        var hasRun: Boolean = false
    ) : Task(id) {
        fun run(): CompletedTask {
            if (hasRun) throw Exception()
            println("Running task=${id}")
            hasRun = true
            return CompletedTask(id)
        }
    }
}