/*
Stub.scala
Stub for Scala Breeze code
*/

import fns.Fns._
import scala.math._

object Main extends App {
  /*
   * Define a function f (in one variable)
   * and calculate the area under the curve between
   * points lb and ub
   * */

  def f(x: Double): Double = {
    math.exp(-x * x / 2) / math.sqrt(2 * Pi)
  }

  val lb = 0.0
  val ub = 5.0

  println("starting MC integration")

  val area = mc_integrate(fn=f, nsamp=1000, lower=lb, upper=ub)

  println(area)

}
