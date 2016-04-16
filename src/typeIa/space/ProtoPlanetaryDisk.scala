package typeIa.space

import typeIa.maths._
import typeIa.maths.units.Units
import typeIa.maths.units.Units._
import typeIa.space.Chemical.Chemical
import typeIa.space.Chemical._

import scala.collection._
import scala.util.Random

/**
 * Icelines
 * H₂O = 150-170K > 2.7 au
 * CO = 25K -> 28 au
 * N₂ = 24K -> 32 au
 * ⊕
 *
 * References:
 * Protoplanetary Disks and Their Evolution, Williams and Cieza = williams_disks.pdf
 * Dynamics and accretion of planetesimals, Kokubo and Id = 01A308_002.pdf
 * Ice Lines, Planetesimal Composition and Solid Surface Density in the Solar Nebula, Dodson-Robinson,
    //Willacy, Bodenheimer, Turner, Beichman = 0806.3788v2.pdf
 */
object ProtoPlanetaryDisk {

  /**
   * Yeah, the little ones are called planetesimals, but I mean c'mon. This is a hunk of stuff that will eventually
   * become a planet or asteroid or plutoid or whatever.
   */
  case class ProtoPlanet(composition: Map[Chemical, Kilograms]){
    def mass: Kilograms = composition.foldLeft(Kilograms(0))((x, t)=>x + t._2)
  }

  case class DiskSection(composition: Map[Chemical, Kilograms], massFractions: Map[Int, Double]){

    def mass: Kilograms = composition.foldLeft(Kilograms(0))((x, t)=>x + t._2)
    def maxPow = math.log10(mass.value).floor.toInt
    def accrete: DiskSection = {
      val max = maxPow

      val newMassFractions = mutable.Map[Int, Double]()
      val it = massFractions.keysIterator
      while(it.hasNext) {
        val pow = it.next()
        val r = 0.5//random

      }

      DiskSection(composition, newMassFractions.toMap)
    }

  }

  case class ChemicalData(iceLine: Metres, massRatio: Double)

  /**
   * Paraphrased from wiki, a body's Hill sphere is the region in which it dominates the attraction of satellites.
   *
   * In other words, a small body orbiting a larger body will influence satellites in this radius
   * @param semiMajorAxis The semi-major axis of the smaller body round the larger. For circular, this is the orbital radius
   * @param smallMass The mass of the smaller body
   * @param largeMass The mass of the larger body
   * @return The hill radius in m
   */
  def hillRadius(semiMajorAxis: Metres, smallMass: Kilograms, largeMass: Kilograms): Metres = {
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

  private def ppdiskAccretionTemperature(r: Metres, z: Metres, starMass: Kilograms, diskMass: Kilograms, accretionRate: KGPerS,
                                              dustToGasRatio: Double,
                                              avgOpacityAtWavelengthsOfDustThermalEmission: PerKGM2,
                                              characteristicRadius: Metres, γ: Double): DegreesKelvin = {
    val surfaceDensity = ppdSurfaceDensity(r, diskMass, characteristicRadius, γ)
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
  def ppdMass(starMass: Kilograms, massRoll: Double): Kilograms = {
    //the median ratio of disk to stellar mass is 0.9% [Protoplanetary Disks and Their Evolution, Williams and Cieza]
    //"the total disk mass around young stars is distributed in a range of 0.001–0.1M⊙", [Dynamics and accretion of planetesimals, Kokubo and Ida]

    val k = 0.1 * massRoll * starMass
    val m = 0.001 * starMass
    if(k < m)
      m
    else
      k
    //0.009 * starMass
  }

  /**
   * surface density at point
   * @param r distance from centre
   * @param diskMass disk mass
   * @param characteristicRadius the edge of the dense stuff.
   * @param γ the radial dependence of the disk viscosity. "From larger samples, Andrews et al. (2009, 2010b) find a tight
   *          range consistent with all having the same value γ = 0.9. Using a different modeling technique, however,
   *          Isella, Carpenter & Sargent (2009) find a very wide range γ = −0.8 to 0.8 with mean <γ>=0.1 in their data.
   *          Negative values of γ correspond to decreasing surface densities for R<Rc, which may be an important
   *          signature of disk evolution" [Protoplanetary Disks and Their Evolution, Williams and Cieza]
   * @return
   */
  def ppdSurfaceDensity(r: Metres, diskMass: Kilograms, characteristicRadius: Metres, γ: Double): KGPerM2 = {
    //radial dep of disk viscosity
    //v proportional tp r ^ γ
    val rOverCharacteristicRadius = r / characteristicRadius
    (2.0-γ) * (diskMass/(2.0 * math.Pi * characteristicRadius.`²`)) *
      (rOverCharacteristicRadius ^ -γ) *
      math.exp(-1.0 * (rOverCharacteristicRadius ^ (2.0-γ)))
  }

  def ppdDustSurfaceDensity(r: Metres, diskMass: Kilograms, characteristicRadius: Metres, γ: Double, gasToDustRatio: Double, iceLine: Metres): KGPerM2 = {
    val surfaceDensity = ppdSurfaceDensity(r, diskMass, characteristicRadius, γ)
    //look, this is a teeny bit naughty. [Dynamics and accretion of planetesimals, Kokubo and Ida] has two theoretical
    //definitions of surface density, one for gas and one for dust; combimed they are (basically)
    //
    //totalSurfaceDensity = (10 * icefunc(r) * dustFactor + 2400 * gasFactor) * (surfaceDensityFunc(r))
    //
    //where the surfaceDensityFunc is the old MMSN ratio of r pow -3/2, and iceFunc = if(r > iceLine) 4.2 else 1.
    //I want to use the [Protoplanetary Disks and Their Evolution, Williams and Cieza] defn above; and I want to use the
    //gas-to-dust data from [The gas mass and gas-to-dust ratio in protoplanetary disks, Williams] (which basically says
    //that the ratio is very variable and often a lot less than 100, so, you know, roll dice!) which means I am going
    //to basically ignore the above eqn except for the icefunc. The hard part then is, given all this, how do I apply?
    //
    //Guess what! I went for a dumbass approach. I mean, I think you can sometimes overthink these things...
    surfaceDensity / (if(r > iceLine)
      gasToDustRatio * 0.5
    else
      gasToDustRatio * 2.0)
  }
  
  

  /**
   * The point at which the disk starts being fuzzy and thin and not really very interesting
   * @param ppdMass Mass of the disk
   * @param p a constant. "...a correlation with disk mass, Md ∝ Rc pow 1.6±0.3" [Protoplanetary Disks and Their Evolution, Williams and Cieza]
   * @return
   */
  def ppdCharacteristicRadius(ppdMass: Kilograms, p: Double): Metres = {
    //m = k * rc ^ p (where p = 1.6 +/- 0.3)
    //m/rc ^ p = k
    //now, assumimg rc = 32au (neptune 30 au plus 2 au for general crap)
    //m = solar mass * 0.009
    //p = 1.6
    //we get
    //(solarMass * 0.009)/((32 * 149597870700.0) ^ 1.6) = k
    //(1.98855e30* 0.009)/((32 * 149597870700.0) ^ 1.6) = k
    //1.789695×10²⁸/1.94142503×10²⁰ = k
    //92184605.243293891

    //so
    //rc = m/k
    //???

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
    //val k = 92184605.243293891
    val solCR = 15//32
    val k = (1.98855e30 * 0.009) / ((solCR * 149597870700.0) ^ 1.6)
    Metres(math.pow(ppdMass.value/k, 1.0/p))
  }

  def ppdOpticalDepth(r: Metres, diskMass: Kilograms, characteristicRadius: Metres, γ: Double, dustOpacity: PerKGM2): Scalar = {
    ppdSurfaceDensity(r, diskMass, characteristicRadius, γ) * dustOpacity
  }

  def ppdDensity(r: Metres, z: Metres, starMass: Kilograms, diskMass: Kilograms, characteristicRadius: Metres, γ: Double): KGPerM3 = {
    val surfaceDensity = ppdSurfaceDensity(r, diskMass, characteristicRadius, γ)
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

  /**
   * This isolation mass comes from [https://ay201b.wordpress.com/2011/04/27/isolation-mass/]. It's a very simple equation
   * that simply assumes a protoplanet sweeps up all the dust in its Hill radius (hence the star component).
   * @param r How far away the pp is
   * @param starMass Duh
   * @param isolationMassConstDiceRoll Because the equation uses 'a const of order unity' we throw dice to add some fun in
   * @param dustSurfaceDensity The surface density of the dust at that point
   * @return
   */

  def fullIsolatiomMass(r: Metres, starMass: Kilograms, isolationMassConstDiceRoll: Double, dustSurfaceDensity: KGPerM2): Kilograms = {
    val c = 1.0 + 0.2 * (isolationMassConstDiceRoll - 0.5)
    (8.0 / math.sqrt(3.0)) * math.pow(math.Pi, 1.5) * math.pow(c, 1.5) * starMass.`¯¹⸍²` * dustSurfaceDensity.`³⸍²` * r.`³`
  }

  /**
   * This comes from [Dynamics and accretion of planetesimals, Kokubo and Ida]. I prefer it to the above because it has
   * a spacing built in, but it's dumb as rocks - it's just the area of the circle bit at a of width b. It also assumes 
   * constant surface density, which is a bit sad.
   * @param a
   * @param b
   * @param dustSurfaceDensity
   * @return
   */
  def ppIsolationMass(a: Metres, b: Metres, dustSurfaceDensity: KGPerM2): Kilograms = {
    2.0 * math.Pi * a * b * dustSurfaceDensity
  }

  private def findLine(starMass: Kilograms, starRadius: Metres, accretionRate: KGPerS,
               starLuminosity: Watts, grazingAngleRoll: Double, start: Metres, end: Metres, minTemp: DegreesKelvin,
                maxTemp: DegreesKelvin): Metres = {
    var min = start
    var max = end
    var v = ((max - min) * 0.5) + min
    var t = temperature(v, starMass, starRadius, accretionRate, starLuminosity, grazingAngleRoll)

    while (t < minTemp || t > maxTemp) {
      if(t < minTemp)
        max = v
      else
        min = v
      v = ((max - min) * 0.5) + min
      t = temperature(v, starMass, starRadius, accretionRate, starLuminosity, grazingAngleRoll)
    }
    v
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
    val diskMass = ppdMass(Constants.SolarMass, 0.09)
    println("dm - " + diskMass)

    val characteristicRadius = ppdCharacteristicRadius(diskMass, 1.6)
    println("cr = " + Units.toau(characteristicRadius).toString + "au")

    val accretionRate = Units.solarMassesPerYear(10e-8)
    val solarRadius = Constants.SolarRadius * 0.9
    val solarLuminosity: Watts = Constants.SolarLuminosity * 0.8
    val grazingAngleRoll: Double = 0.25
    val snowLine = findLine(Constants.SolarMass, solarRadius, accretionRate,
      solarLuminosity, grazingAngleRoll, Units.Metres(0), characteristicRadius * 2.0, Units.DegreesKelvin(150), Units.DegreesKelvin(170))
    println("sl = " + Units.toau(snowLine).toString + "au")


    //'dust line' is not a thing, but I mean it makes sense. "800 K [...] the minimum temperature for silicate crys-
    //tallization' [Ice Lines, Planetesimal Composition and Solid Surface Density in the Solar Nebula, Dodson-Robinson,
    //Willacy, Bodenheimer, Turner, Beichman]. Iron is very approximately the same, so this is the iron and dust line
    val dustLine = findLine(Constants.SolarMass, solarRadius, accretionRate,
        solarLuminosity, grazingAngleRoll, Units.Metres(0), snowLine, Units.DegreesKelvin(800), Units.DegreesKelvin(805))
    println("dl = " + Units.toau(dustLine).toString + "au")

    //I got these from [DENSITY OF METHANE AND NITROGEN AT DIFFERENT TEMPERATURES, Satorre, Domingo, Luna, Santonja]
    val ch4IceLine = findLine(Constants.SolarMass, solarRadius, accretionRate,
      solarLuminosity, grazingAngleRoll, snowLine, characteristicRadius * 2.0, Units.DegreesKelvin(35), Units.DegreesKelvin(36))
    println("ch4l = " + Units.toau(ch4IceLine).toString + "au")

    //24, according to [THE MEASURED COMPOSITIONS OF URANUS AND NEPTUNE FROM THEIR FORMATION ON THE CO
    // ICELINE, Ali-Dib, Mousis, Petit, Lunine]. 22 according to [DENSITY OF METHANE AND NITROGEN AT
    // DIFFERENT TEMPERATURES, Satorre, Domingo, Luna, Santonja]
    val n2IceLine = findLine(Constants.SolarMass, solarRadius, accretionRate,
      solarLuminosity, grazingAngleRoll, snowLine, characteristicRadius * 3.0, Units.DegreesKelvin(22), Units.DegreesKelvin(24))
    println("n2l = " + Units.toau(n2IceLine).toString + "au")

    //25 according to [THE MEASURED COMPOSITIONS OF URANUS AND NEPTUNE FROM THEIR FORMATION ON THE CO
    // ICELINE, Ali-Dib, Mousis, Petit, Lunine]
    val coIceLine = findLine(Constants.SolarMass, solarRadius, accretionRate,
      solarLuminosity, grazingAngleRoll, snowLine, characteristicRadius * 2.0, Units.DegreesKelvin(25), Units.DegreesKelvin(26))
    println("col = " + Units.toau(coIceLine).toString + "au")

    //needs to be 50 - 100. From [The gas mass and gas-to-dust ratio in protoplanetary disks, Williams] (which basically says
    //that the ratio is very variable and often a lot less than 100, so, you know, roll dice!)
    val gasToDustRatio: Double = 75
    val gasFraction = gasToDustRatio/ (gasToDustRatio + 1.0)
    val dustFraction = 1.0 / (gasToDustRatio + 1.0)

    //FIXME numbers!
    val chemData = Map[Chemical, ChemicalData](
    Hydrogen -> ChemicalData(Units.au(100000), gasFraction * 0.4),
    Helium -> ChemicalData(Units.au(100000), gasFraction * 0.4),
    CarbonMonoxide -> ChemicalData(coIceLine, gasFraction * 0.04),
    Water -> ChemicalData(snowLine, gasFraction * 0.04),
    Ammonia -> ChemicalData(coIceLine, gasFraction * 0.04),
    Methane -> ChemicalData(ch4IceLine, gasFraction * 0.04),
    Nitrogen -> ChemicalData(ch4IceLine, gasFraction * 0.04),
    Silicates -> ChemicalData(dustLine, dustFraction * 0.5),
    Iron -> ChemicalData(dustLine, dustFraction * 0.5)
    )

    val γ: Double = 0.9
    val surfaceDensityAtSnowLine = ppdSurfaceDensity(Units.au(2.7), diskMass,
      characteristicRadius, γ)
    println("sd@sl = " + surfaceDensityAtSnowLine)

    //my dust line is a dumbass point at which it's too hot for silicates to form
    val start = Units.toau(dustLine)
    val stepau = 0.1

    //generate a bunch of planetesimals (<<0.1 M⊕) and protoplanets (~0.1M⊕)
    val seed: Long = 100
    val auFrac = 0.4
    val r = new Random(seed)
    var w = r.nextDouble() * auFrac
    var k = start + w * 0.5
    var totMass = Units.Kilograms(0)
    while(k < Units.toau(characteristicRadius * 2.0)) {
      val a = Units.au(k + w * 0.5)

      val composition = chemData.map(t=>{
        val (chem, ChemicalData(iceLine, frac)) = t
        if(a > iceLine)
          (chem, ppIsolationMass(a, Units.au(w), ppdSurfaceDensity(a, diskMass * frac, characteristicRadius, γ)))
        else
          (chem, Kilograms(0))
      })

      val p = ProtoPlanet(composition)

      val m = p.mass
      if(a <= Units.au(1.5))
        totMass += m
      println(k.toString + " au - " + Units.toEarthMasses(m).toString + " M⊕" )
      k += w
      w = r.nextDouble() * auFrac
    }

    //m - 0.387 au, 0.055 m(+)
    //v - 0.723 au 0.815 m(+)
    //e - 1 au 1 m(+)
    //l - 1 au 0.012 m(+)
    //m - 1.524 au 0.107 m(+)
    //a - ? 4<% m 0.00048 m(+)
    val terrestrialMass = Units.earthMasses(0.005 + 0.815 + 1 + 0.012 + 0.107)

    //earth, saturn, mars, venus, mercury + jupiter + neptune + uranus - problem is Uranus is 0.55 M(+) rock, 9.3 - 13.5 M(+) ice.
    val solarsystemSolidMass = Units.earthMasses(1 + (9 + (22-9)/2) + 0.1 + 0.8 + 0.055 + (12 + (45-12)/2) + 1.2 + 0.55)
    println(totMass / terrestrialMass)
    println("tm - " + totMass )
    /*
    val b = Units.au(stepau)
    for(i <- start.to(Units.toau(characteristicRadius), stepau) ) {
      val a = Units.au(i)
      val dustSurfaceDensity = ppdDustSurfaceDensity(a, diskMass, characteristicRadius, γ, gasToDustRatio, snowLiner)
      val m = ppIsolationMass(a, b, dustSurfaceDensity)
      println(i.toString + "au - " + Units.toEarthMasses(m).toString)
    }*/


    /*
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
*/
    //
    //val rad =
  }
}
