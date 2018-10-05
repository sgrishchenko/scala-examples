package com.sg.scalaexamples.scells

import swing._
import event.Event

class Model(val height: Int, val width: Int) extends Evaluator with Arithmetic {
  case class ValueChanged(cell: Cell) extends Event

  case class Cell(row: Int, column: Int) extends Publisher {
    private var f: Formula = Empty

    def formula: Formula = f

    def formula_=(f: Formula): Unit = {
      if (this.f != f) {
        for (c <- references(formula)) deafTo(c)
        this.f = f
        for (c <- references(formula)) listenTo(c)
        value = evaluate(f)
      }
    }

    private var v: Double = Double.NaN

    def value: Double = v

    def value_=(w: Double): Unit = {
      if (!(v == w || v.isNaN && w.isNaN)) {
        v = w
        publish(ValueChanged(this))
      }
    }

    reactions += {
      case ValueChanged(_) => value = evaluate(formula)
    }

    override def toString: String = formula match {
      case Textual(s) => s
      case _ => value.toString
    }
  }

  val cells: Array[Array[Cell]] = Array.ofDim[Cell](height, width)

  for {
    i <- 0 until height
    j <- 0 until width
  } cells(i)(j) = Cell(i, j)
}

