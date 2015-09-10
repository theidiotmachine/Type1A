package typeIa.space.starType

import com.jme3.math.ColorRGBA

/** O-Type main sequence.
 *
  * I have resigned myself to never seeing one of these in Type1A. I bet they are pretty impressive
 */
object OTypeMainSequence extends MainSequence {

  /**
   * From wiki: These stars are rare; it is estimated that there are no more than 20,000 in the entire Milky Way.
   * (0.000000067 of all stars)
   *
   * or 0.00003% of main sequence according to the wiki table (given 90% 0f stars are main sequence, this gives 0.00000027
   * of all stars).
   *
   * I'm going with the second coz it's larger!
   */
  def loneStarProbability: Double = StarType.loneMainSequenceFraction *  0.0000003

  /**
   * wiki says 16
   */
  def massMin: Double = 16
  def massMax: Double = 100
  def radiusMin: Double = 6.6
  def radiusMax: Double = 20
  def surfaceTemperatureMin: Double = 30000
  def surfaceTemperatureMax: Double = 52000

  /**
   * typical is 9bb0ff.. Next, B, is aabfff
   */
  def colorMin: ColorRGBA = {
    StarType.genColor(0xa3, 0xb8, 0xff)
  }

  def colorMax: ColorRGBA = {
    StarType.genColor(0x93, 0xa8, 0xff)
  }

  def luminosityMin: Double = 30000
  def luminosityMax: Double = 1000000

  override def name: String = "type O main sequence star"
}
