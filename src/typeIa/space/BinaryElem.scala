package typeIa.space

import com.jme3.math.ColorRGBA

/**
 * One half of a binary. Orbit radius is how far it is from the central point.
 * 
 */
trait BinaryElem extends LocatedObject{
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
  def loc(time: Double): Loc

  /**
   * In solar radii
   */
  def getRadius: Double
}
