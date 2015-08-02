package typeIa.space

import com.jme3.math.ColorRGBA

/**
 * A binary is a pair of objects that orbit each other. They can have satellites, or by satellites themselves.
 */
trait Binary{
  val a: BinaryElem
  val b: BinaryElem

  /**
   * If this is far enough away to see as a single star, what color is it?
   */
  def getMergedColor: ColorRGBA = {
    val color1 = a.getColor
    val color2 = b.getColor
    val out = new ColorRGBA()
    val lum1 = a.getLuminosity
    val lum2 = b.getLuminosity
    val totLum = lum1 + lum2
    val amt = lum2 / totLum
    out.interpolate(color1, color2, amt.toFloat)
    out
  }

  /**
   * If this is far enough away to see as a single star, how bright is it? In solar luminosities
   */
  def getMergedLuminosity: Double = math.max(a.getLuminosity, b.getLuminosity)

  def getMergedRadius: Double = math.max(a.getRadius, b.getRadius)
}
