package typeIa.space

/**
 * This is a thing, with a location. It might be a star, rouge planet, or a binary
 */
trait LocatedObject {
  val name: String
  def loc(time: Double): Loc
  def tip: String
}
