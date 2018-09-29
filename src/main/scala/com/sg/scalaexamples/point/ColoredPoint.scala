package com.sg.scalaexamples.point

class ColoredPoint(x: Int, y: Int, val color: Color.Value)
  extends Point(x, y) {

  override def canEqual(other: Any): Boolean = other.isInstanceOf[ColoredPoint]

  override def equals(other: Any): Boolean = other match {
    case that: ColoredPoint =>
      super.equals(that) &&
        (that canEqual this) &&
        color == that.color
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(super.hashCode(), color)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
