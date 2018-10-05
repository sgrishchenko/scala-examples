package com.sg.scalaexamples.scells

import scala.swing._

object SCells extends SimpleSwingApplication {
  def top: MainFrame = new MainFrame {
    title = "SCells"
    contents = new Spreadsheet(height = 100, width = 100)
  }
}
