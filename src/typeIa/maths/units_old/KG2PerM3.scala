package typeIa.maths.units_old

/**
 * Created by tim on 07/10/15.
 */
class KG2PerM3(val x: Double) extends AnyVal{

}

object KG2PerM3{
  implicit class KG2PerM3_M3(val x: KG2PerM3) {
    def *(r: M3): KG2 = new KG2(x.x * r.x)
  }
}
