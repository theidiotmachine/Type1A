package typeIa.renderer.stars

import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.renderer.{RenderManager, ViewPort}
import com.jme3.scene.Geometry
import com.jme3.scene.Spatial.CullHint
import com.jme3.scene.control.AbstractControl
import typeIa.renderer.Renderer
import typeIa.space.{Locpc, Utils}

/**
 * Created by tim on 21/04/15.
 */
class StarBillboardControl(val locpc: Locpc, val mat: Material, val geom: Geometry,
                           val color: ColorRGBA, close: StarBillboardControlClose, far: StarBillboardControlFar) extends AbstractControl{
  override def controlUpdate(tpf: Float): Unit = {
    val loc = Renderer.getNearLocGfx(locpc)
    geom.setLocalTranslation(loc)
  }

  override def controlRender(rm: RenderManager, vp: ViewPort): Unit = {
    val cam = vp.getCamera
    val camLoc = cam.getLocation.clone()

    val camLocpc = Renderer.getLocpc(camLoc)
    val offset = locpc - camLocpc
    val lpc = offset.length
    val lau = Utils.pcToau(lpc)
    val closea = close.getCloseAlpha(lpc, lau)
    val fara = far.getFarAlpha(lpc, lau)
    val a = math.min(closea, fara)

    color.a = a.toFloat//math.max(a.toFloat, 0.1f)
    mat.setColor("Color", color)


    if(a < 0.001)
      geom.setCullHint(CullHint.Always)
      //geom.hide()
    else
      geom.setCullHint(CullHint.Never)
    //geom.unhide()

  }
}
