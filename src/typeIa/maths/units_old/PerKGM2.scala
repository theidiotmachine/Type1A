package typeIa.maths.units_old

/**
 * Created by tim on 01/10/15.
 */
class PerKGM2(val x: Double) extends AnyVal {
  def /(r: PerKGM2): Double = x / r.x
}

object PerKGM2{
  implicit class Double_PerKGM2(val x: Double) extends AnyVal {
    def *(r: PerKGM2): PerKGM2 = new PerKGM2(x * r.x)
}
}
