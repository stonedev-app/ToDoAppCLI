package com.app.stone.dev
package utils

import scala.io.Source

/***
 * loadTodos
 *
 * @param filename TodosFileName
 * @return Todos
 */
def loadTodos(filename: String): List[Map[String, ujson.Value]] = {
  val source = Source.fromFile(filename)
  val jsonString = source.mkString
  source.close()

  val json = ujson.read(jsonString)
  json.arr.map(_.obj.toMap).toList
}