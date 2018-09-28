package com.sg.scalaexamples.recipe.base

trait Foods {
  def allFoods: List[Food]

  def foodNamed(name: String): Option[Food] =
    allFoods.find(f => f.name == name)
}
