/*
 * https://github.com/darrenjw/scala-course
 * */

package fns

import breeze.linalg._
import breeze.stats.distributions.Uniform

object Fns {

  def mc_integrate(fn: Double => Double, nsamp: Int, lower: Double, upper: Double): Double = {
    val x = Uniform(lower, upper).sample(nsamp)

    // evaluate the function at each randomly generated x value
    val f_x = x.map(fn)

    val mean_value = sum(f_x) / f_x.length

    (upper - lower) * mean_value

  }

}
