package typeIa.space

import com.jme3.export.{JmeExporter, JmeImporter, Savable}
import com.jme3.math.{Vector3f, ColorRGBA}

/**
 * A single star in a multiple.
 */
class MultipleStar(val name: String, val starData: StarData, val orbitRadiusInAu: Double, val systemLoc: Loc,
                   val orbitVector: Vector3f,//a unit vector
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
  override def loc(time: Double): Loc = {
    //FIXME do orbits!
    systemLoc + Loc.unitAndauLength(orbitVector, if(a)orbitRadiusInAu else -orbitRadiusInAu)//orbitVector.mult(Loc.auTom(if(a)orbitRadiusInAu else -orbitRadiusInAu))
    //systemLoc + (orbitVector * Loc.auTopc(if(a)orbitRadiusInAu else -orbitRadiusInAu))
  }
  /**
   * In solar radii
   */
  override def getRadius: Double = starData.radius

  override def tip: String = name + " - " + starData.tip

  override def write(ex: JmeExporter): Unit = ???

  override def read(im: JmeImporter): Unit = ???
}
