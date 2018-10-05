package com.sg.scalaexamples.scells

import scala.math.pow

object IndexConverters {
  private def charToInt(c: Char) = c.toUpper - 'A'

  private def intToChar(i: Int) = ('A' + i).toChar.toString

  private val base = charToInt('Z') + 1

  def intToLetters(number: Int): String = {

    val result = number / base
    val remainder = number % base

    val leading = if (result > 0)
      intToLetters(result - 1)
    else ""

    leading + intToChar(remainder)
  }

  def lettersToInt(string: String): Int = {
    val reversed = string.reverse
    (0 /: reversed.indices) {
      (result, index) =>
        {
          val digit = charToInt(reversed.charAt(index)) + 1
          result + digit * pow(base, index).toInt
        }
    } - 1
  }
}
