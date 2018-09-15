package com.sg.scalaexamples

object Rational {
  implicit def intToRational(x: Int): Rational = new Rational(x)
}

class Rational(n: Int, d: Int) {
  require(d != 0)

  private val g = gcd(n.abs, d.abs)

  val numer: Int = n / g
  val denom: Int = d / g

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

  def <(that: Rational): Boolean =
    this.numer * that.denom < that.numer * this.denom

  def >(that: Rational): Boolean =
    this.numer * that.denom > that.numer * this.denom

  def max(that: Rational): Rational =
    if (this < that) that else this

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

