package typeIa.space.neighborhood

import typeIa.space.{Locpc, StarGenerator, GalacticObject}

import scala.util.Random

/**
 * A debug star generator
 */
object PDMSClusterNeighborhoodGenerator extends NeighborhoodGenerator {
  override def apply(sizePc: Double): Neighborhood = {
    val seed: Long = 100

    val r = new Random(seed)
    new Neighborhood(Array[GalacticObject](
      /*StarGenerator.generatePDMS(Locpc(0, 0, 0), r),*/
      StarGenerator.generatePDMS(Locpc(3, 0, 0), r),
      StarGenerator.generatePrimaryStar(Locpc(0, 0, 0), r)/*,
      StarGenerator.generatePrimaryStar(Locpc(3, 0, 0), r),
      StarGenerator.generatePDMS(Locpc(-3, 0, 0), r),
      StarGenerator.generatePDMS(Locpc(0, 3, 0), r),
      StarGenerator.generatePDMS(Locpc(0, -3, 0), r),
      StarGenerator.generatePDMS(Locpc(0, 0, 3), r),
      StarGenerator.generatePDMS(Locpc(0, 0, -3), r)*/
    ))
  }
}
