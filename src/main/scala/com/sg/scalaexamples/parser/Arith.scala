package com.sg.scalaexamples.parser

import scala.util.parsing.combinator.JavaTokenParsers

class Arith extends JavaTokenParsers {

  def expr: Parser[Double] = (
    term ~ rep("+" ~ term | "-" ~ term)
    ^^ {
      case term ~ terms => (term /: terms) {
        case (result, "+" ~ value) => result + value
        case (result, "-" ~ value) => result - value
        case _ => throw new IllegalStateException
      }
    }
  )

  def term: Parser[Double] = (
    factor ~ rep("*" ~ factor | "/" ~ factor)
    ^^ {
      case term ~ terms => (term /: terms) {
        case (result, "*" ~ value) => result * value
        case (result, "/" ~ value) => result / value
        case _ => throw new IllegalStateException
      }
    }
  )

  def factor: Parser[Double] = (
    (floatingPointNumber | "(" ~> expr <~ ")")
    ^^ {
      case value: String => value.toDouble
      case value: Double => value
      case _ => throw new IllegalStateException
    }
  )
}
