package com.sg.scalaexamples.scells

import scala.swing._
import event._

class Spreadsheet(val height: Int, val width: Int) extends ScrollPane {
  val borderColor = new java.awt.Color(150, 150, 150)

  val cellModel = new Model(height, width)
  import cellModel._

  val table: Table =
    new Table(height, width) {
      rowHeight = 25
      autoResizeMode = Table.AutoResizeMode.Off
      showGrid = true
      gridColor = borderColor

      override def rendererComponent(
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
      ): Component =
        if (hasFocus) new TextField(userData(row, column))
        else
          new Label(cells(row)(column).toString) {
            xAlignment = Alignment.Right
          }

      def userData(row: Int, column: Int): String = {
        val v = this(row, column)
        if (v == null) "" else v.toString
      }

      reactions += {
        case TableUpdated(_, rows, column) =>
          for (row <- rows)
            cells(row)(column).formula =
              FormulaParsers.parse(userData(row, column))
        case ValueChanged(cell) =>
          updateCell(cell.row, cell.column)
      }

      for {
        row <- cells
        cell <- row
      } listenTo(cell)
    }

  val rowHeader: ListView[String] =
    new ListView((0 until height) map (_.toString)) {
      fixedCellWidth = 30
      fixedCellHeight = table.rowHeight
      border = Swing.LineBorder(borderColor)
    }

  viewportView = table
  rowHeaderView = rowHeader
}
