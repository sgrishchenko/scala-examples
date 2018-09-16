package com.sg.scalaexamples.element

class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width: Int = s.length
  override def height = 1
}
