package com.sg.scalaexamples.rational
import scala.language.implicitConversions

object Rational {
  implicit def intToRational(x: Int): Rational = new Rational(x)
}

class Rational(n: Int, d: Int) extends Ordered[Rational] {
  require(d != 0)

  private val g = gcd(n.abs, d.abs)

  val numer: Int = (if (d < 0) -n else n) / g
  val denom: Int = d.abs / g

  def this(n: Int) = this(n, 1)

  override def toString: String = s"$numer/$denom"

  def +(that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  def +(that: Int): Rational = this + new Rational(that)

  def *(that: Rational): Rational =
    new Rational(
      this.numer * that.numer,
      this.denom * that.denom
    )

  def *(that: Int): Rational = this * new Rational(that)

  def /(that: Rational): Rational =
    new Rational(
      this.numer * that.denom,
      this.denom * that.numer
    )

  def /(that: Int): Rational = this / new Rational(that)

  def max(that: Rational): Rational =
    if (this < that) that else this

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def compare(that: Rational): Int =
    (this.numer * that.denom) - (that.numer * this.denom)

  def canEqual(other: Any): Boolean = other.isInstanceOf[Rational]

  override def equals(other: Any): Boolean = other match {
    case that: Rational =>
      (that canEqual this) &&
        numer == that.numer &&
        denom == that.denom
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(numer, denom)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

