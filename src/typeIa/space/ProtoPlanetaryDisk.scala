package typeIa.space

import typeIa.maths._
import typeIa.maths.units.Units
import typeIa.maths.units.Units._
/**
 * Icelines
 * H₂O = 150-170K > 2.7 au
 * CO = 25K -> 28 au
 * N₂ = 24K -> 32 au
 */
object ProtoPlanetaryDisk {
  /**
   * Paraphrased from wiki, a body's Hill sphere is the region in which it dominates the attraction of satellites.
   *
   * In other words, a small body orbiting a larger body will influence satellites in this radius
   * @param semiMajorAxis The semi-major axis of the smaller body round the larger. For circular, this is the orbital radius
   * @param smallMass The mass of the smaller body
   * @param largeMass The mass of the larger body
   * @return The hill radius in m
   */
  def hillRadiusm(semiMajorAxis: Metres, smallMass: Kilograms, largeMass: Kilograms): Metres = {
    //math.pow(semiMajorAxism, 3) * (1.0 - eccentrictym) * math.sqrt(smallMasskg/(3.0 * largeMasskg))
    semiMajorAxis *
    //* (MetresDouble(1) - eccentricty) *
      //math.sqrt(smallMass/(largeMass*3.0))
      (smallMass/(largeMass*3.0)).√
  }

  //planet forming regions ~<40au
  /**
   * This from Models of the Structure and Evolution of Protoplanetary Disks by Dullemond, Hollenbach, Kamp, and
   * D'Alessio for the grazing angle, and the rest from Theoretical Models of the Structure of Protoplanetary Disks by
   * Dullemond.
   *
   * If you read those papers, you'll find that this is not really a very good approximation; disks are three
   * dimensional things and the idea of a cool 'cloud deck' above an accreting plane and below an irradiated surface
   * is explored, particularly in the latter.
   *
   * Pragmatically, however, this is simple, and seems to come out consistently at twice the temperature. So, that's
   * what I'm doing.
   *
   * As an aside, I spent about a month of my life reading papers on this, and the physics are *fascinating*. Maybe
   * this is what I'll do when I retire.
   * @param r
   * @param starMass
   * @param starRadius
   * @param accretionRate
   * @param starLuminosity
   * @param grazingAngleRoll
   * @return
   */
  def ppdEffectiveTemperature(r: Metres, starMass: Kilograms, starRadius: Metres, accretionRate: KGPerS,
                              starLuminosity: Watts, grazingAngleRoll: Double): DegreesKelvin = {
    val grazingAngle = ((0.5 + grazingAngleRoll) * 0.4) * starRadius / r
    val kelplerianAngularFrequency = ((Constants.Gravitational * starMass) / r.`³`).`√`
    val k4 = ((3.0/(8.0*math.Pi*Constants.StefanBoltzmann)) * accretionRate * kelplerianAngularFrequency.`²`) +
      ((grazingAngle * starLuminosity)/(4*math.Pi*Constants.StefanBoltzmann*r.`²`))
    k4.∜
  }

  def ppdiskAccretionTemperature(r: Metres, z: Metres, starMass: Kilograms, accretionRate: KGPerS,
                                              dustToGasRatio: Double,
                                              avgOpacityAtWavelengthsOfDustThermalEmission: PerKGM2,
                                              characteristicRadius: Metres, γ: Double): DegreesKelvin = {
    val surfaceDensity = ppdSurfaceDensity(r, starMass, characteristicRadius, γ)
    val kelplerianAngularFrequencySqu = (Constants.Gravitational * starMass) / r.`³`
    val effectiuveTempAccretion = ((3.0/(8.0 * math.Pi* Constants.StefanBoltzmann)) * accretionRate * kelplerianAngularFrequencySqu).∜
    val verticalOpticalDepth = 0.5 * surfaceDensity * dustToGasRatio * avgOpacityAtWavelengthsOfDustThermalEmission
    //math.pow(3.0*verticalOpticalDepth/4.0, 0.25) * effectiuveTempAccretion
    (3.0*verticalOpticalDepth/4.0).∜ * effectiuveTempAccretion
  }

  //disks have masses between 0.0001 and 0.1 solar masses
  //vertical scale heights at 100 au of 5-20 au
  //large disk = 850 au
  //val omega k = kelplerianAngularFrequency = ((Constants.Gravitational * starMass) / r.`³`).`√`
  //val mu = mean molecular weight == 2.3
  //val mp = proton mass !
  //val cs = isothermal sound speed = sqrt((boltzman constant * temp (sigh)) / (mu * mp))
  //val alpha = 'dimensionless turbulent strength param' ~= 10e-3 to 10e-2 (10e-1?)
  //val nu (funny v) = viscocity = alpha * ((cs*cs)/omega k)
  //val vr = inward gass velocity = -3/2 * (nu/r)
  //val big epslion = surface density == integrated density at r, z from -inf to +inf
  //val m dot = accretion rate = -2 * pi * r * big epsilon * vr = 3 * pi * big epsilon * nu == known (10e-8 solar masses per year)
  //so
  //mdot = 3 * pi * big epsilon * alpha * ((cs*cs)/omega k)
  //omegak = ((Constants.Gravitational * starMass) / r.`³`).`√`
  //1/omegak = (r.`³`/(Constants.Gravitational * starMass)).sqrt
  //so
  //mdot = 3 * pi * big epsilon * alpha * ((boltzman constant * temp) / (mu * mp)) * (r.`³`/(Constants.Gravitational * starMass)).sqrt
  //
  //val funny n = dustToGasRatio = 0.01
  //val kd = average opacity at wavelengths of dust thermal emission
  //val psi = flaring angle 'stellar light can illuminate the surface of the disk ... a grazing angle' = typically 0.05
/*
  def ppdIrradiationTemperature3(r: Metres, z: Metres, starMass: Kilograms, accretionRate: KGPerS,
                                                starLuminosity: Watts, grazingAngle: Double,
                                                dustOpacityAtWavelengthsOfDustThermalEmission: PerKGM2,
                                                dustOpacityAtStellarWavelengths: PerKGM2): DegreesKelvin = {
    val surf = ppdSurfaceTemperature3(r, z, starLuminosity,
      dustOpacityAtWavelengthsOfDustThermalEmission, dustOpacityAtStellarWavelengths)
    val eff = ppdEffectiveTemperature(r, starMass, accretionRate, starLuminosity, grazingAngle)
    (surf.`⁴` + eff.`⁴`).∜()
  }
*/
  /*
  def protoplanetaryDiskTemperature3(r: Metres, z: Metres, starMass: Kilograms, accretionRate: KGPerS,
                                     starLuminosity: Watts, dustToGasRatio: Double,
                                     dustOpacityAtWavelengthsOfDustThermalEmission: PerKGM2,
                                     dustOpacityAtStellarWavelengths: PerKGM2,
                                     characteristicRadius: Metres, γ: Double, grazingAngle: Double): DegreesKelvin = {
    val acc = ppdiskAccretionTemperature3(r, z, starMass, accretionRate, dustToGasRatio,
      dustOpacityAtWavelengthsOfDustThermalEmission, characteristicRadius, γ)
    val irr = protoplanetaryDiskIrradiationTemperature3(r, z, starMass, accretionRate, starLuminosity, grazingAngle,
      dustOpacityAtWavelengthsOfDustThermalEmission, dustOpacityAtStellarWavelengths)

    (acc.`⁴` + irr.`⁴`).∜()
  }
*/
  /**
   * out to 30au (Neptune)
   *
   * This too low for super-jupiters. multiply up by random?
   * @param starMass
   * @return
   */
  def ppdMass(starMass: Kilograms): Kilograms = {
    //the median ratio of disk to stellar mass is 0.9% [Protoplanetary Disks and Their Evolution, Williams and Cieza]
    0.009 * starMass
  }

  /**
   * surface density at point
   * @param r distance from centre
   * @param starMass star mass
   * @param characteristicRadius the edge of the dense stuff.
   * @param γ a measurement of how steep the disk is. rom larger samples, Andrews et al. (2009, 2010b) find a tight
   *          range consistent with all having the same value γ = 0.9 [Protoplanetary Disks and Their Evolution, Williams and Cieza]
   * @return
   */
  def ppdSurfaceDensity(r: Metres, starMass: Kilograms, characteristicRadius: Metres, γ: Double): KGPerM2 = {
    //radial dep of disk viscosity
    //v proportional tp r ^ γ
    val rOverCharacteristicRadius = r / characteristicRadius
    //so here's a problem. I want to take scalars to arbitrary powers, and can't
    (2.0-γ) * (starMass/(2.0 * math.Pi * characteristicRadius.`²`)) *
      //math.pow(rOverCharacteristicRadius, -γ) *
      (rOverCharacteristicRadius ^ -γ) *
      //math.exp(-1.0 * math.pow(rOverCharacteristicRadius, 2.0-γ))
      math.exp(-1.0 * (rOverCharacteristicRadius ^ (2.0-γ)))
  }

  /**
   * The point at which the disk starts being fuzzy and thin and not really very interesting
   * @param ppdMass Mass of the disk
   * @param p a constant. "...a correlation with disk mass, Md ∝ Rc pow 1.6±03" [Protoplanetary Disks and Their Evolution, Williams and Cieza]
   * @return
   */
  def ppdCharacteristicRadius(ppdMass: Kilograms, p: Double): Metres = {
    //m = k * rc ^ p (where p = 1.6 +/1 0.3)
    //m/k = rc ^ p
    //m/k - p = rc
    //now, assumimg rc = 32au (neptune 30 au plus 2 au for general crap)
    //m = solar mass * 0.009
    //p = 1.6
    //we get
    //solarMass * 0.009 = k * 32au ^ 1.6
    //1.98855e30 * 0.009 = k * (32 * 149597870700.0) ^ 1.6
    //1.98855e30 * 0.009 / (32 * 149597870700.0) ^ 1.6 = k
    //1.789695×10²⁸ / 4.787131862×10¹² ^ 1.6
    //1.789695×10²⁸ /1.94142503×10²⁰
    //92184605.243293891
    //which is obviously poop, but you know
    val k = 92184605.243293891
    Metres(math.pow(ppdMass.value/k, p))
  }

  def ppdOpticalDepth(r: Metres, starMass: Kilograms, characteristicRadius: Metres, γ: Double, dustOpacity: PerKGM2): Scalar = {
    ppdSurfaceDensity(r, starMass, characteristicRadius, γ) * dustOpacity
  }

  def ppdDensity(r: Metres, z: Metres, starMass: Kilograms, characteristicRadius: Metres, γ: Double): KGPerM3 = {
    val surfaceDensity = ppdSurfaceDensity(r, starMass, characteristicRadius, γ)
    val scaleHeight = ppdPressureScaleHeight(r, starMass)
    (surfaceDensity/(scaleHeight * math.sqrt(2.0*math.Pi))) * math.exp(-(z.`²`/(2*scaleHeight.`²`)))
  }


  def ppdPressureScaleHeight(r: Metres, starMass: Kilograms): Metres = {
    // H = k * r ^ h (where h = 1.3 to 1.5)
    //
    // H = 0.035
    //
    // 0.035 = k * 1 ^ h
    //0.035 * 1^-h = k
    //0.035 = k
//    Metres(0.035 * math.pow(r.x, 1.4))
    //val tc = protoplanetaryDiskEffectiveTemperature2(r, starMass, accretionRate, starLuminosity, grazingAngle)

    //(k * tc * r*r*r)/(mu * mp * Constants.Gravitational * starMass)
    ???
  }

  /**
   * The effective temperature of a ppd is an incredibly complicated calculation. Fortunately, I found something
   * simple that was out by 2* in a solar-like environment. So I bodged it. I... I'm sorry
   *
   * You should look at the actual model I use, in ppdEffectiveTemperature. That contains full attribution.
   * @param r How far from the star
   * @param starMass The star mass
   * @param starRadius The star radius
   * @param accretionRate The disk accretion rate
   * @param starLuminosity The star luminosity
   * @param grazingAngleRoll Dice roll (0.0-1.0) that lets us vary the disk geometry. I used 0.3 for sol
   * @return Effective temperature at location.
   */
  def temperature(r: Metres, starMass: Kilograms, starRadius: Metres, accretionRate: KGPerS,
                  starLuminosity: Watts, grazingAngleRoll: Double): DegreesKelvin = 0.5 * ppdEffectiveTemperature(
    r, starMass, starRadius, accretionRate, starLuminosity, grazingAngleRoll
  )


  def fullIsolatiomMass(r: Metres, starMass: Kilograms, isolationMassConstDiceRoll: Double, surfaceDensity: KGPerM2): Kilograms = {
    val c = 1.0 + 0.2 * (isolationMassConstDiceRoll - 0.5)
    (8.0 / math.sqrt(3.0)) * math.pow(math.Pi, 1.5) * math.pow(c, 1.5) * starMass.`¯¹⸍²` * surfaceDensity.`³⸍²` * r.`³`
  }


  def massOfRingSection(r1: Metres, r2: Metres, starMass: Kilograms, characteristicRadius: Metres, γ: Double): Kilograms = {
    Integrater.integrate(r1, r2, (a: Metres, b: Metres) => {
      val diff = b - a
      val mid = a + (diff * 0.5)
      val dens = ppdSurfaceDensity(mid, starMass, characteristicRadius, γ)
      val out = dens * math.Pi * (b*b - a*a)
      out
      //pi * b * b - pi * a * a
      //pi * (b*b - a*a)
    })
  }

  /*
  //, starMass: Kilograms, characteristicRadius: Metres, γ: Double
  def approxDiskMass(r1: Metres, r2: Metres, f: (Metres) => Kilograms): Kilograms = {
    //dumb integration
    val dist = r2 - r1
    val numSteps = 20
    val stepSize = dist / numSteps
    val halfStepSize = stepSize * 0.5

    var out = Kilograms(0)
    var step = 0
    while(step < numSteps) {
      val r = r1 + halfStepSize + (stepSize * step)
      out = out + f(r)
    }
    out
  }
  */

  /*
  def dumbIntegration[X, Y](r1: X, r2: X, f: (X) => Y): Y = {
    val dist = r2 - r1
    val numSteps = 20
    val stepSize = dist / numSteps
    val halfStepSize = stepSize * 0.5

    var out = 0
    var step = 0
    while(step < numSteps) {
      val r = r1 + halfStepSize + (stepSize * step)
      out = out + f(r)
    }
    out
  }*/

  def main(args: Array[String]) {

    val surfaceDensityAtSnowLine = ppdSurfaceDensity(Units.au(2.7), Constants.SolarMass,
      ppdCharacteristicRadius(ppdMass(Constants.SolarMass), 1.6), 0.9)
    println(surfaceDensityAtSnowLine)

    val x2 = ppdEffectiveTemperature(Units.au(2.7), Constants.SolarMass, Constants.SolarRadius * 0.9,
      Units.solarMassPerYear(10e-8), Constants.SolarLuminosity * 0.8, 0.3)
    println(x2)

    //-*
    val x3 = temperature(Units.au(2.7), Constants.SolarMass, Constants.SolarRadius * 0.9,
      Units.solarMassPerYear(10e-8), Constants.SolarLuminosity * 0.8, 0.25)
    println(x3)

    val x4 = temperature(Units.au(28), Constants.SolarMass, Constants.SolarRadius * 0.9,
      Units.solarMassPerYear(10e-8), Constants.SolarLuminosity * 0.8, 0.25)
    println(x4)

    val x5 = temperature(Units.au(32), Constants.SolarMass, Constants.SolarRadius * 0.9,
      Units.solarMassPerYear(10e-8), Constants.SolarLuminosity * 0.8, 0.25)
    println(x5)
  }
}
