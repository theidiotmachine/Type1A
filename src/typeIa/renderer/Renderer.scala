package typeIa.renderer

import com.jme3.math.Vector3f
import typeIa.space.Loc

/**
 * Not the actual renderer, but utils to render locs, I guess
 */
object Renderer {
  //this is how many pcs to gfx units.
  var gfxScalepc: Double = 0.5

  //how many m to to gfx units
  def gfxScalem: Double = gfxScalepc * Loc.MetresInParsecs

  //how many au to gfx units
  def gfxScaleau: Double = Loc.pcToau(gfxScalepc)

  var gfxOrigin: Loc = Loc(0, 0, 0)

  /**
   * Given a location in pc, returns a location in gfx. Will fail if the sizes overflow the float value:
   * either due to a high scale, a distant centre, or both.
   */
  def getNearLocGfx(loc: Loc): Vector3f = {
    val d = loc - gfxOrigin
    new Vector3f((gfxScalem * d.x).toFloat, (gfxScalem * d.y).toFloat, (gfxScalem * d.z).toFloat)
  }
/*
    new Vector3f(((loc.xpc - gfxOrigin.x) * gfxScale).toFloat,
    ((loc.ypc - gfxOrigin.y) * gfxScale).toFloat,
    ((loc.zpc - gfxOrigin.z) * gfxScale).toFloat)
  */

/*
  def getLoc(locgfx: Vector3f) = new Loc(
    (locgfx.x / gfxScale) + gfxOrigin.x,
    (locgfx.y / gfxScale) + gfxOrigin.y,
    (locgfx.z / gfxScale) + gfxOrigin.z
  )
*/
  /**
   * Given a gfx loc, get a game loc. This will be very rough and ready because of the casting
   * @param locgfx The gfx location
   * @return a game loc
   */
  def getLoc(locgfx: Vector3f) = new Loc(
    (locgfx.x / gfxScalem).toLong,
    (locgfx.y / gfxScalem).toLong,
    (locgfx.z / gfxScalem).toLong
  ) + gfxOrigin

}
