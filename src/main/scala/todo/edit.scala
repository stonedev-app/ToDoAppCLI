package com.app.stone.dev
package todo

/***
 * addTodos
 * @param todos Todos
 * @param titles Title of Todo
 * @return Todos
 */
def addTodos(todos: List[Map[String, ujson.Value]], titles: List[String]) :List[Map[String, ujson.Value]] = {
  // to get the starting id for adding new todos
  val startId = getTodosMaxId(todos) + 1
  // Generate a list of tuples containing the title and id
  val titleIdTupleList = titles.zipWithIndex.map { case(title, index) => (title, index + startId) }
  // Add the newly created Todos to the original Todos
  todos ++ titleIdTupleList.map{case(title, id) => createTodo(id, title)}
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
    val id = todo(Keys.Id.name).num.toInt
    if (id > max) id else max
  }
}

/***
 * createTodo
 * @param id id
 * @param title Title
 * @return Todo
 */
private def createTodo(id: Int, title: String): Map[String, ujson.Value] = {
  Map(
    Keys.Id.name    -> ujson.Num(id.toDouble),
    Keys.Title.name -> ujson.Str(title),
    Keys.Done.name  -> ujson.Bool(false)
  )
}