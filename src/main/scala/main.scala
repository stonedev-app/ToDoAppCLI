package com.app.stone.dev

import todo.formatTodos
import utils.loadTodos

@main
def main(args: String*): Unit = {
  if (args.isEmpty) {
    println("Usage: list|add [options]")
  } else {
    args(0) match {
      case "list" =>
        val todos = loadTodos(".todo.json")
        formatTodos(todos).foreach(println)
      case "add" =>
        val todos = loadTodos(".todo.json")
        // TODO: add task
        // TODO: write json file
      case _ =>
        println("Unknown command. Usage: list|add [options]")
    }
  }
}

