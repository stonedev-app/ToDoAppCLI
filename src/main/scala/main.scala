package com.app.stone.dev

import todo.formatTodos
import utils.loadTodos

@main
def main(): Unit = {
  val todos = loadTodos(".todo.json")
  formatTodos(todos).foreach(println)
}

