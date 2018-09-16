package com.sg.scalaexamples

import com.sg.scalaexamples.intqueue.BasicIntQueue
import com.sg.scalaexamples.intqueue.Doubling
import com.sg.scalaexamples.intqueue.Incrementing

object Main extends App {
  val queue = new BasicIntQueue
    with Incrementing
    with Doubling

  queue.put(10)
  queue.put(20)

  println(queue.get())
  println(queue.get())

}

