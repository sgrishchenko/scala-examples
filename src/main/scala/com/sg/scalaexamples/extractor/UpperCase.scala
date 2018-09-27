package com.sg.scalaexamples.extractor

object UpperCase {
  def unapply(s: String): Boolean =
    s.toUpperCase == s
}

