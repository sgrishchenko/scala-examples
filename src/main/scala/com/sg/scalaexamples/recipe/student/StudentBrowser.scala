package com.sg.scalaexamples.recipe.student

import com.sg.scalaexamples.recipe.base.{Browser, Database}

object StudentBrowser extends Browser {
  val database: Database = StudentDatabase
}