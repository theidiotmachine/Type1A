package typeIa.renderer.stars

/**
 * Created by tim on 21/04/15.
 */
class StarBillboardControlFarSingle(val luminosity: Double)extends StarBillboardControlFar{
  override def getFarAlpha(lpc: Double, lau: Double): Double =
    getDistBasedA(lpc)
}
