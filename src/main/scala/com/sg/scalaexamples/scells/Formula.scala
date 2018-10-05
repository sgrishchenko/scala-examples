package com.sg.scalaexamples.scells

import IndexConverters.intToLetters

trait Formula

case class Coord(row: Int, column: Int) extends Formula {
  override def toString: String = intToLetters(column) + row
}

case class Range(c1: Coord, c2: Coord) extends Formula {
  override def toString: String = s"$c1:$c2"
}

case class Number(value: Double) extends Formula {
  override def toString: String = value.toString
}

case class Textual(value: String) extends Formula {
  override def toString: String = value
}

case class Application(function: String, arguments: List[Formula]) extends Formula {
  override def toString: String = function + arguments.mkString("(", ",", ")")
}

object Empty extends Textual("")
