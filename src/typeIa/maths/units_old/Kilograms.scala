package typeIa.maths.units_old

/**
 * Unit of mass. Duh.
 *
 * It's worth reading the comments in MetresDouble
 */
class Kilograms(val x: Double) extends AnyVal {
  def /(r: Kilograms): Double = x / r.x
  def √(): K1_2 = new K1_2(math.sqrt(x))
  def +(r: Kilograms): Kilograms = new Kilograms(x + r.x)
  def -(r: Kilograms): Kilograms = new Kilograms(x - r.x)
  def invSqrt: PerKG1_2 = new PerKG1_2(math.pow(x, -0.5))
}

object Kilograms {
  def apply(d: Double) = new Kilograms(d)

  implicit class Double_Kilograms(val x: Double) extends AnyVal{
    def *(r: Kilograms): Kilograms = new Kilograms(x * r.x)
  }

  implicit class Kilograms_Double(val x: Kilograms) /*extends AnyVal*/{
    def *(r: Double): Kilograms = new Kilograms(x.x*r)
  }

  implicit class Kilograms_Metres(val x: Kilograms) /*extends AnyVal*/{
    def *(r: Metres): KGM = new KGM(x.x*r.x)
  }

  implicit class Kilograms_Seconds(val x: Kilograms) {
    def /(r: Seconds): KGPerS = new KGPerS(x.x / r.x)
  }

  implicit class Kilograms_M2(val x: Kilograms) {
    def /(r: M2): KGPerM2 = new KGPerM2(x.x / r.x)
  }
}

class KG2(val x: Double) extends AnyVal

class K1_2(val x: Double) extends AnyVal{

}

class PerKG1_2(val x: Double) extends AnyVal{

}

object PerKG1_2 {
  implicit class Double_PerKG1_2(val x: Double) extends AnyVal{
    def *(r: PerKG1_2): PerKG1_2 = new PerKG1_2(x * r.x)
  }

  implicit class PerKG1_2_KG3_2PerM3 (x: PerKG1_2) {
    def *(r: KG3_2PerM3): KGPerM3 = new KGPerM3(x.x * r.x)
  }
}