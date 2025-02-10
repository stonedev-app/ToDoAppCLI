package com.app.stone.dev
package todo

/** *
 * formatTodoByKeys
 *
 * @param keys keys to use format
 * @return Function to format Todo
 */
def formatTodoByKeys(keys: String*): Map[String, ujson.Value] => String = {
  todo => keys.map(key => key + ":" + todo(key)).mkString(" ")
}

/** *
 * formatTodos
 *
 * @param todos Todos
 * @return List of Todo string
 */
def formatTodos(todos: List[Map[String, ujson.Value]]): List[String] = {
  todos.map(formatTodoByKeys("id", "task", "done"))
}
