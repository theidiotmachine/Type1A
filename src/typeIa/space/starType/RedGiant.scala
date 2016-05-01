package typeIa.space.starType

import com.jme3.math.ColorRGBA
import typeIa.maths.units.Units.DegreesKelvin

/**
 * Red giants. Data from
 *
 * m.space.com/22471-red-giant-stars.html
 *
 * and the wiki Red Giant article
 */
object RedGiant extends StarType{
  /**
   * The likelihood of a star being this type when alone or in a distant binary. I totally made this up; although there
   * are no red giants in the 100 closest stars, so it is probably less than 1%?
   */
  override def loneStarProbability: Double = 0.002

  /**
   * The largest radius this star type can be have. In solar radii
   *
   * from
   */
  override def radiusMax: Double = 1000

  /**
   * Here 'min' means for the lowest mass star of this class.
   */
  override def colorMin: ColorRGBA = StarType.genColor(0xff, 0xc9, 0x56)

  /**
   * The highest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMax: DegreesKelvin = new DegreesKelvin(4000)

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMax: Double = 500 //'several hundred'

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 8

  override def name: String = "red giant"

  /**
   * The smallest radius this star type can have. In solar radii
   */
  override def radiusMin: Double = 100

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMin: Double = 100

  /**
   * The lowest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMin: DegreesKelvin = new DegreesKelvin(3000)

  /**
   * The smallest mass this star type can be. In solar masses
   */
  override def massMin: Double = 0.3

  /**
   * Here 'max' means for the highest mass star of this class.
   */
  override def colorMax: ColorRGBA = StarType.genColor(0xff, 0xe3, 0xc6)
}
