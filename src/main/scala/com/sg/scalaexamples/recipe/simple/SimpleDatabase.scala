package com.sg.scalaexamples.recipe.simple

import com.sg.scalaexamples.recipe.base.Database

object SimpleDatabase
  extends Database
  with SimpleFoods
  with SimpleFoodCategories
  with SimpleRecipes
