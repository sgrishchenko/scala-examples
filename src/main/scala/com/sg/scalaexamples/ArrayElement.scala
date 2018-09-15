package com.sg.scalaexamples

class ArrayElement(
  val contents: Array[String]
) extends Element {
  if (height > 0)
    require(
      contents.tail.forall(_.length == contents.head.length)
    )

  def this(contents: String*) = this(Array(contents: _*))

  def above(that: Element): Element =
    new ArrayElement(this.contents ++ that.contents)

}
