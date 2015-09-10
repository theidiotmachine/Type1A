package typeIa.renderer.stars

import com.jme3.asset.AssetManager
import com.jme3.math.ColorRGBA
import com.jme3.renderer.{Camera, RenderManager, ViewPort}
import com.jme3.scene.control.AbstractControl
import com.jme3.scene.{Node, Spatial}
import typeIa.renderer.Renderer
import typeIa.space.{Loc, LocatedObject}

/**
 * Billboard control for multi systems
 */
class StarMultiMasterControl(val loc: Loc, val kids: Array[StarBillboardControlDatum], val cam: Camera) extends AbstractControl{
  override def controlUpdate(tpf: Float): Unit = {
    val locgfx = Renderer.getNearLocGfx(loc)
    spatial.setLocalTranslation(locgfx)
    var i = 0
    while(i < kids.length) {
      kids(i).update(spatial.asInstanceOf[Node])
      i += 1
    }

    val camLocgfx = cam.getLocation.clone()

    val camLoc = Renderer.getLoc(camLocgfx)
    val offset = loc - camLoc

    val lpc = offset.lengthpc
    val lau = Loc.pcToau(lpc)
    i = 0
    while(i < kids.length) {
      kids(i).render(lpc, lau, loc)
      i += 1
    }
  }

  override def controlRender(rm: RenderManager, vp: ViewPort): Unit = {}
}

/**
 *
 * @param go The galactic object
 * @param luminosity How luminous this thing is
 * @param osepau I... don't know
 * @param oparentSepau Or this one
 * @param texName What texture
 * @param assetManager We need the asset manager to create textures
 * @param color The color
 * @param loc The galactic location
 * @param radius How big this thing is
 */
class StarBillboardControlDatum(val go: LocatedObject,
                                luminosity: Double,
                                osepau: Option[Double], oparentSepau: Option[Double],
                                texName: String,
                                assetManager: AssetManager,
                                val color: ColorRGBA,
                                val loc: Loc,
                                val radius: Double) {

  private def createCloseFar(luminosity: Double, osepau: Option[Double], oparentSepau: Option[Double]): (StarBillboardControlClose, StarBillboardControlFar) = {

    val close = osepau match{
      case None => new StarBillboardControlCloseSingle(luminosity)
      case Some(sepau) => new StarBillboardControlCloseBinary(luminosity, sepau)
    }
    val far = oparentSepau match{
      case None => new StarBillboardControlFarSingle(luminosity)
      case Some(parentSepau) => new StarBillboardControlFarBinary(luminosity, parentSepau)
    }

    (close, far)
  }

  val (close, far) = createCloseFar(luminosity, osepau, oparentSepau)
  val material = StarBillboard.createMaterial(assetManager, color)
  var visible: Boolean = false
  var attached: Boolean = false
  var ospatial: Option[Spatial] = None


  def render(lpc: Double, lau: Double, parentLoc: Loc): Unit = {
    val closea = close.getCloseAlpha(lpc, lau)
    val fara = far.getFarAlpha(lpc, lau)
    val a = math.min(closea, fara)

    if(lau < 100000) {
      val i = 3
    }

    if(a < 0.001 && visible) {
      //ospatial.get.removeFromParent()
      //ospatial = None
      visible = false
    }
    else if(a > 0.001 && !visible) {
      color.a = a.toFloat//math.max(a.toFloat, 0.1f)
      val geom = StarBillboard.createQuad(go, Renderer.getNearLocGfx(loc - parentLoc), radius)
      material.setColor("Color", color)
      geom.setMaterial(material)
      ospatial = Some(geom)
      visible = true
    } else if(visible) {
      color.a = a.toFloat//math.max(a.toFloat, 0.1f)
      material.setColor("Color", color)
    }
  }

  def update(parentNode: Node): Unit = {
    if(visible && !attached) {
      parentNode.attachChild(ospatial.get)
      attached = true
    }
    else if(!visible && attached) {
      ospatial.get.removeFromParent()
      ospatial = None
      attached = false
    }
  }
}
