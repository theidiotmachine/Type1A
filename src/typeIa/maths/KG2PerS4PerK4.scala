package typeIa.maths

/**
 * Created by tim on 24/09/15.
 */
class KG2PerS4PerK4(val x: Double) extends AnyVal{

}

object KG2PerS4PerK4 {
  implicit class KG2PerS4PerK4_PerS4(val x: KG2PerS4PerK4) {
    def *(r: PerS4): KG2PerK4 = new KG2PerK4(x.x * r.x)
  }
}
