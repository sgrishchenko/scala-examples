package com.sg.scalaexamples.recipe.student

import com.sg.scalaexamples.recipe.base.{ Database, Food, Recipe }

object StudentDatabase extends Database {

  object FrozenFood extends Food("FrozenFood")

  object HeatItUp extends Recipe(
    "heat it up",
    List(FrozenFood),
    "Microwave the 'food' for 10 minutes."
  )

  def allFoods = List(FrozenFood)

  def allRecipes = List(HeatItUp)

  def allCategories = List(
    FoodCategory("edible", List(FrozenFood))
  )
}
