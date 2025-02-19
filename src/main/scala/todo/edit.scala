package com.app.stone.dev
package todo

/***
 * makeAddTodos
 * @param nextId Id
 * @param titles Title of Todo
 * @return Todos to be added
 */
def makeAddTodos(nextId: Int,
                 titles: List[String]) :List[Map[String, ujson.Value]] = {
  // Generate a list of tuples containing the title and id
  val titleIdTupleList = titles.zipWithIndex.map { case(title, index) => (title, index + nextId) }
  // Newly created Todos
  titleIdTupleList.map{case(title, id) => createTodo(id, title, false)}
}

/***
 * nextIdCalculator
 * @param startId Id
 * @return getNextId(todos): => nextId
 */
def nextIdCalculator(startId: Int): List[Map[String, ujson.Value]] => Int = {
  todos => {
    // Convert to empty if todos is null
    val safeTodos = Option(todos).getOrElse(List.empty)
    // Get the maximum value of the ID
    val maxId = safeTodos.map(todo => todo(Keys.Id.name).num.toInt).maxOption
    // If an ID can be retrieved, increment it by 1. Otherwise, set the startId
    maxId.map(_ + 1).getOrElse(startId)
  }
}

/***
 * makeDeleteTodos
 * @param ids ids to be deleted
 * @param todos Original Todos
 * @return Todos to be deleted
 */
def makeDeleteTodos(ids: List[Int],
                    todos: List[Map[String, ujson.Value]]): List[Map[String, ujson.Value]] = {
  todos.filter(todo => ids.contains(todo(Keys.Id.name).num.toInt))
}

/***
 * createTodo
 * @param id id
 * @param title Title
 * @param done true:done false:not done
 * @return Todo
 */
private def createTodo(id: Int, title: String, done: Boolean): Map[String, ujson.Value] = {
  Map(
    Keys.Id.name    -> ujson.Num(id.toDouble),
    Keys.Title.name -> ujson.Str(title),
    Keys.Done.name  -> ujson.Bool(done)
  )
}