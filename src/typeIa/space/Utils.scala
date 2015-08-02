package typeIa.space

import scala.util.Random

/**
 * Created by tim on 14/04/15.
 */
object Utils {
  def auTopc(au: Double): Double = {
    au / 206264.806
  }

  def pcToau(pc: Double): Double = pc * 206264.806

  //from stack exchange:
  //math.stackexchange.com/questions/44689/how-to-find-a-random-axis-or-unit-vector-in-3d
  def randomUnitLocpc(r: Random): Locpc = {
    //we want [0, 2Pi)
    val theta = r.nextDouble() * 2.0 * math.Pi
    //we want [-1, 1], which this doesn't quite give us
    val z = r.nextDouble() * 2.0 - 1
    val sqrtOneMinusZSquared = math.sqrt(1 - (z * z))
    new Locpc(sqrtOneMinusZSquared * math.cos(theta), sqrtOneMinusZSquared * math.sin(theta), z)
  }
}
