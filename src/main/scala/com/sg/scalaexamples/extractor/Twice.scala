package com.sg.scalaexamples.extractor

object Twice {
  def apply(s: String): String = s + s

  def unapply(s: String): Option[String] = {
    val length = s.length / 2
    val left = s.substring(0, length)
    val right = s.substring(length)

    if (left == right)
      Some(right)
    else
      None
  }
}

