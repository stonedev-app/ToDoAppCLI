package com.app.stone.dev

import scala.io.Source

@main
def main(): Unit = {
  val todos = loadTodos(".todo.json")
  formatTodos(todos).foreach(println)
}

/***
 * formatTodoByKeys
 * @param keys keys to use format
 * @return Function to format Todo
 */
def formatTodoByKeys(keys: String*): Map[String, ujson.Value] => String = {
  todo => keys.map( key => key + ":" + todo(key)).mkString(" ")
}

/***
 * formatTodos
 * @param todos Todos
 * @return List of Todo string
 */
def formatTodos(todos: List[Map[String, ujson.Value]]): List[String] = {
  todos.map(formatTodoByKeys("id", "task", "done"))
}

/***
 * loadTodos
 * @param filename TodosFileName
 * @return Todos
 */
def loadTodos(filename: String): List[Map[String, ujson.Value]] = {
  val source = Source.fromFile(filename)
  val jsonString = source.mkString
  source.close()

  val json = ujson.read(jsonString)
  json.arr.map(_.obj.toMap).toList
}