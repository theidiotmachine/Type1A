package typeIa.renderer

import com.jme3.math.Vector3f
import typeIa.space.Locpc

/**
 * Created by tim on 10/04/15.
 */
object Renderer {
  var gfxScale: Double = 0.5
  var gfxOrigin: Locpc = new Locpc(0, 0, 0)

  /**
   * Given a location in pc, returns a location in gfx. Will fail if the sizes overflow the float value:
   * either due to a high scale, a distant centre, or both.
   */
  def getNearLocGfx(loc: Locpc): Vector3f = new Vector3f(((loc.x - gfxOrigin.x) * gfxScale).toFloat,
    ((loc.y - gfxOrigin.y) * gfxScale).toFloat,
    ((loc.z - gfxOrigin.z) * gfxScale).toFloat)
  
  def getLocpc(locgfx: Vector3f) = new Locpc(
    (locgfx.x / gfxScale) + gfxOrigin.x,
    (locgfx.y / gfxScale) + gfxOrigin.y,
    (locgfx.z / gfxScale) + gfxOrigin.z
  )
}
