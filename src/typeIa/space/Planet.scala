package typeIa.space

/**
 * A planet, for the purposes of this, orbits a star (and so has a loc in au)
 */
class Planet(val loc: Locau, val orbitRadiusInAu: Double, o1: => Primary, o2: => Array[Satellite]) extends Satellite with Primary {
  lazy val primary = o1
  lazy val satellites = o2
}
