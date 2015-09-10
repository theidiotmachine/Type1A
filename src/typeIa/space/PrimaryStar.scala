package typeIa.space

import com.jme3.export.{JmeExporter, JmeImporter, Savable}

/**
 * A star, for the purposes of this, is the largest body in a system; so we include brown dwarfs
 */
class PrimaryStar(val name: String, val loc: Loc, val starData: StarData,
                  o: => Array[Satellite])
  extends Primary with LocatedObject with Star
  with Savable{
  lazy val satellites = o

  override def write(ex: JmeExporter): Unit = ???

  override def read(im: JmeImporter): Unit = ???

  def tip: String = {
    name + " - " + starData.tip
  }

  override def loc(time: Double): Loc = loc
}
