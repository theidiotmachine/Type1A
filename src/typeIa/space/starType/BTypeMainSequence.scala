package typeIa.space.starType

import com.jme3.math.ColorRGBA
import typeIa.maths.units.Units.DegreesKelvin

/**
 * B type main sequence star
 */
object BTypeMainSequence extends MainSequence{
  /**
   * The likelihood of a star being this type.
   *
   * wiki says About 1 in 800 (0.125%) of the main-sequence stars in the solar neighborhood are class B stars
   */
  override def loneStarProbability: Double = 0.00125 * StarType.loneMainSequenceFraction

  /**
   * The largest diameter this star type can be have. In solar radii
   */
  override def radiusMax: Double = 6.6

  /**
   * Here 'min' means for the lowest mass star of this class.
   *
   * 'typical' is 0xaabfff
   */
  override def colorMin: ColorRGBA = StarType.genColor(0xba, 0xcb, 0xff)

  /**
   * The highest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMax: DegreesKelvin = new DegreesKelvin(30000)

  /**
   * In multiple of sol luminosity. This is tricky. Table here
   * http://www.enchantedlearning.com/subjects/astronomy/stars/startypes.shtml
   * says typical is 20000: wiki gives 30000
   */
  override def luminosityMax: Double = 30000

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 16

  /**
   * The smallest diameter this star type can have. In solar diameters
   */
  override def radiusMin: Double = 1.8

  /**
   * In multiple of sol luminosity
   *
   * From https://en.wikipedia.org/wiki/Stellar_classification. Typical is 20000
   */
  override def luminosityMin: Double = 25

  /**
   * The lowest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMin: DegreesKelvin = new DegreesKelvin(10000)

  /**
   * The smallest mass this star type can be. In solar masses
   */
  override def massMin: Double = 2.1

  /**
   * Here 'max' means for the highest mass star of this class.
   *
   * 'typical' is 0xaabfff
   */
  override def colorMax: ColorRGBA = StarType.genColor(0xa3, 0xb8, 0xff)

  override def name: String = "type B main sequence star"
}
