package typeIa.space.starType

import com.jme3.math.ColorRGBA
import typeIa.maths.units.Units.DegreesKelvin

/**
 * An M Type brown dwarf. Data mostly from https://en.wikipedia.org/wiki/Brown_dwarf
 */
object MTypeBrownDwarf extends StarType {
  /**
   * The likelihood of a star being this type.
   *
   * I basically made this up
   */
  override def loneStarProbability: Double = StarType.brownDwarfFraction * 0.5

  /**
   * The largest radius this star type can be have. In solar radii
   */
  override def radiusMax: Double = 0.075

  /**
   * Here 'min' means for the lowest mass star of this class.
   *
   * I'm using the M colors from the main sequence. this means that my M class dwarves are too red.
   */
  override def colorMin: ColorRGBA = StarType.genColor(0xff, 0xc9, 0x56)

  /**
   * The highest surface temp this star type can have. In K
   *
   * Taken from the M main seqnece
   */
  override def surfaceTemperatureMax: DegreesKelvin = new DegreesKelvin(2400)

  /**
   * In multiple of sol luminosity
   *
   * I took this from the MTypeMain Sequence
   */
  override def luminosityMax: Double = 0.00015

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 0.07

  /**
   * The smallest radius this star type can have. In solar radii
   *
   * wiki (https://en.wikipedia.org/wiki/Brown_dwarf) sez The net result is that the radii of brown dwarfs vary by only 10–15% over the range of possible masses.
   */
  override def radiusMin: Double = 0.075 * 0.85

  /**
   * In multiple of sol luminosity
   *
   * I made this up
   */
  override def luminosityMin: Double = 0.000015

  /**
   * The lowest surface temp this star type can have. In K
   *
   * Taken from https://en.wikipedia.org/wiki/WISE_1828%2B2650
   */
  override def surfaceTemperatureMin: DegreesKelvin = new DegreesKelvin(400)

  /**
   * The smallest mass this star type can be. In solar masses
   */
  override def massMin: Double = 0.01

  /**
   * Here 'max' means for the highest mass star of this class. I'm going with ffcc6f as max, which is a typical M type
   * This means that my dwarves are too pale
   */
  override def colorMax: ColorRGBA = StarType.genColor(0xff, 0xcc, 0x6f)

  override def name: String = "type M brown dwarf"
}
