package com.sg.scalaexamples.simulation

object Main extends App {

  object MySimulation extends CircuitSimulation {
    def InverterDelay = 1

    def AndGateDelay = 3

    def OrGateDelay = 5
  }

  import MySimulation._

  val input1, input2, sum, carry = new Wire

  probe("sum", sum)
  probe("carry", carry)

  halfAdder(input1, input2, sum, carry)

  input1 setSignal true
  run()

  input2 setSignal true
  run()
}

