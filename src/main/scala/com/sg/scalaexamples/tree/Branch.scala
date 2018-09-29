package com.sg.scalaexamples.tree

class Branch[+T](
  val elem: T,
  val left: Tree[T],
  val right: Tree[T]
) extends Tree[T] {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Branch[_]]

  override def equals(other: Any): Boolean = other match {
    case that: Branch[_] =>
      (that canEqual this) &&
        elem == that.elem &&
        left == that.left &&
        right == that.right
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(elem, left, right)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
