package typeIa.space.starType

import com.jme3.math.ColorRGBA

/**
 * A type main sequence star.
 */
object ATypeMainSequence extends StarType{
  /**
   * The likelihood of a star being this type.
   *
   * wiki sez About 1 in 160 (0.625%) of the main-sequence stars in the solar neighborhood are class A stars
   */
  override def loneStarProbability: Double = 0.00625 * StarType.loneMainSequenceFraction

  /**
   * The largest diameter this star type can be have. In solar diameters
   */
  override def radiusMax: Double = 1.8

  /**
   * Here 'min' means for the lowest mass star of this class.
   */
  override val colorMin: ColorRGBA = StarType.genColor(0xe1, 0xe7, 0xff)

  /**
   * The highest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMax: Double = 7500

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMax: Double = 25

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 2.1

  /**
   * The smallest diameter this star type can have. In solar diameters
   */
  override def radiusMin: Double = 1.4

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMin: Double = 5

  /**
   * The lowest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMin: Double = 7500

  /**
   * The smallest mass this star type can be. In solar masses
   */
  override def massMin: Double = 1.4

  /**
   * Here 'max' means for the highest mass star of this class.
   *
   * Typical is 0xcad7ff
   */
  override val colorMax: ColorRGBA = StarType.genColor(0xba, 0xcb, 0xff)

  override def name: String = "type A main sequence star"
}
