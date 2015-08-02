package typeIa.space

import com.jme3.export.{JmeExporter, JmeImporter, Savable}

/**
 * A star, for the purposes of this, is the largest body in a system; so we include brown dwarfs
 */
class PrimaryStar(val name: String, val galacticLoc: Locpc, val starData: StarData,
                  o: => Array[Satellite])
  extends Primary with GalacticObject with SolarSystemObject with Star
  with Savable{
  lazy val satellites = o
  val solarSystemLoc = new Locau(0, 0, 0)

  override def write(ex: JmeExporter): Unit = ???

  override def read(im: JmeImporter): Unit = ???

  def tip: String = {
    name + " - " + starData.tip
  }

  override def galacticLoc(time: Double): Locpc = galacticLoc
}
