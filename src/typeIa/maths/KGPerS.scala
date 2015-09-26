package typeIa.maths

/**
 * Kilograms per second
 */
class KGPerS(val x: Double) extends AnyVal{

}

object KGPerS {
  def solarMassPerYear(x: Double): KGPerS = {
    x * Constants.SolarMass / Constants.SeccondsPerYear
  }
}
