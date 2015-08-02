package typeIa.renderer.stars

/**
 * Use this when you are a star in a pair
 */
class StarBillboardControlFarBinary(val luminosity: Double, val parentSeparationDistau: Double) extends StarBillboardControlFar{

  override def getFarAlpha(lpc: Double, lau: Double): Double = {
    val max = maxDist(parentSeparationDistau)
    if (lau > max)
      0
    else if (lau > minDist(parentSeparationDistau)) {
      //fade in from getDistBasedA(l)
      val min = minDist(parentSeparationDistau)
      val range = max - min
      val delta = (lau - min) / range
      //delta * getDistBasedA(l)
      (1 - delta) * getDistBasedA(lpc)
    }
    else
      getDistBasedA(lpc)
  }
}
