package typeIa.space

import com.jme3.math.Vector3f

import scala.util.Random

/**
 * Space location. A parsec offset from origin, with an astronomical unit offset from that.
 *
 * A parsec is 3.26ly, or 31 trillion km, or 2.0626*10 exp 5 au. 206264.806 au
 * Proxima centauri is about 1.3 parsecs from the sun: the sun is about 8000 pc from the centre of the milky way;
 * which has a diamater of 34000pc
 *
 * 1au is 150 million km. The sun is 0.0046au in radius, Earth has radius 4.26 * 10 exp -5au, and is
 * 1au from the sun.
 *
 * Origin is game origin, the centre of the neighborhood.
 *
 * FIXME: figure out how to normalize
 */
/*
class Loc(val xpc: Double, val ypc: Double, val zpc: Double,
          val xau: Double = 0, val yau: Double = 0, val zau: Double = 0) {
  def separation(r: Loc): (Double, Double) = {
    val sepxpc = xpc - r.xpc
    val sepypc = ypc - r.ypc
    val sepzpc = zpc - r.zpc
    //val seppc = math.sqrt(sepxpc * sepxpc + sepypc * sepypc + sepzpc * sepzpc)
    /*
    v = a + e
    len v = √((a+e).x² + (a+e).y² + (a+e).z²)
          = √(a.x² + a.x*e.x + e.x² + a.y² + a.y*e.y + e.y² + a.z² + a.z*e.z + e.z²)

     */
  }
}

object Loc {
  def apply(xpc: Double, ypc: Double, zpc: Double): Loc = new Loc(xpc, ypc, zpc)
}*/

/**
 * Space location. m from the origin - the neighborhood centre..
 *
 * A parsec is 3.26ly, or 31 trillion km, or 2.0626*10 exp 5 au. 206264.806 au
 * Proxima centauri is about 1.3 parsecs from the sun: the sun is about 8000 pc from the centre of the milky way;
 * which has a diamater of 34000pc
 *
 * 1au is 150 million km. The sun is 0.0046au in radius, Earth has radius 4.26 * 10 exp -5au, and is
 * 1au from the sun.
 *
 * Origin is game origin, the centre of the neighborhood.
 *
 * FIXME: figure out how to normalize
 */
case class Loc(x: Long, y: Long, z: Long){
  def -(r: Loc): Loc = Loc(x - r.x, y - r.y, z - r.z)

  def +(r: Loc): Loc = Loc(x + r.x, y + r.y, z + r.z)

  /**
   * Multiply by a scalar. Will overflow if the double is too large
   * @param r scalar
   * @return a new loc
   */
  def *(r: Double): Loc = Loc((x * r).toLong, (y * r).toLong, (z * r).toLong)

  def pluspc(rx: Double, ry: Double, rz: Double): Loc =
    Loc(x + (rx * Loc.ParsecsInMetres).toLong,
      (y + ry * Loc.ParsecsInMetres).toLong, (z + rz * Loc.ParsecsInMetres).toLong)

  def lengthpc: Double = {
    //Idea here is to get the values nice and small before we do the square. Not sure how accurate it will be
    val xpc = x * Loc.MetresInParsecs
    val ypc = y * Loc.MetresInParsecs
    val zpc = z * Loc.MetresInParsecs
    Math.sqrt(xpc*xpc + ypc*ypc + zpc*zpc)
  }
}

object Loc{
  val ParsecsInMetres: Double = 3.08567758e16d
  val MetresInParsecs: Double = 1.0/ParsecsInMetres
  val AstronomicalUnitsInMetres: Double = 149597870700.0
  val MetresInAstronomicalUnits: Double = 1.0/AstronomicalUnitsInMetres
  def pc(x: Double, y: Double, z: Double): Loc =
    Loc((x * ParsecsInMetres).toLong, (y * ParsecsInMetres).toLong, (z * ParsecsInMetres).toLong)
  def au(x: Double, y: Double, z: Double): Loc =
    Loc((x * AstronomicalUnitsInMetres).toLong, (y * AstronomicalUnitsInMetres).toLong, (z * AstronomicalUnitsInMetres).toLong)

  def auTopc(au: Double): Double = au / 206264.806

  def pcToau(pc: Double): Double = pc * 206264.806

  //from stack exchange:
  //math.stackexchange.com/questions/44689/how-to-find-a-random-axis-or-unit-vector-in-3d
  def randomUnitLocpc(r: Random): Loc = {
    //we want [0, 2Pi)
    val theta = r.nextDouble() * 2.0 * math.Pi
    //we want [-1, 1], which this doesn't quite give us
    val z = r.nextDouble() * 2.0 - 1
    val sqrtOneMinusZSquared = math.sqrt(1 - (z * z))
    Loc.pc(sqrtOneMinusZSquared * math.cos(theta), sqrtOneMinusZSquared * math.sin(theta), z)
  }

  def randomLocCubepc(r: Random, pc: Double): Loc =
    Loc.pc((r.nextDouble() - 0.5) * pc, (r.nextDouble() - 0.5) * pc,
      (r.nextDouble() - 0.5) * pc)

  def auTom(au: Double): Long = (au * AstronomicalUnitsInMetres).toLong

  def pcTom(pc: Double): Long = (pc * ParsecsInMetres).toLong

  def mToau(m: Double): Double = m * MetresInAstronomicalUnits

  def mToau(m: Long): Double = m.toDouble * MetresInAstronomicalUnits

  def unitAndauLength(unit: Vector3f, aulength: Double): Loc = {
    Loc.au(aulength * unit.x, aulength * unit.y, aulength * unit.z)
  }
}