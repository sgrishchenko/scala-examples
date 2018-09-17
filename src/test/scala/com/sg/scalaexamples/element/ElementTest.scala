package com.sg.scalaexamples.element

import org.scalatest.FunSuite
import Element.elem

class ElementTest extends FunSuite {
  test("elem result should have passed width") {
    val element = elem('x', 2, 3)

    assert(element.width == 2)
  }

  test("elem should throw an exception if the content has different lengths") {
    assertThrows[IllegalArgumentException] {
      elem(
        "short",
        "long"
      )
    }
  }
}
