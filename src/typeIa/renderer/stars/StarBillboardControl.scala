package typeIa.renderer.stars

import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.renderer.{RenderManager, ViewPort}
import com.jme3.scene.Geometry
import com.jme3.scene.Spatial.CullHint
import com.jme3.scene.control.AbstractControl
import typeIa.renderer.Renderer
import typeIa.space.Loc

/**
 * Control to fade out stars if they are too far to be seen
 */
class StarBillboardControl(val loc: Loc, val mat: Material, val geom: Geometry,
                           val color: ColorRGBA, close: StarBillboardControlClose, far: StarBillboardControlFar) extends AbstractControl{
  override def controlUpdate(tpf: Float): Unit = {
    val locgfx = Renderer.getNearLocGfx(loc)
    geom.setLocalTranslation(locgfx)
  }

  override def controlRender(rm: RenderManager, vp: ViewPort): Unit = {
    val cam = vp.getCamera
    val camLocGfx = cam.getLocation.clone()

    val camLoc = Renderer.getLoc(camLocGfx)
    val offset = loc - camLoc
    val lpc = offset.lengthpc
    val lau = Loc.pcToau(lpc)
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
