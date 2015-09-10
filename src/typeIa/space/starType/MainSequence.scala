package typeIa.space.starType

/**
 * Stuff common to main sequence stars
 */
trait MainSequence extends StarType{
  override def luminosity(mass: Double): Double = {
    //stefan bolzman (1) - main sequence stars
    //lumiosity = ((mass/SolarMass)^3.9)*solarLuminosity
    //because we are dealing in solar masses and solar luminosities already, this is just this
    val lum = math.pow(mass, 3.9)
    if(lum > luminosityMax)
      luminosityMax
    else if(lum < luminosityMin)
      luminosityMin
    else
      lum
  }
}
