package typeIa.maths

import typeIa.maths.units.Units._

/**
 * Physics and maths consts
 */
final object Constants {
  val StefanBoltzmann = KGPerS3PerK4(5.670373e-8)
  val Gravitational = PerKGM3PerS2(6.67408e-11)

  val SolarRadius = new Metres(696342000)
  val SolarTemperature = new DegreesKelvin(5778)
  val SolarMass = new Kilograms(1.98855e30)
  val SolarLuminosity = new Watts(30846e26)

  val SeccondsPerYear = new Seconds(31556926)
  //val SolarLuminosityW = 3.846e26

  //[charge][mass][length][time][temp]
}
