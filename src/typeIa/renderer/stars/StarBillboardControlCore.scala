package typeIa.renderer.stars

import typeIa.renderer.Renderer

/**
 * Created by tim on 18/04/15.
 */
trait StarBillboardControlCore {
  val luminosity: Double
  /** I found a thing that says 13
    * http://cosmoquest.org/forum/showthread.php?49612-How-far-do-you-have-to-get-away-from-the-Sun-to-get-it-invisible-for-the-unaided-eye
    */
  protected def getAbsMaxDistPc: Double = luminosity * 500//13

  protected def getDistBasedA(distPc: Double): Double = {
    val totDist = getAbsMaxDistPc
    if(distPc>=totDist)
      0
    else
      1.0 - (distPc / totDist)
  }

  protected val maxDistRatio = 150
  protected val minDistRatio = 120

  protected val optDist = 150

  protected def maxDist(separationDistau: Double): Double = separationDistau * optDist * Renderer.gfxScale * 1.1
  protected def minDist(separationDistau: Double): Double = separationDistau * optDist * Renderer.gfxScale * 0.9
}