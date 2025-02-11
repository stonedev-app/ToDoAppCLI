package com.app.stone.dev
package todo

/**
 * json's file name of Todo
 */
val JSON_FILE_NAME = ".todo.json"

/***
 * Keys
 * @param name name of key
 */
private enum Keys(val name: String) {
  case Id extends Keys("id")
  case Title extends Keys("title")
  case Done extends Keys("done")
}
