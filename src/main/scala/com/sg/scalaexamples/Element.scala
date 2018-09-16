package com.sg.scalaexamples

import Element.elem

object Element {
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)

  def elem(contents: String*): Element =
    new ArrayElement(contents.toArray)

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element =
    new LineElement(line)
}

abstract class Element {
  def contents: Array[String]

  def height: Int = contents.length

  def width: Int =
    if (height == 0) 0
    else contents.head.length

  def above(that: Element): Element = {
    val first = this widen that.width
    val second = that widen this.width

    elem(first.contents ++ second.contents)
  }

  def beside(that: Element): Element = {
    val first = this heighten that.height
    val second = that heighten this.height

    elem(
      for (
        (line1, line2) <- first.contents zip second.contents
      ) yield line1 + line2
    )
  }

  def widen(width: Int): Element =
    stretch(_.width, width, _ beside _) { width =>
      elem(' ', width, height)
    }

  def heighten(height: Int): Element =
    stretch(_.height, height, _ above _) { height =>
      elem(' ', width, height)
    }

  private def stretch(
    source: Element => Int,
    target: Int,
    transform: (Element, Element) => Element
  )(factory: Int => Element): Element =
    if (target <= source(this)) this
    else {
      val first = factory((target - source(this)) / 2)
      val second = factory(target - source(this) - source(first))

      transform(
        transform(first, this),
        second
      )
    }

  override def toString: String = contents mkString "\n"
}
