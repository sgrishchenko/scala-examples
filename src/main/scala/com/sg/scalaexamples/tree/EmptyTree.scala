package com.sg.scalaexamples.tree

object EmptyTree extends Tree[Nothing] {
  def elem = throw new NoSuchElementException("EmptyTree.elem")

  def left = throw new NoSuchElementException("EmptyTree.left")

  def right = throw new NoSuchElementException("EmptyTree.right")
}
