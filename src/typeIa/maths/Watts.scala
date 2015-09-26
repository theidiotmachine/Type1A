package typeIa.maths

/**
 * in SI, kg m^2 s^-3
 */
class Watts(val x: Double) extends AnyVal{

}

object Watts {
  implicit class Double_Watts(val x: Double) extends AnyVal{
    def *(r: Watts): Watts = new Watts(x * r.x)
  }

  implicit class Watts_Double(val x: Watts) {
    def *(r: Double): Watts = new Watts(x.x * r)
  }

  implicit class Watts_KG2PerS4PerK4(val x: Watts) {
    def /(r: KG2PerS4PerK4): PerM2SK4 = new PerM2SK4(x.x/r.x)
  }

  implicit class Watts_KGM2PerS3PerK4(val x: Watts) {
    def /(r: KGM2PerS3PerK4): K4 = new K4(x.x / r.x)
  }

}
