package com.app.stone.dev
package utils

import java.io.PrintWriter
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.Try

/***
 * loadTodos
 *
 * @param filename TodosFileName
 * @return Todos
 */
def loadTodos(filename: String): List[Map[String, ujson.Value]] = {
  Try {
    val source = Source.fromFile(filename)
    val jsonString = try source.mkString finally source.close()
    val json = ujson.read(jsonString)
    json.arr.map(_.obj.toMap).toList
  }.getOrElse(List.empty)
}

/***
 * saveTodos
 *
 * @param todos Todos
 * @param filename TodosFileName
 */
def saveTodos(todos: List[Map[String, ujson.Value]], filename: String): Unit = {
  // list for storing jsonObj
  val listBuffer: ListBuffer[ujson.Obj] = ListBuffer()

  // Loop through todos
  for (todo <- todos) {
    // create jsonObj
    val jsonObj = ujson.Obj()
    // Transfer the keys and values from the Todo(Map) to jsonObj
    for ((key, value) <- todo) {
      jsonObj(key) = value
    }
    // Add jsonObj to the list
    listBuffer += jsonObj
  }

  // Convert the jsonObj inside the list into a ujson.Arr
  val json = ujson.Arr(listBuffer.toList: _*)

  // Convert to a string
  val jsonString = json.render(indent = 2)

  // write to a file
  val writer = new PrintWriter(filename)
  writer.write(jsonString)
  writer.close()
}