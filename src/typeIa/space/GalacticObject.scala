package typeIa.space

/**
 * This is the principle thing in a system, and will appear on the galaxy map. It might be a star, rouge planet, or a binary
 *
 * I wonder if it should also inherit Primary
 */
trait GalacticObject {
  val name: String
  def galacticLoc(time: Double): Locpc
  def tip: String
}
