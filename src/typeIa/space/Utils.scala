package typeIa.space

import com.jme3.math.Vector3f

import scala.util.Random

/**
 * Created by tim on 14/04/15.
 */
object Utils {
  //from stack exchange:
  //math.stackexchange.com/questions/44689/how-to-find-a-random-axis-or-unit-vector-in-3d
  def randomUnitVector(r: Random): Vector3f = {
    //we want [0, 2Pi)
    val theta = r.nextDouble() * 2.0 * math.Pi
    //we want [-1, 1], which this doesn't quite give us
    val z = r.nextDouble() * 2.0 - 1
    val sqrtOneMinusZSquared = math.sqrt(1 - (z * z))
    new Vector3f((sqrtOneMinusZSquared * math.cos(theta)).toFloat, (sqrtOneMinusZSquared * math.sin(theta)).toFloat,
      z.toFloat)
  }
}
