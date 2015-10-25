package typeIa.maths.units_old

/**
 * Created by tim on 30/09/15.
 */
class KGPerM2(val x: Double) extends AnyVal {
  override def toString: String = x.toString + " kg m⁻²"
  def to3Over2(): KG3_2PerM3 = new KG3_2PerM3(math.pow(x, 1.5))
}

object KGPerM2{
  implicit class Double_KGPerM2(val x: Double) extends AnyVal{
    def *(r: KGPerM2): KGPerM2 = new KGPerM2(x * r.x)
  }

  implicit class KGPerM2_Double(val x: KGPerM2) {
    def *(r: Double): KGPerM2 = new KGPerM2(x.x * r)
  }

  implicit class KGPerM2_PerKGM2(val x: KGPerM2) {
    def *(r: PerKGM2): Double = x.x * r.x
  }

  implicit class KGPerM2_Metres(val x: KGPerM2) {
    def /(r: Metres): KGPerM3 = new KGPerM3(x.x / r.x)
  }

  implicit class KGPerM2_M2(val x: KGPerM2) {
    def *(r: M2): Kilograms = new Kilograms(x.x * r.x)
  }
}



