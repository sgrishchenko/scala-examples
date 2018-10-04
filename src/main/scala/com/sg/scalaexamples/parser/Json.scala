package com.sg.scalaexamples.parser

import scala.util.parsing.combinator.JavaTokenParsers

class Json extends JavaTokenParsers {
  def value: Parser[Any] = (
    obj
    | arr
    | stringLiteral
    | floatingPointNumber
    | "null"
    | "true"
    | "false"
  )

  def obj: Parser[Map[String, Any]] = (
    "{" ~> repsep(member, ",") <~ "}"
    ^^ (Map() ++ _)
  )

  def arr: Parser[List[Any]] = "[" ~> repsep(value, ",") <~ "]"

  def member: Parser[(String, Any)] = (
    stringLiteral ~ ":" ~ value
    ^^ { case name ~ ":" ~ value => (name, value) }
  )
}