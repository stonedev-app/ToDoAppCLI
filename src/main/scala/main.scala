package com.app.stone.dev

import todo.{JSON_FILE_NAME, formatTodos, makeAddTodos, makeDeleteTodos, nextIdCalculator}
import utils.{loadTodos, saveTodos}

@main
def main(args: String*): Unit = {

  if (args.isEmpty) {
    println("Usage: list|add [options]|delete [options]")
  } else {
    val options = args.tail.toList
    val todos = loadTodos(JSON_FILE_NAME)
    args(0) match {
      case "list" =>
        formatTodos(todos).foreach(println)
      case "add" =>
        val getNextId = nextIdCalculator(0)
        val addTodos = makeAddTodos(getNextId(todos), options)
        saveTodos(todos ++ addTodos, JSON_FILE_NAME)
        formatTodos(addTodos).foreach(println)
      case "delete" =>
        val ids = options.collect{
          case s if s.forall(_.isDigit) => s.toInt
        }
        val deleteTodos = makeDeleteTodos(ids, todos)
        saveTodos(todos.diff(deleteTodos), JSON_FILE_NAME)
        formatTodos(deleteTodos).foreach(println)
      case _ =>
        println("Unknown command. Usage: list|add [options]|delete [options]")
    }
  }
}