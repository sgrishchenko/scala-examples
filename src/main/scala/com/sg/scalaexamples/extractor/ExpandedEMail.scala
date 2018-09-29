package com.sg.scalaexamples.extractor

object ExpandedEMail {
  def unapplySeq(email: String): Option[(String, Seq[String])] = {
    try {
      val EMail(user, domain) = email
      val Domain(parts @ _*) = domain

      Some(user, parts)
    } catch {
      case _: MatchError => None
    }
  }
}

