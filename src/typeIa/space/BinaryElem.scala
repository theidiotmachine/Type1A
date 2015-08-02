package typeIa.space

import com.jme3.math.ColorRGBA

/**
 * One half of a binary. Orbit radius is how far it is from the central point.
 * 
 * It's a galactic object, even though it may not be an actual object, or visible always, because
 * at some zoom it can be represented by a sprite
 */
trait BinaryElem extends GalacticObject{
  val orbitRadiusInAu: Double

  /**
   * The color for a sprite
   */
  def getColor: ColorRGBA

  /**
   * In solar luminosities
   */
  def getLuminosity: Double

  /**
   *
   */
  def galacticLoc(time: Double): Locpc

  /**
   * In solar radii
   */
  def getRadius: Double
}
