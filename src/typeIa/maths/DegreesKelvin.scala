package typeIa.maths

/**
 * Temperature in Kelvin
 *
 * It's worth reading the comments in MetresDouble
 */
class DegreesKelvin(val k: Double) extends AnyVal{
  def +(r: DegreesKelvin) = new DegreesKelvin(k+r.k)
  def `⁴`: K4 = new K4(k*k*k*k)
  override def toString: String = k.toString + "K"
}

object DegreesKelvin {
  def apply(k: Double) = new DegreesKelvin(k)

  implicit class Double_DegreesKelvin(val d: Double) extends AnyVal {
    def *(r: DegreesKelvin): DegreesKelvin = new DegreesKelvin(d* r.k)
  }

  implicit class DegreesKelvin_Double(val x: DegreesKelvin) {
    def *(r: Double): DegreesKelvin = new DegreesKelvin(r*x.k)
  }
}

class K4(val k: Double) extends AnyVal{
  def ∜(): DegreesKelvin = new DegreesKelvin(math.pow(k, 0.25))
  def +(r: K4): K4 = new K4(k+r.k)
}

object K4{
  implicit class DoubleK4(val d: Double) extends AnyVal {
    def *(r: K4): K4 = new K4(d* r.k)
  }
}
