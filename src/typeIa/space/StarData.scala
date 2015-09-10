package typeIa.space

import com.jme3.math.ColorRGBA
import typeIa.space.starType.StarType

/**
 * Data for a star
 * @param starType    The star type
 * @param mass        In solar masses
 * @param luminosity  In solar lumionsity
 * @param radius      In solar radii
 * @param color       RGB color
 * @param surfaceTemperature  In degrees K
 */
class StarData(val starType: StarType,
               val mass: Double, val luminosity: Double, val radius: Double, val color: ColorRGBA, val surfaceTemperature: Double) {
  def tip: String = starType.name

  /**
   * 150-170K from the protostar, where ice grains can form. From
   * https://ay201b.wordpress.com/the-snow-line-in-protoplanetary-disks/
   * 'isolation mass of planetesimals is amplified by a factor of 8, cores form faster. [...] Gas giants form
   * much more easily beyond the snow line'
   * 2.7AU from sun
   * @return
   */
  def formationSnowLineau: Double = ???

  def spaceTempAtm(distm: Long) = {
    //distm = (((starRad^2*surfTem^4)/out)/4)^1/2
    //4*(distm^2) = (starRad^2*surfTem^4)/out
    //out = (starRad^2*surfTem^4)/(4*(distm^2))
    val distmd = distm.toDouble
    (radius*radius*surfaceTemperature*surfaceTemperature*surfaceTemperature*surfaceTemperature)/(4*distmd*distmd)
  }
}
