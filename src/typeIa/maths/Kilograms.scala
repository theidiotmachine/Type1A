package typeIa.maths

/**
 * Unit of mass. Duh.
 *
 * It's worth reading the comments in MetresDouble
 */
class Kilograms(val kg: Double) extends AnyVal {
  def /(r: Kilograms): Double = kg / r.kg
  //def *(r: Double): Kilograms = new Kilograms(kg * r)
}

object Kilograms {
  def apply(d: Double) = new Kilograms(d)

  implicit class Double_Kilograms(val d: Double) extends AnyVal{
    def *(r: Kilograms): Kilograms = new Kilograms(d* r.kg)
  }

  implicit class Kilograms_Double(val kg: Kilograms) /*extends AnyVal*/{
    def *(r: Double): Kilograms = new Kilograms(kg.kg*r)
  }

  implicit class Kilograms_Seconds(val x: Kilograms) {
    def /(r: Seconds): KGPerS = new KGPerS(x.kg / r.x)
  }
}