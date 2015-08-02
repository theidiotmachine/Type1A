package typeIa.space.neighborhood

/**
 * Created by tim on 14/07/15.
 */
trait NeighborhoodGenerator {
  def apply(sizePc: Double): Neighborhood
}
