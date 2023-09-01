package trees


interface TaskExecutor {
    fun execute(task: Task.IncompleteTask): List<Task.CompletedTask>
}

class IndependentExecutor : TaskExecutor {

    private var completed: LinkedHashSet<Task.CompletedTask> = LinkedHashSet()

    override fun execute(task: Task.IncompleteTask): List<Task.CompletedTask> {
        return execute(task, completed)
    }

    private fun execute(task: Task.IncompleteTask, finished: Set<Task.CompletedTask>): List<Task.CompletedTask> {
        val toRun = task.dependencies subtract finished subtract completed
        val done = toRun.flatMap { t -> execute(t as Task.IncompleteTask, completed) }.toList() + task.run()

        return completed.run {
            addAll(done)
            println("Completed=$this")
            toList()
        }
    }
}

sealed class Task(open val id: Int) {
    data class CompletedTask(override val id: Int) : Task(id)
    data class IncompleteTask(override val id: Int, val dependencies: Set<IncompleteTask>) : Task(id) {
        fun run(): CompletedTask {
            println("Running task=${this@IncompleteTask.id}")
            return CompletedTask(id)
        }
    }
}