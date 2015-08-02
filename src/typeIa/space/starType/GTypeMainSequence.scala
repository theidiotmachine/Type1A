package typeIa.space.starType

import com.jme3.math.ColorRGBA

/**
 * G type main sequence
 */
object GTypeMainSequence extends StarType{
  /**
   * The likelihood of a star being this type.
   */
  override def loneStarProbability: Double = 0.076 * StarType.loneMainSequenceFraction

  /**
   * The largest radius this star type can be have. In solar radii
   */
  override def radiusMax: Double = 1.15

  /**
   * Here 'min' means for the lowest mass star of this class.
   *
   * 0xfff4ea
   */
  override def colorMin: ColorRGBA = StarType.genColor(0xff, 0xe3, 0xc6)

  /**
   * The highest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMax: Double = 6000

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMax: Double = 1.5

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 1.04

  /**
   * The smallest radius this star type can have. In solar radii
   */
  override def radiusMin: Double = 0.96

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMin: Double = 0.6

  /**
   * The lowest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMin: Double = 5200

  /**
   * The smallest mass this star type can be. In solar masses
   */
  override def massMin: Double = 0.8

  /**
   * Here 'max' means for the highest mass star of this class.
   *
   * typical is 0xfff4ea
   */
  override def colorMax: ColorRGBA = StarType.genColor(0xf1, 0xf5, 0xf5)

  override def name: String = "type G star"
}
