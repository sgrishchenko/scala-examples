package com.sg.scalaexamples.rna

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable.ArrayBuffer
import scala.collection.{IndexedSeqLike, mutable}

final class RnaSeq private(
  val groups: Array[Int],
  val length: Int
) extends IndexedSeq[Base] with IndexedSeqLike[Base, RnaSeq] {

  import RnaSeq._

  override def newBuilder: mutable.Builder[Base, RnaSeq] = RnaSeq.newBuilder

  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)
  }

  override def foreach[U](f: Base => U): Unit = {
    var i = 0
    var b = 0
    while (i < length) {
      b = if (i % N == 0) groups(i / N) else b >>> S
      f(Base.fromInt(b & M))
      i += 1
    }
  }
}

object RnaSeq {
  // Количество разрядов, необходимое для представления группы
  private val S = 2
  // Количество групп, помещающихся в Int-значение
  private val N = 32 / S
  // Битовая маска для изоляции группы
  private val M = (1 << S) - 1

  def fromSeq(buf: Seq[Base]): RnaSeq = {
    val length = (buf.length + N - 1) / N
    val groups = new Array[Int](length)

    for (i <- buf.indices)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)

    new RnaSeq(groups, buf.length)
  }

  def apply(bases: Base*): RnaSeq = fromSeq(bases)

  def unapplySeq(arg: RnaSeq): Option[Seq[Base]] = Some(arg)

  def newBuilder: mutable.Builder[Base, RnaSeq] =
    new ArrayBuffer[Base] mapResult fromSeq

  implicit def canBuildFrom: CanBuildFrom[RnaSeq, Base, RnaSeq] =
    new CanBuildFrom[RnaSeq, Base, RnaSeq] {
      def apply(): mutable.Builder[Base, RnaSeq] = newBuilder

      def apply(from: RnaSeq): mutable.Builder[Base, RnaSeq] = newBuilder
    }

}
