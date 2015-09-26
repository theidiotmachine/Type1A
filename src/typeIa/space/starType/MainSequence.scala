package typeIa.space.starType

/**
 * Stuff common to main sequence stars
 */
trait MainSequence extends StarType{
  override def luminosity(mass: Double): Double = {
    //stefan bolzman (1) - main sequence stars
    //lumiosity = ((mass/SolarMass)^3.9)*solarLuminosity
    //because we are dealing in solar masses and solar luminosities already, this is just this
    //however, 'relation flattens out at higher masses'; www2.astro.psu.edu/users/rbc/a534/lec18.pdf
    val pow = if(mass > 10)
      3.0
    else if(mass < 1)
      3.0
    else
      3.88

    val lum = math.pow(mass, pow)
    if(lum > luminosityMax)
      luminosityMax
    else if(lum < luminosityMin)
      luminosityMin
    else
      lum
  }

  override def radius(mass: Double): Double = {
    //from www2.astro.psu.edu/users/rbc/a534/lec18.pdf
    if(mass == 1.0)
      1.0
    else if(mass > 1.0)
      math.pow(mass, 0.57)
    else
      math.pow(mass, 0.8)
  }
}
