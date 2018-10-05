package com.sg.scalaexamples.scells

import scala.util.parsing.combinator.RegexParsers
import IndexConverters.lettersToInt

object FormulaParsers extends RegexParsers {
  def ident: Parser[String] = """[a-zA-Z_]\w*""".r

  def decimal: Parser[String] = """-?\d+(\.\d*)?""".r

  def column: Parser[String] = """[A-Za-z]+""".r

  def row: Parser[Int] = """\d+""".r ^^ (_.toInt)

  def cell: Parser[Coord] =
    column ~ row ^^ {
      case column ~ row => Coord(row, lettersToInt(column))
    }

  def range: Parser[Range] =
    cell ~ ":" ~ cell ^^ {
      case c1 ~ ":" ~ c2 => Range(c1, c2)
    }

  def number: Parser[Number] =
    decimal ^^ (d => Number(d.toDouble))

  def application: Parser[Application] =
    ident ~ "(" ~ repsep(expr, ",") ~ ")" ^^ {
      case f ~ "(" ~ ps ~ ")" => Application(f, ps)
      case result => throw new IllegalArgumentException(result.toString)
    }

  def expr: Parser[Formula] =
    range | cell | number | application

  def textual: Parser[Textual] =
    """[^=].*""".r ^^ Textual

  def formula: Parser[Formula] =
    number | textual | "=" ~> expr

  def parse(input: String): Formula =
    parseAll(formula, input) match {
      case Success(result, _) => result
      case error: NoSuccess => Textual(s"[${error.msg}]")
    }
}
