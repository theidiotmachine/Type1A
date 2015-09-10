package typeIa.space

import com.jme3.math.ColorRGBA

/**
 * A binary in a multiple star system
 */
class MultipleSubBinary(val name: String, val a: BinaryElem, val b: BinaryElem, val orbitRadiusInAu: Double) extends BinaryElem with Binary{
  /**
   * The color for a sprite
   */
  override def getColor: ColorRGBA = getMergedColor

  /**
   * In solar luminosities
   */
  override def getLuminosity: Double = getMergedLuminosity

  /**
   * For rendering. Not sure about this
   */
  override def loc(time: Double): Loc = ???

  /**
   * In solar radii
   */
  override def getRadius: Double = getMergedRadius

  val tip: String = name
}