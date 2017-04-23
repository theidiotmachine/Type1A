package typeIa.space

import typeIa.maths._
import typeIa.maths.units.{AU, Units}
import typeIa.maths.units.Units._
import typeIa.space.Chemical.{Chemical, _}

import scala.collection.mutable.ArrayBuffer
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
 * The resonance overlap criterion and the onset of stochastic behaviour in the restricted three body problem, Wisdom
 */
object ProtoPlanetaryDisk {

  case class Resonance(a: Int, b: Int)

  /**
   * Yeah, the little ones are called planetesimals, but I mean c'mon. This is a hunk of stuff that will eventually
   * become a planet or asteroid or plutoid or whatever.
   */
  case class ProtoPlanet(composition: Map[Chemical, Kilograms]){
    def mass: Kilograms = composition.foldLeft(Kilograms(0))((x, t)=>x + t._2)
    def rockMass: Kilograms = composition(Chemical.Silicates) + composition(Chemical.Iron)
  }

  /**
    * Disk section base class
    */
  trait DiskSection {
    val r1: Metres
    val r2: Metres

    def accretable: Boolean
  }

  /**
    * Disk section which simply contains unchanged disk stuff.
    * @param r1 inner radius
    * @param r2 outer radius
    * @param chemData the chemical composition of the disk
    */
  case class SimpleDiskSection(r1: Metres, r2: Metres, chemData: Map[Chemical, ChemicalData]) extends DiskSection {
    override def accretable: Boolean = true

    override def toString: String = {
      "DiskSection from " + AU.au(r1) + " to " + AU.au(r2)
    }

    //def get
  }

  trait LumpyDiskSection extends DiskSection {
    val composition: Map[Chemical, Kilograms]
    val mass: Kilograms = composition.foldLeft(Kilograms(0))((x, t)=>x + t._2)
    val rockMass: Kilograms = composition(Chemical.Silicates) + composition(Chemical.Iron)

    override def accretable: Boolean = false
  }

  /**
    * A disk section with a single proto planet in it
    * @param r1 inner radius
    * @param r2 outer radius
    * @param r actual orbital radius of the pp
    * @param composition what the pp is made of
    */
  class ProtoplanetDiskSection(val r1: Metres, val r2: Metres, r: Metres, val composition: Map[Chemical, Kilograms],
                              solarMass: Kilograms) extends LumpyDiskSection {


    override def toString: String = {
      "Protoplanet at " + AU.au(r) + "; mass: " + Units.toEarthMasses(mass).toString +
        " rock mass: " + Units.toEarthMasses(rockMass)
    }


    val orbitalPeriod: Seconds = 2.0 * math.Pi * (r.`³`/(solarMass * Constants.Gravitational)).√

    /**
      * Given this orbit, find the pair of distances from the sun that it would resonate at
      *
      * This is just a straight rework of the keplerian circular orbital period. It's sooo great that everything is
      * circular
      * @param resonance a resonance pair
      * @param solarMass the solar mass
      * @return
      */
    def getResonanceOrbitalDist(resonance: Resonance, solarMass: Kilograms): (Metres, Metres) = {
      //period = 2.0 * math.Pi * √(r³/(solarMass * Constants.Gravitational))
      //so when period * resonance.a == period2 * resonance.b
      //(period * resonance.a)/resonance.b = period2
      //(2.0 * math.Pi * √(r³/(solarMass * Constants.Gravitational)) * resonance.a)/resonance.b = 2.0 * math.Pi * √(r2³/(solarMass * Constants.Gravitational))
      //(√(r³/(solarMass * Constants.Gravitational)) * resonance.a)/resonance.b = √(r2³/(solarMass * Constants.Gravitational))
      //((√(r³/(solarMass * Constants.Gravitational)) * resonance.a)/resonance.b)^2 = r2³/(solarMass * Constants.Gravitational)
      //(((√(r³/(solarMass * Constants.Gravitational)) * resonance.a)/resonance.b)^2)*(solarMass * Constants.Gravitational) = r2³
      //∛((((√(r³/(solarMass * Constants.Gravitational)) * resonance.a)/resonance.b)^2)*(solarMass * Constants.Gravitational)) = r2
      val mg = solarMass * Constants.Gravitational
      (((((r.`³` / mg).√ * resonance.a) / resonance.b).`²` *mg).∛, ((((r.`³` / mg).√ * resonance.b) / resonance.a).`²` *mg).∛)
    }

    /**
      * This is the classic [Wisdom 1980] formula. I am fairly sure I am using it right!
      * @param solarMass the solar mass
      * @return
      */
    def getChaoticZone(solarMass: Kilograms): Metres = {
      math.pow(1.3 * (mass / solarMass).value, 2.0/7.0) * r
    }

    def findChaoticResonanceCandidates(solarMass: Kilograms, resonances: Array[Resonance]): List[Metres] = {
      val cz = getChaoticZone(solarMass)
      val outerR = r + cz / 2.0
      val innerR = r - cz / 2.0
      resonances.foldLeft(List[Metres]())((b, r)=>{
        val (cand1, cand2) = getResonanceOrbitalDist(r, solarMass)
        val o1 = if(cand1 > innerR && cand1 < outerR)
          Option(cand1)
        else
          None
        val o2 = if(cand2 > innerR && cand2 < outerR)
          Option(cand2)
        else
          None
        b ++ o1 ++ o2
      })
    }

    val chaoticResonanceCandidates: List[Metres] = findChaoticResonanceCandidates(solarMass, kirkwoodResonances)
  }

  class PlanetesimalDiskSection(val r1: Metres, val r2: Metres,
                                val composition: Map[Chemical, Kilograms]) extends LumpyDiskSection {

  }

  /*
    case class DiskSection(composition: Map[Chemical, Kilograms],
                           /*massFractions: Map[Int, Double]*/
                          r1: Metres, r2: Metres
                           ){


      def mass: Kilograms = composition.foldLeft(Kilograms(0))((x, t)=>x + t._2)

      /*
      def maxPow: Int = math.log10(mass.value).floor.toInt
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
      */

    }
  */

  case class ChemicalData(iceLine: Metres, massRatio: Double)

  /**
   * Paraphrased from wiki, a body's Hill sphere is the region in which it dominates the attraction of satellites.
   *
   * In other words, a small body orbiting a larger body will influence satellites in this radius.
    *
    * This is for circular orbits
   * @param semiMajorAxis The semi-major axis of the smaller body round the larger. For circular, this is the orbital radius
   * @param smallMass The mass of the smaller body
   * @param largeMass The mass of the larger body
   * @return The hill radius in m
   */
  def circularHillRadius(semiMajorAxis: Metres, smallMass: Kilograms, largeMass: Kilograms): Metres = {
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
   * @param r dist from star
   * @param starMass kg
   * @param starRadius metres
   * @param accretionRate kg/s
   * @param starLuminosity watts
   * @param grazingAngleRoll a random number
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

  /*
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
  */

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
   * @param starMass star mass
   * @param r random number 0-1
   * @return
   */
  def ppdMass(starMass: Kilograms, r: Double): Kilograms = {
    //the median ratio of disk to stellar mass is 0.9% [Protoplanetary Disks and Their Evolution, Williams and Cieza]
    //"the total disk mass around young stars is distributed in a range of 0.001–0.1M⊙", [Dynamics and accretion of planetesimals, Kokubo and Ida]

    //val k = 0.1 * massRoll * starMass
    val ratio = Distributions.gumbel(r, 0.009, 0.025)
    val min = 0.001
    val max = 0.1
    if(ratio < min)
      min * starMass
    else if(ratio > max)
      max * starMass
    else
      ratio * starMass
    //0.009 * starMass
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

  /**
    * Compoistion at a given radius, r
    * @param r radius
    * @param chemData raw disk composition
    * @return
    */
  def dustComposition(r: Metres, chemData: Map[Chemical, ChemicalData]): Map[Chemical, Double] = {
    chemData.map(chemDatum=>{
      if(r > chemDatum._2.iceLine)
        chemDatum._1 -> chemDatum._2.massRatio
      else
        chemDatum._1 -> 0.0
    })
  }

  /**
    * This is a very simple surface density function that assumes the disk is of uniform composition.
    * @param r radius for the surface density calculation
    * @param diskMass disk mass
    * @param characteristicRadius characteristic radius
    * @param γ disk angle
    * @param chemData composition
    * @return
    */
  def chemDustSurfaceDensity(r: Metres, diskMass: Kilograms, characteristicRadius: Metres, γ: Double,
                             chemData: Map[Chemical, ChemicalData], dustComposition: Map[Chemical, Double]): Map[Chemical, KGPerM2] = {
    val surfaceDensity = ppdSurfaceDensity(r, diskMass, characteristicRadius, γ)
    chemData.map(chemDatum=>{
      chemDatum._1 -> surfaceDensity * dustComposition(chemDatum._1)
    })
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
    //solarMass * 0.009 = k * 32au ^ 1.6
    //1.98855e30 * 0.009 = k * (32 * 149597870700.0) ^ 1.6
    //1.98855e30 * 0.009 / (32 * 149597870700.0) ^ 1.6 = k
    //1.789695×10²⁸ / 4.787131862×10¹² ^ 1.6
    //1.789695×10²⁸ /1.94142503×10²⁰
    //92184605.243293891
    //which is obviously poop, but you know
    //val k = 92184605.243293891
    val solCR = 32
    val k = (1.98855e30 * 0.009) / ((solCR * 149597870700.0) ^ 1.6)
    Metres(math.pow(ppdMass.value/k, 1.0/p))
  }

  /*
  private def ppdOpticalDepth(r: Metres, diskMass: Kilograms, characteristicRadius: Metres, γ: Double, dustOpacity: PerKGM2): Scalar = {
    ppdSurfaceDensity(r, diskMass, characteristicRadius, γ) * dustOpacity
  }
  */
/*
  def ppdDensity(r: Metres, z: Metres, starMass: Kilograms, diskMass: Kilograms, characteristicRadius: Metres, γ: Double): KGPerM3 = {
    val surfaceDensity = ppdSurfaceDensity(r, diskMass, characteristicRadius, γ)
    val scaleHeight = ppdPressureScaleHeight(r, starMass)
    (surfaceDensity/(scaleHeight * math.sqrt(2.0*math.Pi))) * math.exp(-(z.`²`/(2*scaleHeight.`²`)))
  }
*/
/*
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
*/

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
  private [this] def temperature(r: Metres, starMass: Kilograms, starRadius: Metres, accretionRate: KGPerS,
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

  def fullIsolationMass(r: Metres, starMass: Kilograms, isolationMassConstDiceRoll: Double, dustSurfaceDensity: KGPerM2): Kilograms = {
    val c = 1.0 + 0.2 * (isolationMassConstDiceRoll - 0.5)
    (8.0 / math.sqrt(3.0)) * math.pow(math.Pi, 1.5) * math.pow(c, 1.5) * starMass.`¯¹⸍²` * dustSurfaceDensity.`³⸍²` * r.`³`
  }

  /**
   * This comes from [Dynamics and accretion of planetesimals, Kokubo and Ida]. I prefer it to the above because it has
   * a spacing built in, but it's dumb as rocks - it's just the area of the circle bit at a of width b.
   * @param a inner radius
   * @param b outer radius
   * @param dustSurfaceDensity dust surface density
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


  /**
    * Is this what they are called? These are the resonances that cause the Kirkwood gaps in the asteroid belt, anyway
    */
  val kirkwoodResonances: Array[Resonance] = Array(Resonance(2, 1), Resonance(7,3), Resonance(5, 2), Resonance(3, 1),
    Resonance(7, 6) //not actually one of the kirkwoods
  )

  def main(args: Array[String]) {

    val solarMass = Constants.SolarMass

    val diskMass = ppdMass(solarMass, 0.4)
    println("disk mass - " + diskMass)
    println("disk mass as a % of solar mass - " + (diskMass / solarMass) * 100)

    val characteristicRadius = ppdCharacteristicRadius(diskMass, 1.6)
    println("characteristic radius = " + AU.au(characteristicRadius).toString + " (neptune - 30 au)")

    val accretionRate = Units.solarMassesPerYear(10e-8)
    val solarRadius = Constants.SolarRadius * 0.9
    val solarLuminosity: Watts = Constants.SolarLuminosity * 0.8
    val grazingAngleRoll: Double = 0.5
    val snowLine = findLine(solarMass, solarRadius, accretionRate,
      solarLuminosity, grazingAngleRoll, Units.Metres(0), characteristicRadius * 10.0, Units.DegreesKelvin(150), Units.DegreesKelvin(170))
    println("snow line = " + AU.au(snowLine).toString)


    //'dust line' is not a thing, but I mean it makes sense. "800 K [...] the minimum temperature for silicate crys-
    //tallization' [Ice Lines, Planetesimal Composition and Solid Surface Density in the Solar Nebula, Dodson-Robinson,
    //Willacy, Bodenheimer, Turner, Beichman]. Iron is very approximately the same, so this is the iron and dust line
    val dustLine = findLine(solarMass, solarRadius, accretionRate,
        solarLuminosity, grazingAngleRoll, Units.Metres(0), snowLine, Units.DegreesKelvin(800), Units.DegreesKelvin(805))
    println("dust line = " + AU.au(dustLine).toString)

    //I got these from [DENSITY OF METHANE AND NITROGEN AT DIFFERENT TEMPERATURES, Satorre, Domingo, Luna, Santonja]
    val ch4IceLine = findLine(solarMass, solarRadius, accretionRate,
      solarLuminosity, grazingAngleRoll, snowLine, characteristicRadius * 10.0, Units.DegreesKelvin(35), Units.DegreesKelvin(36))
    println("methane ice line = " + AU.au(ch4IceLine).toString)

    //24, according to [THE MEASURED COMPOSITIONS OF URANUS AND NEPTUNE FROM THEIR FORMATION ON THE CO
    // ICELINE, Ali-Dib, Mousis, Petit, Lunine]. 22 according to [DENSITY OF METHANE AND NITROGEN AT
    // DIFFERENT TEMPERATURES, Satorre, Domingo, Luna, Santonja]
    val n2IceLine = findLine(solarMass, solarRadius, accretionRate,
      solarLuminosity, grazingAngleRoll, snowLine, characteristicRadius * 10.0, Units.DegreesKelvin(22), Units.DegreesKelvin(24))
    println("nitrogen ice line = " + AU.au(n2IceLine).toString)

    //25 according to [THE MEASURED COMPOSITIONS OF URANUS AND NEPTUNE FROM THEIR FORMATION ON THE CO
    // ICELINE, Ali-Dib, Mousis, Petit, Lunine]
    val coIceLine = findLine(solarMass, solarRadius, accretionRate,
      solarLuminosity, grazingAngleRoll, snowLine, characteristicRadius * 10.0, Units.DegreesKelvin(25), Units.DegreesKelvin(26))
    println("carbon monoxide ice line = " + AU.au(coIceLine).toString)

    //needs to be 50 - 100. From [The gas mass and gas-to-dust ratio in protoplanetary disks, Williams] (which basically says
    //that the ratio is very variable and often a lot less than 100, so, you know, roll dice!)
    val gasToDustRatio: Double = 75
    val gasFraction = gasToDustRatio/ (gasToDustRatio + 1.0)
    val dustFraction = 1.0 / (gasToDustRatio + 1.0)

    //FIXME numbers!
    val chemData = Map[Chemical, ChemicalData](
    Hydrogen -> ChemicalData(AU(100000), gasFraction * 0.4),
    Helium -> ChemicalData(AU(100000), gasFraction * 0.4),
    CarbonMonoxide -> ChemicalData(coIceLine, gasFraction * 0.04),
    Water -> ChemicalData(snowLine, gasFraction * 0.04),
    Ammonia -> ChemicalData(coIceLine, gasFraction * 0.04),
    Methane -> ChemicalData(ch4IceLine, gasFraction * 0.04),
    Nitrogen -> ChemicalData(ch4IceLine, gasFraction * 0.04),
    Silicates -> ChemicalData(dustLine, dustFraction * 0.5),
    Iron -> ChemicalData(dustLine, dustFraction * 0.5)
    )

    val γ: Double = 0.9

    //my dust line is a dumbass point at which it's too hot for silicates to form
    val start = AU.au(dustLine)
    val auFrac = 0.4
    val seed: Long = 100
    val random = new Random(seed)
    //var w = AU(r.nextDouble() * auFrac)

    //var k = start + AU.au(w * 0.5)


    val diskSections = ArrayBuffer[DiskSection](
      SimpleDiskSection(dustLine, characteristicRadius, chemData)
    )

    var i = 0
    while(i < 50) {
      //choose a simple disk section
      val accretables = diskSections.zipWithIndex.filter(p=>p._1.accretable)

      val (thisAccretable, idx) = accretables(random.nextInt(accretables.size))

      //split into an inner, a protoplanet, and an outer

      //place on disk that we are going to split. actually it should be random
      val r = (random.nextDouble() * (thisAccretable.r2 - thisAccretable.r1)) + thisAccretable.r1

      //the dust surface density is a summation of anything which is frozen at this point
      val dust = dustComposition(r, chemData)
      val cDSD = chemDustSurfaceDensity(r, diskMass, characteristicRadius, γ, chemData, dust)
      val aggregatedDustSurfaceDensity = cDSD.foldLeft(Units.KGPerM2(0))((b, cSDDatum)=>{ b + cSDDatum._2 })

      //here is a planetesimal. First, attempt to sweep up the full isolation mass
      val protoPlanetMass = fullIsolationMass(r, solarMass, random.nextDouble(), aggregatedDustSurfaceDensity)

      //that would make a protoplanet with this hill radius
      val hr = circularHillRadius(r, protoPlanetMass, solarMass)

      //so, make sure that we can actually generate that.
      val diskSectionsToAccrete = ArrayBuffer[(DiskSection, Int)]((thisAccretable, idx))

      //does it fall off the far edge of this disk?
      if(hr+r > thisAccretable.r2){
        var idxNext = idx + 1
        var allAccretable = true
        var thisR = thisAccretable.r2

        while(idxNext < diskSections.size && thisR < hr+r){
          val ds = diskSections(idxNext)
          if(!ds.accretable) {
            allAccretable = false
          } else if(allAccretable) {
            diskSectionsToAccrete += ((ds, idxNext))
          }
          thisR = ds.r2
          idxNext += 1
        }
      }

      //and what about the near edge
      if(r-hr < thisAccretable.r1){
        //prev
        var idxNext = idx - 1
        var allAccretable = true
        var thisR = thisAccretable.r1
        while(idxNext >= 0 && thisR > r-hr){
          val ds = diskSections(idxNext)
          if(!ds.accretable) {
            allAccretable = false
          } else if(allAccretable) {
            diskSectionsToAccrete.insert(0, (ds, idxNext))
          }
          thisR = ds.r1
          idxNext -= 1
        }
      }


      if(diskSectionsToAccrete.size == 1){
        val (oldDiskSection, oldDiskSectionIdx) = diskSectionsToAccrete(0)

        //if we only found one disk section and it fits neatly into a ring, that's easy. It's a proto planet
        /*
        if(hr+r < oldDiskSection.r2 && r-hr > oldDiskSection.r1) {
          val sd1 = SimpleDiskSection(oldDiskSection.r1, r-hr, chemData)
          val protoPlanetComposition = dust.map(dcDatum=>{ dcDatum._1 -> dcDatum._2 * protoPlanetMass })
          val pd1 = new ProtoplanetDiskSection(r-hr, r+hr, r, protoPlanetComposition, solarMass)
          val sd2 = SimpleDiskSection(r + hr, oldDiskSection.r2, chemData)

          //remove the old one
          diskSections.remove(oldDiskSectionIdx)
          //insert the new ones
          diskSections.insert(oldDiskSectionIdx, sd1, pd1, sd2)
        } else { */
          //if it doesn't fit in neatly, that means we are hanging off an edge. We're going to say that it's
          // planetesimals, because something is disrupting it from forming properly.
          val (innerSimpleDisk, newR1, planetesimalsInner) = if(r-hr < oldDiskSection.r1)
            (None, oldDiskSection.r1, true)
          else
            (Option(SimpleDiskSection(oldDiskSection.r1, r-hr, chemData)), r-hr, false)

          val (outerSimpleDisk, newR2, planetesimalsOuter) = if(r+hr > oldDiskSection.r2)
            (None, oldDiskSection.r2, true)
          else
            (Option(SimpleDiskSection(r + hr, oldDiskSection.r2, chemData)), r+hr, false)

          val ds = if(planetesimalsInner || planetesimalsOuter){
            val planetesimalMass = ppIsolationMass(newR1, newR2, aggregatedDustSurfaceDensity)
            val planetesimalComposition = dust.map(dcDatum=>{ dcDatum._1 -> dcDatum._2 * planetesimalMass })
            new PlanetesimalDiskSection(newR1, newR2, planetesimalComposition)
          }
          else{
            val protoPlanetComposition = dust.map(dcDatum=>{ dcDatum._1 -> dcDatum._2 * protoPlanetMass })
            new ProtoplanetDiskSection(newR1, newR2, r, protoPlanetComposition, solarMass)
          }

        //remove the old one
        diskSections.remove(oldDiskSectionIdx)
        //insert the new ones
        val newDiskSections = (List[DiskSection]() ++ innerSimpleDisk :+ ds) ++ outerSimpleDisk
        diskSections.insertAll(oldDiskSectionIdx,newDiskSections)
/*
        }
        */
      } else {
        println("urgh")
      }


      //now
      //val cands = pd1.chaoticResonanceCandidates
      //println("cr cands:")
      //cands.foreach(x=>println(AU.au(x)))

      /*
      fullIsolationMass(r, solarMass, random.nextDouble(),
        ppdDustSurfaceDensity(r, diskMass, characteristicRadius, γ, gasToDustRatio, snowLine))
        */
      i += 1
    }

    diskSections.foreach(
      diskSection => {
        println(diskSection)

        diskSection match {
          case pds: ProtoplanetDiskSection => println("Orbital period: "
            + Units.toEarthYears(pds.orbitalPeriod) + " years")
          case _ =>
        }
      }
    )

    //generate a bunch of planetesimals (<<0.1 M⊕) and protoplanets (~0.1M⊕)
/*
    var totMass = Units.Kilograms(0)
    var totRockMass = Units.Kilograms(0)
    while(k < AU.au(characteristicRadius)) {
      val a = AU.au(k + w * 0.5)

      val composition = chemData.map(t=>{
        val (chem, ChemicalData(iceLine, frac)) = t
        if(a > iceLine)
          (chem, frac * ppIsolationMass(a, AU.au(w), ppdSurfaceDensity(a, diskMass, characteristicRadius, γ)))
        else
          (chem, Kilograms(0))
      })

      val p = ProtoPlanet(composition)

      val m = p.mass
      totRockMass = totRockMass + p.rockMass
      if(a <= AU(1.5))
        totMass += m
      println(k.toString + " " + Units.toEarthMasses(m).toString + " M⊕" )
      k = k + w
      w = AU(r.nextDouble() * auFrac)
    }
*/
    //m - 0.387 au, 0.055 m⊕
    //v - 0.723 au 0.815 m⊕
    //e - 1 au 1 m⊕
    //l - 1 au 0.012 m⊕
    //m - 1.524 au 0.107 m⊕
    //a - ? 4<% m 0.00048 m⊕
    val terrestrialMass = Units.earthMasses(0.055 + 0.815 + 1 + 0.012 + 0.107)


    val solarsystemSolidMass = Units.earthMasses(
      //earth, saturn,          mars,   venus,  mercury + jupiter +          neptune + uranus - problem is Uranus is 0.55 M⊕ rock, 9.3 - 13.5 M⊕ ice.
      1 +      (9 + (22-9)/2) + 0.107 + 0.815 + 0.055 +   (12 + (45-12)/2) + 1.2 +     0.55
        //of course in teh time it took me to write this, there is a hypothetical new planet
        //how many other people have ever gotten to write that?
        //nine - nine is >= 10M⊕. Let's go with a uranus style make up
      + 0.5
      //hypothetical inner giant
      + 2
    )

    //println("totRockMass / solarsystemSolidMass = " + (totRockMass / solarsystemSolidMass).toString)
    println("hello")
    //println("tm - " + totMass )
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
