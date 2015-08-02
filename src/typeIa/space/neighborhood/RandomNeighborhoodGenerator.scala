package typeIa.space.neighborhood

import typeIa.space.{StarGenerator, GalacticObject}

import scala.util.Random

/**
 * The default random star generator
 */
object RandomNeighborhoodGenerator extends NeighborhoodGenerator{
  //@tailrec
  private def nextStar(r: Random, numStarsLeft: Long, sizePc: Double): List[GalacticObject] = {
    val star = StarGenerator.generateStarSystem(r, sizePc)

    if(numStarsLeft == 1)
      List(star)
    else
      star +: nextStar(r, numStarsLeft - 1, sizePc)
  }

  def apply(sizePc: Double): Neighborhood = {

    // From wiki, The true stellar density near the Sun is estimated as 0.004 stars per cubic light year, or 0.14 stars pc−3.
    // https://en.wikpedia.org/wiki/Stellar_density
    val stellarDensity = 0.14

    val numStars = (sizePc * sizePc * sizePc * stellarDensity).round

    val seed: Long = 100

    val r = new Random(seed)

    val stars = nextStar(r, numStars, sizePc).toArray

    new Neighborhood(stars)
  }
}
