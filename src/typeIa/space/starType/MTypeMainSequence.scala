package typeIa.space.starType

import com.jme3.math.ColorRGBA

/**
 * M type main sequence star
 */
object MTypeMainSequence extends MainSequence{
  /**
   * The likelihood of a star being this type.
   */
  override def loneStarProbability: Double = 0.7645 * StarType.loneMainSequenceFraction

  /**
   * The largest radius this star type can be have. In solar radii
   */
  override def radiusMax: Double = 0.7

  /**
   * Here 'min' means for the lowest mass star of this class.
   *
   * typical is ffcc6f
   */
  override def colorMin: ColorRGBA = StarType.genColor(0xff, 0xc9, 0x56)

  /**
   * The highest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMax: Double = 3700

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMax: Double = 0.08

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 0.45

  /**
   * The smallest radius this star type can have. In solar radii
   */
  override def radiusMin: Double = 0.075

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMin: Double = 0.00015

  /**
   * The lowest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMin: Double = 2400

  /**
   * The smallest mass this star type can be. In solar masses
   *
   * this from https://en.wikipedia.org/wiki/Red_dwarf
   */
  override def massMin: Double = 0.075

  /**
   * Here 'max' means for the highest mass star of this class.
   */
  override def colorMax: ColorRGBA = StarType.genColor(0xff, 0xcf, 0x88)

  override def name: String = "red dwarf (type M main sequence star)"
}
