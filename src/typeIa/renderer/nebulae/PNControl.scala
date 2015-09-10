package typeIa.renderer.nebulae

import com.jme3.renderer.{Camera, RenderManager, ViewPort}
import com.jme3.scene.control.AbstractControl
import typeIa.space.Loc

/**
 * Created by tim on 25/07/15.
 */
class PNControl(val loc: Loc, val cam: Camera) extends AbstractControl{
  override def controlUpdate(tpf: Float): Unit = {
    /*
    val loc = Renderer.getNearLocGfx(locpc)
    spatial.setLocalTranslation(loc)


    val camLoc = cam.getLocation.clone()

    val camLocpc = Renderer.getLocpc(camLoc)
    val offset = locpc - camLocpc

    val lpc = offset.length
    val lau = Utils.pcToau(lpc)
*/

    /*
    + = l0, * = l1


    ->x y is straight up
    |
    z

      +   +   +
      *   *   *
    +   +   +   +
    *   *   *   *
      +   +   +
     */

    /*
    assuming c for x dist
      +
      |
    + | +

    sin 60 = p / c
    c sin 60 = p
    0.866025404 c ~= p

    which will also be height

     */
  }

  override def controlRender(rm: RenderManager, vp: ViewPort): Unit = {}
}
