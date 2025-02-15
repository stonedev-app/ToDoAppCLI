package com.app.stone.dev
package todo

/***
 * makeTodos
 * @param getNextId get NextId form Todos
 * @param titles Title of Todo
 * @return Todos
 */
def makeTodos(getNextId: Int => Int,
              titles: List[String]) :List[Map[String, ujson.Value]] = {
  // Get nextId. startId is 0
  val nextId = getNextId(0)
  // Generate a list of tuples containing the title and id
  val titleIdTupleList = titles.zipWithIndex.map { case(title, index) => (title, index + nextId) }
  // Newly created Todos
  titleIdTupleList.map{case(title, id) => createTodo(id, title)}
}

/** *
 * nextIdCalculator
 *
 * @param todos Todos
 * @return getNextId(startId:Int): => nextId:Int
 */
def nextIdCalculator(todos: List[Map[String, ujson.Value]]): Int => Int = {
  // If todos is empty, maxId is Int.MinValue
  val maxId = todos.foldLeft(Int.MinValue) { (max, todo) =>
    val id = todo(Keys.Id.name).num.toInt
    if (id > max) id else max
  }
  // ID start at startId. If ToDos already exists, increment the ID by 1.
  startId => if(maxId == Int.MinValue) startId else maxId + 1
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