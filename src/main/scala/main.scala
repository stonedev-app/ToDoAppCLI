package com.app.stone.dev

import todo.{JSON_FILE_NAME, formatTodos, makeTodos, nextIdCalculator}
import utils.{loadTodos, saveTodos}

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
        val getNextId = nextIdCalculator(0)
        val addTodos = makeTodos(getNextId(todos), params)
        saveTodos(todos ++ addTodos, JSON_FILE_NAME)
        formatTodos(addTodos).foreach(println)
      case _ =>
        println("Unknown command. Usage: list|add [options]")
    }
  }
}