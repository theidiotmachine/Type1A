package typeIa.renderer.stars

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

  /**
   * The maximum distance that we will render these at, in au
   * @param separationDistau The separation dist between the two things
   * @return an au distance to separate them
   */
  protected def maxDistau(separationDistau: Double): Double = separationDistau * 100.0 * 1.1//0.1 * Renderer.gfxScaleau * 1.1

  protected def minDist(separationDistau: Double): Double = separationDistau * 100.0 * 0.9//* 0.1 * Renderer.gfxScaleau * 0.9
}