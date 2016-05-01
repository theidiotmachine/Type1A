package typeIa.space

import com.jme3.math.ColorRGBA
import typeIa.maths.Constants
import typeIa.maths.units.Units.DegreesKelvin
import typeIa.space.starType.{GTypeMainSequence, StarType}

import scala.util.Random

/**
 * Data for a star
 *
 * @param starType    The star type
 * @param mass        In solar masses
 * @param luminosity  In solar lumionsity
 * @param radius      In solar radii
 * @param color       RGB color
 * @param surfaceTemperature  In degrees K
 */
class StarData(val starType: StarType,
               val mass: Double, val luminosity: Double, val radius: Double, val color: ColorRGBA, val surfaceTemperature: DegreesKelvin) {
  def tip: String = starType.name

  val radiusm = Constants.SolarRadius * radius
  /**
   * 150-170K from the protostar, where ice grains can form. From
   * https://ay201b.wordpress.com/the-snow-line-in-protoplanetary-disks/
   * 'isolation mass of planetesimals is amplified by a factor of 8, cores form faster. [...] Gas giants form
   * much more easily beyond the snow line'
   * 2.7AU from sun
 *
   * @return
   */
  def formationSnowLineau: Double = ???
  //snowLineau(0.75)

  def snowLineau(/*dimness: Double = 1.0*/): Double = {
    //val bodge = 1.3
    //val formationSurfaceTemperature = surfaceTemperature * dimness// * bodge
    Loc.mToau(math.sqrt((
      (radiusm * radiusm * surfaceTemperature.`⁴`).value
        / (160*160*160*160)
      ) / 4))
  }

  def spaceTempAtm(distm: Long) = {
    //distm = (((starRad^2*surfTem^4)/(out^4))/4)^1/2
    //4*(distm^2) = (starRad^2*surfTem^4)/(out^4)
    //out = ((starRad^2*surfTem^4)/(4*(distm^2)))^1/4
    val distmd = distm.toDouble
    //this bodge makes the numbers work, so we get ~5au
    val bodge = 1.0
    val bodgedSurfaceTemperature = surfaceTemperature * bodge
    //math.pow((radiusm*radiusm*bodgedSurfaceTemperature.`⁴`)/(4*distmd*distmd), 0.25)
    ((radiusm*radiusm*bodgedSurfaceTemperature.`⁴`)/(4*distmd*distmd)).∜
  }
}

object StarData {
  def main(args: Array[String]) {
    val starData = GTypeMainSequence.generateStarData(1, new Random())
    val snowLineau = starData.snowLineau()
    val fsnowLineau = starData.formationSnowLineau
    val t = starData.spaceTempAtm((Loc.AstronomicalUnitsInMetres * 5.0).toLong)
    val t2 = starData.spaceTempAtm((Loc.AstronomicalUnitsInMetres).toLong)
    val i = 3
  }
}