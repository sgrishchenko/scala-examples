package com.sg.scalaexamples.element

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import Element.elem

class ElementSpec extends FlatSpec with Matchers {
  "A UniformElement" should "have a width equal to the passed value" in {
    val element = elem('x', 2, 3)
    element.width should be(2)
  }

  it should "have a height equal to the passed value" in {
    val element = elem('x', 2, 3)
    element.height should be(3)
  }

  it should "throw an IAE if passed a negative width" in {
    an[IllegalArgumentException] should be thrownBy {
      elem('x', -2, 3)
    }
  }

  it should "throw an IAE if passed a negative height" in {
    an[IllegalArgumentException] should be thrownBy {
      elem('x', 2, -3)
    }
  }
}
