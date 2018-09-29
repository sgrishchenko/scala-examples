package com.sg.scalaexamples.tree

trait Tree[+T] {
  def elem: T
  def left: Tree[T]
  def right: Tree[T]
}
