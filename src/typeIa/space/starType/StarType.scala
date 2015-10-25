package typeIa.space.starType

import com.jme3.math.ColorRGBA
import typeIa.maths.Constants
import typeIa.space.StarData

import scala.util.Random

/**
 * Trait for a star type. The data for this comes from
 *
 * https://en.wikipedia.org/wiki/Stellar_classification#Harvard_spectral_classification
 * http://www.vendian.org/mncharity/dir3/starcolor/
 */
trait StarType{
  /**
   * The likelihood of a star being this type when alone or in a distant binary. This
   */
  def loneStarProbability: Double

  /**
   * The smallest mass this star type can be. In solar masses
   */
  def massMin: Double

  /**
   * The largest mass this star type can be. In solar masses
   */
  def massMax: Double

  /**
   * The smallest radius this star type can have. In solar radii
   */
  def radiusMin: Double

  /**
   * The largest radius this star type can be have. In solar radii
   */
  def radiusMax: Double

  /**
   * Unbelievably dumb liner interpolation
   * @param mass the star mass
   * @return the star radius
   */
  def radius(mass: Double): Double = {
    val massRange = massMax - massMin
    val radRange = radiusMax - radiusMin
    val massFrac = (mass - massMin) / massRange
    (massFrac * radRange) + radiusMin
  }

  /**
   * The lowest surface temp this star type can have. In K
   */
  def surfaceTemperatureMin: Double

  /**
   * The highest surface temp this star type can have. In K
   */
  def surfaceTemperatureMax: Double

  def surfaceTemperature(luminosity: Double, radius: Double): Double = {
    //val massRange = massMax - massMin
    //val massFrac = (mass - massMin) / massRange
    //val sTempRange = surfaceTemperatureMax - surfaceTemperatureMin
    //(massFrac * sTempRange) + surfaceTemperatureMin

    //stefan bolzman (2)
    //luminosity = 4*pi*radius^2*sbConst*surfaceTemp^4
    //so
    //luminosity/(4*pi*radius^2*sbConst) = surfaceTemp^4
    //luminosity/(4*pi*radius^2*sbConst)^1/4 = surfaceTemp

    val radiusm = radius * StarType.SolarRadiusm
    val surfaceTemperature = math.pow((luminosity*StarType.SolarLuminosityW)/(4*math.Pi*radiusm*radiusm*Constants.StefanBoltzmann.value), 0.25)
    if(surfaceTemperature > surfaceTemperatureMax)
      surfaceTemperatureMax
    else if (surfaceTemperature < surfaceTemperatureMin)
      surfaceTemperatureMin
    else
      surfaceTemperature
  }

  /**
   * Here 'min' means for the lowest mass star of this class.
   */
  def colorMin: ColorRGBA

  /**
   * Here 'max' means for the highest mass star of this class.
   */
  def colorMax: ColorRGBA

  /**
   * In multiple of sol luminosity
   */
  def luminosityMin: Double

  /**
   * In multiple of sol luminosity
   */
  def luminosityMax: Double

  def luminosity(mass: Double): Double = {
    val massRange = massMax - massMin
    val massFrac = (mass - massMin) / massRange
    val lumRange = luminosityMax - luminosityMin
    (massFrac * lumRange) + luminosityMin
  }

  /**
   * Dumb linear interpolation of star data
   * @param mass Given a mass
   * @return Create me some star data for this star type
   */
  def generateStarData(mass: Double, r: Random): StarData = {
    val massRange = massMax - massMin
    val massFrac = (mass - massMin) / massRange

    val color = new ColorRGBA
    color.interpolate(colorMin, colorMax, massFrac.toFloat)

    val starLuminosity = luminosity(mass)
    val starRadius = radius(mass)

    new StarData(this, mass, starLuminosity, starRadius, color,
      surfaceTemperature(starLuminosity, starRadius)
    )
  }

  def name: String

  def loneStarPNProb: Double = 0
}

object StarType {
  /**
   * Number of stars that are luminous stars, rather than brown dwarves. Note that a black hole is a luminous star
   * for these purposes
   *
   * http://www.space.com/16112-brown-dwarf-stars-sun-rare.html
   *
   * Rather than one star for every brown dwarf, there may be as many as six stars for every brown dwarf.
   */
  val starFraction = 6.0/7.0

  /**
   * Number of stars that are brown dwarves, rather than glowy stars. Obvs the inverse of above
   */
  val brownDwarfFraction = 1.0/7.0


  /**
   * Fraction of luminous stars that are main sequence, as opposed to giants, white dwarves, or black holes
   */
  val loneMainSequenceFraction = 0.95 * starFraction //0.9 * starFraction

  /**
   * Utility to generate a jme3 floating point color from the 8 bit colors I get much of my source data from
   */
  def genColor(r: Int, g: Int, b: Int): ColorRGBA = {
    new ColorRGBA(r.toFloat/256.0f, g.toFloat/256.0f, b.toFloat/256.0f, 1.0f)
  }

  val SolarRadiusm = 696342000
  val SolarLuminosityW = 3.846e26


}
