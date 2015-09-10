package typeIa.space.starType

import com.jme3.math.ColorRGBA

/**
 * F type main sequence star
 */
object FTypeMainSequence extends MainSequence{
  /**
   * The likelihood of a star being this type.
   */
  override def loneStarProbability: Double = 0.03 * StarType.loneMainSequenceFraction

  /**
   * The largest diameter this star type can be have. In solar diameters
   */
  override def radiusMax: Double = 1.4

  /**
   * Here 'min' means for the lowest mass star of this class
   *
   * typical is f8f7ff.
   */
  override def colorMin: ColorRGBA = StarType.genColor(0xf1, 0xf5, 0xf5)

  /**
   * The highest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMax: Double = 7500

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMax: Double = 5

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 1.4

  /**
   * The smallest diameter this star type can have. In solar diameters
   */
  override def radiusMin: Double = 1.15

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMin: Double = 1.5

  /**
   * The lowest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMin: Double = 6000

  /**
   * The smallest mass this star type can be. In solar masses
   */
  override def massMin: Double = 1.04

  /**
   * Here 'max' means for the highest mass star of this class.
   */
  override def colorMax: ColorRGBA = StarType.genColor(0xe1, 0xe7, 0xff)

  override def name: String = "type F main sequence star"
}
