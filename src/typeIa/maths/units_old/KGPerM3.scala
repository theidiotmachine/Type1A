package typeIa.maths.units_old

/**
 * Created by tim on 01/10/15.
 */
class KGPerM3(val x: Double) extends AnyVal {

}

object KGPerM3{
  implicit class KGPerM3_Double(val x: KGPerM3) {
    def *(r: Double): KGPerM3 = new KGPerM3(x.x * r)
  }

  implicit class KGPerM3_M3(val x: KGPerM3) {
    def *(r: M3): Kilograms = new Kilograms(x.x * r.x)
  }
}
