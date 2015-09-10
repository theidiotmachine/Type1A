package typeIa.space.neighborhood

import typeIa.space.LocatedObject

/**
 * A collection of stars
 */
class Neighborhood(val stars: Array[LocatedObject]) {

}

object Neighborhood {
  def apply(sizePc: Double): Neighborhood = {

    RandomNeighborhoodGenerator(sizePc)
    //PDMSClusterNeighborhoodGenerator(sizePc)
  }
}
