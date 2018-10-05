package com.sg.scalaexamples.scells

import scala.collection.mutable

trait Evaluator {
  this: Model =>
  type Op = List[Double] => Double
  val operations = new mutable.HashMap[String, Op]

  def evaluate(f: Formula): Double = try {
    f match {
      case Coord(row, column) => cells(row)(column).value
      case Number(value) => value
      case Textual(_) => Double.NaN
      case Application(function, arguments) =>
        val values = arguments flatMap evalList
        operations(function)(values)
    }
  } catch {
    case _: Exception => Double.NaN
  }

  private def evalList(f: Formula): List[Double] = f match {
    case Range(_, _) => references(f) map (_.value)
    case _ => List(evaluate(f))
  }

  def references(f: Formula): List[Cell] = f match {
    case Coord(row, column) =>
      List(cells(row)(column))
    case Range(Coord(r1, c1), Coord(r2, c2)) =>
      for {
        row <- (r1 to r2).toList
        column <- c1 to c2
      } yield cells(row)(column)
    case Application(_, arguments) =>
      arguments flatMap references
    case _ => List()
  }
}
