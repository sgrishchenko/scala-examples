package com.sg.scalaexamples.recipe.simple

import com.sg.scalaexamples.recipe.base.{ Browser, Database }

object SimpleBrowser extends Browser {
  override val database: Database = SimpleDatabase
}

