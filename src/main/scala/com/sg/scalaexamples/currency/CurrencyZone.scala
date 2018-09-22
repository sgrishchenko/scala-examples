package com.sg.scalaexamples.currency

abstract class CurrencyZone {
  type Currency <: AbstractCurrency

  def make(x: Long): Currency

  val CurrencyUnit: Currency

  abstract class AbstractCurrency {
    val amount: Long

    def designation: String

    override def toString: String = {
      val format = s"%.${decimals(CurrencyUnit.amount)}f $designation"
      val result = amount.toDouble / CurrencyUnit.amount.toDouble

      result formatted format
    }

    private def decimals(n: Long): Int =
      if (n == 1)
        0
      else
        1 + decimals(n / 10)

    def +(that: Currency): Currency =
      make(this.amount + that.amount)

    def *(x: Double): Currency =
      make((this.amount * x).toLong)

    def -(that: Currency): Currency =
      make(this.amount - that.amount)

    def /(that: Double): Currency =
      make((this.amount / that).toLong)

    def /(that: Currency): Double =
      this.amount.toDouble / that.amount

    def from(other: CurrencyZone#AbstractCurrency): Currency = {
      val rate = Converter.exchangeRate(other.designation)(this.designation)
      make(math.round(other.amount.toDouble * rate))
    }
  }

}
