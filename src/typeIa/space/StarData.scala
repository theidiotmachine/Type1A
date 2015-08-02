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
}
