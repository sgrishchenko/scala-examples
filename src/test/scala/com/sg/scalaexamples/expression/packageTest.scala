package com.sg.scalaexamples.expression

import org.scalatest.FunSuite

class packageTest extends FunSuite {

  test("testSimplifyAll") {
    val operation = BinOp(
      "+",
      UnOp("-", UnOp("-", Var("x"))),
      UnOp("-", UnOp("-", Var("x")))
    )

    val expected = BinOp("*", Var("x"), Number(2))

    assert(simplifyAll(operation) == expected)
  }

}
