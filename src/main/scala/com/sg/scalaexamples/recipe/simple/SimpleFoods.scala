package com.sg.scalaexamples.recipe.simple

import com.sg.scalaexamples.recipe.base.{ Food, Foods }

trait SimpleFoods extends Foods {

  object Apple extends Food("Apple")

  object Orange extends Food("Orange")

  object Cream extends Food("Cream")

  object Sugar extends Food("Sugar")

  def allFoods = List(Apple, Orange, Cream, Sugar)
}
