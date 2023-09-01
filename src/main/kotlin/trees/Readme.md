Problem statement

Write a task executor that runs tasks in order.
Each task, T, can have multiple other tasks it depends on.
Before running the task T, the task executor must complete running all the dependent tasks.
Each dependent task can also have tasks that it depends on.

Case 1

- All dependent tasks are independent - the dependencies form a graph such that no two tasks depend on the same task.
  Eg:

```
T1   T2
 \  /
  T0
```

Case 2

- Multiple tasks can depend on the same task, and each task must be run once. Eg:

```
     T3
   /    \
  T1 -> T2
   \    /
     T0
```

Skeleton code:

```kotlin
interface TaskExecutor {
    fun execute(t: Task)
}

data class Task(dependencies: Set<Task>)
```