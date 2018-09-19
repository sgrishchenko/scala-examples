package com.sg.scalaexamples.expression

import com.sg.scalaexamples.element.Element
import com.sg.scalaexamples.element.Element.elem

object ExprFormatter {
  def format(expr: Expr, enclPrec: Int = 0): Element = expr match {
    case Var(name) =>
      elem(name)
    case Number(num) =>
      elem(stripDot(num.toString))
    case UnOp(op, arg) =>
      elem(op) beside format(arg, unaryPrecedence)
    case BinOp("/", left, right) =>
      val top = format(left, fractionPrecedence)
      val bottom = format(right, fractionPrecedence)

      val width = top.width max bottom.width
      val line = elem('-', width, 1)
      val frac = top above line above bottom

      if (enclPrec != fractionPrecedence)
        frac
      else
        wrap(frac, " ")
    case BinOp(op, left, right) =>
      val opPrec = precedence(op)
      println(op, enclPrec, opPrec)
      val formattedLeft = format(left, opPrec)
      val formattedRight = format(right, opPrec + 1)

      val formattedOp = wrap(
        elem(s" $op "),
        formattedLeft,
        formattedRight
      )

      if (enclPrec <= opPrec)
        formattedOp
      else
        wrap(formattedOp, "(", ")")
  }

  private def wrap(content: Element, s: String): Element =
    wrap(content, s, s)

  private def wrap(content: Element, left: String, right: String): Element =
    wrap(content, elem(left), elem(right))

  private def wrap(content: Element, left: Element, right: Element): Element =
    left beside content beside right

  private def stripDot(s: String) =
    if (s endsWith ".0")
      s.substring(0, s.length - 2)
    else s
}
