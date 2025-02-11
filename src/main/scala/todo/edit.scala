package com.app.stone.dev
package todo

/***
 * addTasks
 * @param todos Todos
 * @param tasks Tasks
 * @return Todos
 */
def addTasks(todos: List[Map[String, ujson.Value]], tasks: String*) :List[Map[String, ujson.Value]] = {
  // to get the starting id for adding new tasks
  val startId = getTodosMaxId(todos) + 1
  // Generate a list of tuples containing the tasks and id
  val taskIdTupleList = tasks.zipWithIndex.map { case(task, index) => (task, index + startId) }
  // Add the newly created Todos to the original Todos
  todos ++ taskIdTupleList.map{case(task, id) => createTodo(id, task)}
}

/** *
 * getTodosMaxId
 *
 * @param todos Todos
 * @return MaxId
 */
private def getTodosMaxId(todos: List[Map[String, ujson.Value]]): Int = {
  // The Todo list's ID starts from 0
  todos.foldLeft(0) { (max, todo) =>
    val id = todo("id").num.toInt
    if (id > max) id else max
  }
}

/***
 * createTodo
 * @param id id
 * @param task task
 * @return Todo
 */
private def createTodo(id: Int, task: String): Map[String, ujson.Value] = {
  Map(
    "id"   -> ujson.Num(id.toDouble),
    "task" -> ujson.Str(task),
    "done" -> ujson.Bool(false)
  )
}