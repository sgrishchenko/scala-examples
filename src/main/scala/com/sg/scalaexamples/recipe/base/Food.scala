package com.sg.scalaexamples.recipe.base

abstract class Food(val name: String) {
  override def toString: String = name
}

