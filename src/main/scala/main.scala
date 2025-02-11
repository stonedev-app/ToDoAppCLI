package com.app.stone.dev

import todo.formatTodos
import utils.loadTodos

@main
def main(args: String*): Unit = {

  val jsonFileName = ".todo.json"

  if (args.isEmpty) {
    println("Usage: list|add [options]")
  } else {
    args(0) match {
      case "list" =>
        val todos = loadTodos(jsonFileName)
        formatTodos(todos).foreach(println)
      case "add" =>
        val todos = loadTodos(jsonFileName)
        // TODO: add task
        // TODO: write json file
      case _ =>
        println("Unknown command. Usage: list|add [options]")
    }
  }
}