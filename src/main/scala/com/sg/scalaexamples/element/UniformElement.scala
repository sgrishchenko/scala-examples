package com.sg.scalaexamples.element

class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int
) extends Element {
  require(width >= 0, "The width should not be negative.")
  require(height >= 0, "The height should not be negative.")

  private val line = ch.toString * width
  def contents: Array[String] = Array.fill(height)(line)
}
