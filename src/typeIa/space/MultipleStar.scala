package typeIa.space

import com.jme3.export.{JmeExporter, JmeImporter, Savable}
import com.jme3.math.ColorRGBA

/**
 * A single star in a multiple.
 */
class MultipleStar(val name: String, val starData: StarData, val orbitRadiusInAu: Double, val systemLoc: Locpc,
                   val orbitVector: Locpc,
                   a: Boolean) extends BinaryElem with Star with Savable{
  /**
   * The color for a sprite
   */
  override def getColor: ColorRGBA = starData.color

  /**
   * In solar luminosities
   */
  override def getLuminosity: Double = starData.luminosity

  /**
   *
   */
  override def galacticLoc(time: Double): Locpc = {
    //FIXME do orbits!
    systemLoc + (orbitVector * Utils.auTopc(if(a)orbitRadiusInAu else -orbitRadiusInAu))
  }
  /**
   * In solar radii
   */
  override def getRadius: Double = starData.radius

  override def tip: String = name + " - " + starData.tip

  override def write(ex: JmeExporter): Unit = ???

  override def read(im: JmeImporter): Unit = ???
}
