package typeIa.space.starType

import com.jme3.math.ColorRGBA

/**
 * K type main sequence star
 */
object KTypeMainSequence extends MainSequence{
  /**
   * The likelihood of a star being this type.
   */
  override def loneStarProbability: Double = 0.121 * StarType.loneMainSequenceFraction

  /**
   * The largest radius this star type can be have. In solar radii
   */
  override def radiusMax: Double = 0.96

  /**
   * Here 'min' means for the lowest mass star of this class.
   *
   * typical is 0xffd2a1
   */
  override def colorMin: ColorRGBA = StarType.genColor(0xff, 0xcf, 0x88)

  /**
   * The highest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMax: Double = 5200

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMax: Double = 0.6

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 0.8

  /**
   * The smallest radius this star type can have. In solar radii
   */
  override def radiusMin: Double = 0.7

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMin: Double = 0.08

  /**
   * The lowest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMin: Double = 3700

  /**
   * The smallest mass this star type can be. In solar masses
   */
  override def massMin: Double = 0.45

  /**
   * Here 'max' means for the highest mass star of this class.
   */
  override def colorMax: ColorRGBA = StarType.genColor(0xff, 0xe3, 0xc6)

  override def name: String = "orange dwarf (type K main sequence star)"
}
