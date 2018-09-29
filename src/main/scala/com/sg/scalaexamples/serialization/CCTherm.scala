package com.sg.scalaexamples.serialization

import scala.xml.Elem

abstract class CCTherm {
  val description: String
  val yearMade: Int
  val dateObtained: String
  val bookPrice: Int
  val purchasePrice: Int
  val condition: Int

  override def toString: String = description

  def toXML: Elem =
    <cctherm>
      <description>
        { description }
      </description>
      <yearMade>
        { yearMade }
      </yearMade>
      <dateObtained>
        { dateObtained }
      </dateObtained>
      <bookPrice>
        { bookPrice }
      </bookPrice>
      <purchasePrice>
        { purchasePrice }
      </purchasePrice>
      <condition>
        { condition }
      </condition>
    </cctherm>
}

object CCTherm {
  def fromXML(node: scala.xml.Node): CCTherm =
    new CCTherm {
      val description: String =
        (node \ "description").text.trim
      val yearMade: Int =
        (node \ "yearMade").text.trim.toInt
      val dateObtained: String =
        (node \ "dateObtained").text.trim
      val bookPrice: Int =
        (node \ "bookPrice").text.trim.toInt
      val purchasePrice: Int =
        (node \ "purchasePrice").text.trim.toInt
      val condition: Int =
        (node \ "condition").text.trim.toInt
    }
}
