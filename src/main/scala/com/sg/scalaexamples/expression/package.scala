package com.sg.scalaexamples

package object expression {

  sealed abstract class Expr

  case class Var(name: String) extends Expr

  case class Number(num: Double) extends Expr

  case class UnOp(operator: String, arg: Expr) extends Expr

  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) =>
      simplifyAll(e) // Double negation
    case UnOp("abs", e @ UnOp("abs", _)) =>
      simplifyAll(e) // Double absolute
    case BinOp("+", e, Number(0)) =>
      simplifyAll(e) // Adding zero
    case BinOp("*", e, Number(1)) =>
      simplifyAll(e) // Multiplication by one
    case BinOp("+", x, y) if x == y =>
      BinOp("*", simplifyAll(x), Number(2)) // Doubling
    case UnOp(op, e) =>
      UnOp(op, simplifyAll(e))
    case BinOp(op, left, right) =>
      BinOp(op, simplifyAll(left), simplifyAll(right))
    case _ => expr
  }

  def describe(e: Expr): String = e match {
    case Number(_) => "a number"
    case Var(_) => "a variable"
    case UnOp(_, _) => "a unary operation"
    case BinOp(_, _, _) => "a binary operation"
  }

  val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
    )

  val precedence: Map[String, Int] = {
    val assocs =
      for {
        i <- opGroups.indices
        op <- opGroups(i)
      } yield op -> i

    assocs.toMap
  }

  val unaryPrecedence: Int = opGroups.length
  val fractionPrecedence: Int = -1
}
