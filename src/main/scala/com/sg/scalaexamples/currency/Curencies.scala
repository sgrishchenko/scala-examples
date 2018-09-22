package com.sg.scalaexamples.currency

object US extends CurrencyZone {

  abstract class Dollar extends AbstractCurrency {
    def designation = "USD"
  }

  type Currency = Dollar

  def make(cents: Long): Dollar = new Dollar {
    val amount: Long = cents
  }

  val Cent: Dollar = make(1)
  val Dollar: Dollar = make(100)
  val CurrencyUnit: Dollar = Dollar
}

object Europe extends CurrencyZone {

  abstract class Euro extends AbstractCurrency {
    def designation = "EUR"
  }

  type Currency = Euro

  def make(cents: Long): Euro = new Euro {
    val amount: Long = cents
  }

  val Cent: Euro = make(1)
  val Euro: Euro = make(100)
  val CurrencyUnit: Euro = Euro
}

object Japan extends CurrencyZone {

  abstract class Yen extends AbstractCurrency {
    def designation = "JPY"
  }

  type Currency = Yen

  def make(yen: Long): Yen = new Yen {
    val amount: Long = yen
  }

  val Yen: Yen = make(1)
  val CurrencyUnit: Yen = Yen
}

