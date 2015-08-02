package typeIa.renderer.stars

/**
 * Created by tim on 18/04/15.
 */
class StarBillboardControlCloseSingle(val luminosity: Double) extends StarBillboardControlClose {
  def getCloseAlpha(lpc: Double, lau: Double): Double = {
    getDistBasedA(lpc)
  }
}
