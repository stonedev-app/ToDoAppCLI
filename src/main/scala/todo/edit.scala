package com.app.stone.dev
package todo

/***
 * addTodos
 * @param todos Todos
 * @param titles Title of Todo
 * @return Todos
 */
def addTodos(todos: List[Map[String, ujson.Value]], titles: List[String]) :List[Map[String, ujson.Value]] = {
  // get the maxId from todos
  val maxId = getTodosMaxId(todos)
  // ID start at 0. If ToDos already exists, increment the ID by 1.
  val startId = if(maxId == Int.MinValue) 0 else maxId + 1
  // Generate a list of tuples containing the title and id
  val titleIdTupleList = titles.zipWithIndex.map { case(title, index) => (title, index + startId) }
  // Add the newly created Todos to the original Todos
  todos ++ titleIdTupleList.map{case(title, id) => createTodo(id, title)}
}

/** *
 * getTodosMaxId
 *
 * @param todos Todos
 * @return MaxId. If todos is empty, return Int.MinValue.
 */
private def getTodosMaxId(todos: List[Map[String, ujson.Value]]): Int = {
  // If todos is empty, return Int.MinValue
  todos.foldLeft(Int.MinValue) { (max, todo) =>
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