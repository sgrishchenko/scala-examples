package com.sg.scalaexamples.patricia

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable
import scala.collection.immutable

class PrefixMap[T]
  extends mutable.Map[String, T]
  with mutable.MapLike[String, T, PrefixMap[T]] {

  var suffixes: immutable.Map[Char, PrefixMap[T]] = Map.empty
  var value: Option[T] = None

  def get(s: String): Option[T] =
    if (s.isEmpty) value
    else suffixes
      .get(s(0))
      .flatMap(_.get(s substring 1))

  def withPrefix(s: String): PrefixMap[T] =
    if (s.isEmpty) this
    else {
      val leading = s(0)
      suffixes get leading match {
        case None =>
          suffixes = suffixes + (leading -> empty)
        case _ =>
      }
      suffixes(leading) withPrefix (s substring 1)
    }

  override def update(s: String, elem: T): Unit =
    withPrefix(s).value = Some(elem)

  override def remove(s: String): Option[T] =
    if (s.isEmpty) {
      val prev = value
      value = None
      prev
    } else suffixes
      .get(s(0))
      .flatMap(_.remove(s substring 1))

  def iterator: Iterator[(String, T)] = {
    val head = for (v <- value.iterator)
      yield ("", v)

    val tail = for {
      (chr, m) <- suffixes.iterator
      (s, v) <- m.iterator
    } yield (chr +: s, v)

    head ++ tail
  }

  def +=(pair: (String, T)): this.type = {
    update(pair._1, pair._2)
    this
  }

  def -=(s: String): this.type = {
    remove(s)
    this
  }

  override def empty: PrefixMap[T] = PrefixMap.empty
}

object PrefixMap {
  def empty[T] = new PrefixMap[T]

  def apply[T](pairs: (String, T)*): PrefixMap[T] = {
    val map: PrefixMap[T] = empty
    for (pair <- pairs) map += pair
    map
  }

  def newBuilder[T]: mutable.Builder[(String, T), PrefixMap[T]] =
    new mutable.MapBuilder[String, T, PrefixMap[T]](empty)

  implicit def canBuildFrom[T]: CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] =
    new CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] {
      def apply(from: PrefixMap[_]): mutable.Builder[(String, T), PrefixMap[T]] = newBuilder[T]

      def apply(): mutable.Builder[(String, T), PrefixMap[T]] = newBuilder[T]
    }

}
