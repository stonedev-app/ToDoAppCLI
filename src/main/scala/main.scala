package com.app.stone.dev

import todo.{JSON_FILE_NAME, addTodos, formatTodos}
import utils.loadTodos

@main
def main(args: String*): Unit = {

  if (args.isEmpty) {
    println("Usage: list|add [options]")
  } else {
    val params = args.tail.toList
    args(0) match {
      case "list" =>
        val todos = loadTodos(JSON_FILE_NAME)
        formatTodos(todos).foreach(println)
      case "add" =>
        val todos = loadTodos(JSON_FILE_NAME)
        val newTodos = addTodos(todos, params)
        // TODO: write json file
      case _ =>
        println("Unknown command. Usage: list|add [options]")
    }
  }
}