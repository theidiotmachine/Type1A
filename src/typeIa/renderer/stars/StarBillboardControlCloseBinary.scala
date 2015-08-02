package typeIa.renderer.stars

/**
 * Use this when you are the billboard of a merged pair. When far away, it's just the normal alg.
 * when close, it fades out so that the individual stars can be seen.
 */
class StarBillboardControlCloseBinary(val luminosity: Double, val separationDistau: Double) extends StarBillboardControlClose {
  def getCloseAlpha(lpc: Double, lau: Double): Double = {
    val max = maxDist(separationDistau)
    if(lau > max)
      getDistBasedA(lpc)
    else if(lau > minDist(separationDistau)) {
      //fade in from getDistBasedA(l)
      val min = minDist(separationDistau)
      val range = max - min
      val delta = (lau - min)/range
      //(1-delta) * getDistBasedA(l)
      delta * getDistBasedA(lpc)
    }
    else
      0
  }
}
