package com.app.stone.dev
package todo

/***
 * makeTodos
 * @param todos Todos
 * @param titles Title of Todo
 * @return Todos
 */
def makeTodos(todos: List[Map[String, ujson.Value]], titles: List[String]) :List[Map[String, ujson.Value]] = {
  // NextId
  val nextId = getNextId(todos)
  // Generate a list of tuples containing the title and id
  val titleIdTupleList = titles.zipWithIndex.map { case(title, index) => (title, index + nextId) }
  // Newly created Todos
  titleIdTupleList.map{case(title, id) => createTodo(id, title)}
}

/** *
 * getNextId
 *
 * @param todos Todos
 * @return NextId. If todos is empty, return 0.
 */
private def getNextId(todos: List[Map[String, ujson.Value]]): Int = {
  // If todos is empty, maxId is Int.MinValue
  val maxId = todos.foldLeft(Int.MinValue) { (max, todo) =>
    val id = todo(Keys.Id.name).num.toInt
    if (id > max) id else max
  }
  // ID start at 0. If ToDos already exists, increment the ID by 1.
  if(maxId == Int.MinValue) 0 else maxId + 1
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