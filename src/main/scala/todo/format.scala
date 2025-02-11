package com.app.stone.dev
package todo

/** *
 * formatTodos
 *
 * @param todos Todos
 * @return List of Todo string
 */
def formatTodos(todos: List[Map[String, ujson.Value]]): List[String] = {
  todos.map(formatTodoByKeys(Keys.Id.name, Keys.Title.name, Keys.Done.name))
}

/** *
 * formatTodoByKeys
 *
 * @param keys keys to use format
 * @return Function to format Todo
 */
private def formatTodoByKeys(keys: String*): Map[String, ujson.Value] => String = {
  todo => keys.map(key => key + ":" + todo(key)).mkString(" ")
}
