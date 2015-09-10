package typeIa.space.neighborhood

import typeIa.space.{Loc, StarGenerator, LocatedObject}

import scala.util.Random

/**
 * A debug star generator
 */
object PDMSClusterNeighborhoodGenerator extends NeighborhoodGenerator {
  override def apply(sizePc: Double): Neighborhood = {
    val seed: Long = 100

    val r = new Random(seed)
    new Neighborhood(Array[LocatedObject](
      StarGenerator.generatePDMS(Loc.pc(0, 0, 0), r),
      StarGenerator.generatePDMS(Loc.pc(3, 0, 0), r),
      //StarGenerator.generatePrimaryStar(Loc.pc(0, 0, 0), r)

      //StarGenerator.generatePrimaryStar(Locpc(3, 0, 0), r),
      StarGenerator.generatePDMS(Loc.pc(-3, 0, 0), r),
      StarGenerator.generatePDMS(Loc.pc(0, 3, 0), r),
      StarGenerator.generatePDMS(Loc.pc(0, -3, 0), r),
      StarGenerator.generatePDMS(Loc.pc(0, 0, 3), r),
      StarGenerator.generatePDMS(Loc.pc(0, 0, -3), r)
    ))
  }
}
