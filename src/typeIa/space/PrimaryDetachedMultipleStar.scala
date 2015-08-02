package typeIa.space

import com.jme3.export.{JmeExporter, JmeImporter, Savable}
import typeIa.space.binaryStarType.Detatched

/**
 * This is where I break with astronomy terms: it's not really clear what star means. In this 'star' means a single
 * ball of glowing plasma, not a smudge of white in a telescope that could be a pair. As a result I use 'mutiple' to mean
 * more than one star very close, rather than a system with > 2 stars.
 *
 * @param orbitVector Gives the separation vector from a to b
 */
class PrimaryDetachedMultipleStar(val name: String, val galacticLoc: Locpc,
                                  val orbitVector: Locpc,
                                  e1: => BinaryElem,
                                  e2: => BinaryElem,
                                  val binaryStarType: Detatched) extends Binary with GalacticObject with Savable{
  lazy val a = e1
  lazy val b = e2
  val solarSystemLoc = new Locau(0, 0, 0)

  /**
   * How far these binary elems are
   */
  def getSeparationAu: Double = a.orbitRadiusInAu + b.orbitRadiusInAu

  override def write(ex: JmeExporter): Unit = ???

  override def read(im: JmeImporter): Unit = ???

  def tip: String = {
    name + " - Multiple system"
  }

  override def galacticLoc(time: Double): Locpc = galacticLoc
}
