package typeIa.maths

import scala.util.Random

/**
  * Created by tim on 07/05/16.
  */
object Distributions {
  /**
    *
    * @param r is a [0-1] uniform random number
    * @param location
    * @param scale
    * @return
    */
  def gumbel(r: Double, location: Double, scale: Double): Double = {
    location - scale * math.log( -math.log(r))
  }
}
