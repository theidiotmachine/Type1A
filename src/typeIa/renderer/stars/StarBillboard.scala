package typeIa.renderer.stars

import com.jme3.asset.AssetManager
import com.jme3.material.Material
import com.jme3.material.RenderState.BlendMode
import com.jme3.math.{ColorRGBA, Vector3f}
import com.jme3.renderer.Camera
import com.jme3.renderer.queue.RenderQueue.Bucket
import com.jme3.scene.control.BillboardControl
import com.jme3.scene.shape.Quad
import com.jme3.scene.{Geometry, Node, Spatial}
import typeIa.renderer.Renderer
import typeIa.space.{LocatedObject, PrimaryDetachedMultipleStar, PrimaryStar}
/**
 * Created by tim on 06/04/15.
 */
object StarBillboard {

  def createQuad(go: LocatedObject, locgfx: Vector3f, radius: Double): Geometry = {
    val size = math.min(math.max(radius/10, 0.05), 0.2).toFloat
    val quad = new Quad(size, size)
    val geom = new Geometry(go.name, quad)
    geom.setLocalTranslation(locgfx)
    val control = new BillboardControl
    geom.addControl(control)

    geom.setQueueBucket(Bucket.Transparent)
    geom.setUserData("GalacticObject", go)

    geom
  }

  def createMaterial(assetManager: AssetManager, color: ColorRGBA): Material = {
    val material = new Material(assetManager,
      "Common/MatDefs/Misc/Unshaded.j3md")
    material.setColor("Color", color)
    material.getAdditionalRenderState.setBlendMode(BlendMode.AlphaAdditive)
    //mat1.getAdditionalRenderState.setAlphaFallOff(0.5f)
    //mat1.getAdditionalRenderState.setAlphaTest(true)

    val tex = assetManager.loadTexture("Textures/star1.png")
    material.setTexture("ColorMap", tex)
    material
  }


  def createPSSystem(ps: PrimaryStar, assetManager: AssetManager): Spatial = {
    val color = ps.starData.color
    val luminosity = ps.starData.luminosity
    val radius = ps.starData.radius
    val loc = ps.loc
    val geom = createQuad(ps, Renderer.getNearLocGfx(loc), radius)
    val mat = createMaterial(assetManager, color)
    geom.setMaterial(mat)
    geom.addControl(new StarBillboardControl(loc, mat, geom, color.clone(),
      new StarBillboardControlCloseSingle(luminosity), new StarBillboardControlFarSingle(luminosity)))
    geom
  }

  def createPDMSSystem(pdms: PrimaryDetachedMultipleStar, assetManager: AssetManager, cam: Camera): Spatial = {
    val out = new Node
    val sep = pdms.getSeparationAu

    val kids = Array[StarBillboardControlDatum](
      new StarBillboardControlDatum(
        pdms,
        pdms.getMergedLuminosity, Some(sep), None, "Textures/star1.png", assetManager,
        pdms.getMergedColor.clone(), pdms.loc(0), pdms.getMergedRadius),
      new StarBillboardControlDatum(
        pdms.a,
        pdms.a.getLuminosity, None, Some(sep), "Textures/star1.png", assetManager,
        pdms.a.getColor.clone(), pdms.a.loc(0), pdms.a.getRadius),
      new StarBillboardControlDatum(
        pdms.b,
        pdms.b.getLuminosity, None, Some(sep), "Textures/star1.png", assetManager,
        pdms.b.getColor.clone(), pdms.b.loc(0), pdms.b.getRadius)
    )

    out.addControl(new StarMultiMasterControl(pdms.loc, kids, cam))
    out
  }
}
