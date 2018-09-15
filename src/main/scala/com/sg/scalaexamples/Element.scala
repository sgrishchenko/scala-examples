package com.sg.scalaexamples

abstract class Element {
  def contents: Array[String]

  def height: Int = contents.length

  def width: Int =
    if (height == 0) 0
    else contents.head.length

  override def toString: String = contents.mkString("\n")
}
