package typeIa.space.starType

import com.jme3.math.ColorRGBA
import typeIa.space.StarData

import scala.util.Random

/**
 * From wiki. To do: PN!
 */
object WhiteDwarf extends StarType{
  /**
   * The likelihood of a star being this type when alone or in a distant binary.
   *
   * This is a guess based on the hundred nearest stars having what look like 4 lone wds
   */
  override def loneStarProbability: Double = 0.04

  /**
   * The largest radius this star type can be have. In solar radii
   */
  override def radiusMax: Double = 0.02

  /**
   * Here 'min' means for the least luminous WD. again, cribbed off the wiki pic
   */
  override def colorMin: ColorRGBA = StarType.genColor(0xff, 0xcf, 0x88)

  /**
   * The highest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMax: Double = 150000

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMax: Double = 100

  /**
   * The largest mass this star type can be. In solar masses
   */
  override def massMax: Double = 1.33

  override def name: String = "white dwarf"

  /**
   * The smallest radius this star type can have. In solar radii
   */
  override def radiusMin: Double = 0.008

  /**
   * In multiple of sol luminosity
   */
  override def luminosityMin: Double = 0.0001

  /**
   * The lowest surface temp this star type can have. In K
   */
  override def surfaceTemperatureMin: Double = 4000

  /**
   * The smallest mass this star type can be. In solar masses
   */
  override def massMin: Double = 0.17

  /**
   * Here 'max' means for the most luminous star. WDs can be B-K, at least on the nice wiki pic
   */
  override def colorMax: ColorRGBA = StarType.genColor(0xa3, 0xb8, 0xff)

  /**
   * Dumb linear interpolation of star data
   * @param mass Given a mass
   * @return Create me some star data for this star type
   */
  override def generateStarData(mass: Double, r: Random): StarData = {
    //the mass is pretty dumb. A better model for mass would be a nice bell-shaped curve centred on 0.5 to 0.6

    //this from the wiki article where radius is inv proportional to cube root of mass, with
    // the caveat that spin changes things. I took a handful of real wds, and got a constant that sorta fitted them
    val c = 0.01
    val radius = math.pow(mass, -0.333) * c

    //a w dwarf cools over its lifetime; so we'll take a random number for age. I wonder if I should store age? It
    //might be useful for planets. Note that the age factor here is 1 - young, 0 = old, which is just because it's
    // convenient
    val ageFactor = r.nextDouble()
    val starLuminosity = luminosity(mass) * ageFactor
    val color = new ColorRGBA
    color.interpolate(colorMin, colorMax, ageFactor.toFloat)
    new StarData(this, mass, starLuminosity, radius, color,
      surfaceTemperature(starLuminosity, radius))
  }

  override def loneStarPNProb: Double = 0.5
}
