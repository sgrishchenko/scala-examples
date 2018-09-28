package com.sg.scalaexamples.recipe.simple

import com.sg.scalaexamples.recipe.base.FoodCategories

trait SimpleFoodCategories extends FoodCategories {
  this: SimpleFoods =>

  def allCategories: List[FoodCategory] = List(
    FoodCategory("fruits", List(Apple, Orange)),
    FoodCategory("misc", List(Cream, Sugar)),
  )
}
