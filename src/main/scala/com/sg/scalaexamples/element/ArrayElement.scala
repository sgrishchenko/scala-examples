package com.sg.scalaexamples.element

class ArrayElement(
  val contents: Array[String]
) extends Element {
  if (height > 0)
    require(
      contents.tail.forall(_.length == contents.head.length)
    )

  def this(contents: String*) = this(contents.toArray)
}
