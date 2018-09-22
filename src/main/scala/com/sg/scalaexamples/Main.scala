package com.sg.scalaexamples

import com.sg.scalaexamples.currency._

object Main extends App {
  val balance = US.Dollar * 100
  println(balance)

  val tmp1 = Japan.Yen from balance
  println(tmp1)

  val tmp2 = Europe.Euro from tmp1
  println(tmp2)

  val tmp3 = US.Dollar from tmp2
  println(tmp3)

}

