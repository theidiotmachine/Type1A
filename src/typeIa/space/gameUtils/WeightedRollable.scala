package typeIa.space.gameUtils

/**
 * A trait for a thing to be rolled in a random lookup
 */
trait WeightedRollable {
  /**
   * The likelihood of rolling this. You should probably constrain this between 0 and 1, although the table
   * will normalise
   */
  def probability: Double
}
