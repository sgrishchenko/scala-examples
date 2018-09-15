package com.sg.scalaexamples

import org.scalatest.FunSuite

class RationalTest extends FunSuite {
  val oneHalf = new Rational(1, 2)
  val twoThirds = new Rational(2, 3)

  test("test +") {
    val sum = oneHalf + twoThirds

    assert(sum.toString === "7/6")
  }

  test("test + with Int") {
    val sum = oneHalf + 2

    assert(sum.toString === "5/2")
  }

  test("test *") {
    val product = oneHalf * twoThirds

    assert(product.toString === "1/3")
  }

  test("test * with Int") {
    val product = twoThirds * 2

    assert(product.toString === "4/3")
  }

  test("test /") {
    val product = oneHalf / twoThirds

    assert(product.toString === "3/4")
  }

  test("test / with Int") {
    val product = twoThirds / 3

    assert(product.toString === "2/9")
  }

  test("test max") {
    val max = oneHalf max twoThirds

    assert(max === twoThirds)
  }

  test("test <") {
    assert(oneHalf < twoThirds)
  }

  test("test >") {
    assert(twoThirds > oneHalf)
  }

  test("test toString") {
    assert(oneHalf.toString === "1/2")
    assert(twoThirds.toString === "2/3")
  }

  test("test normalize") {
    val oneHalf = new Rational(2, 4)

    assert(oneHalf.toString === "1/2")
  }
}
