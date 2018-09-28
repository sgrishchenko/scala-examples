package com.sg.scalaexamples.recipe.simple

import com.sg.scalaexamples.recipe.base.{Recipe, Recipes}

trait SimpleRecipes extends Recipes {
  this: SimpleFoods =>

  object FruitSalad extends Recipe(
    "fruit salad",
    List(Apple, Orange, Cream, Sugar),
    "Stir it all together."
  )

  def allRecipes: List[Recipe] = List(FruitSalad)
}
