package typeIa.space

/**
 * A thing which orbits something else
 */
trait Satellite {
  val primary: Primary
  val orbitRadiusInAu: Double
}
