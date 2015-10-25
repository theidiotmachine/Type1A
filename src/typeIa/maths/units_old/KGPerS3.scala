package typeIa.maths.units_old

/**
 * Created by tim on 19/09/15.
 */
class KGPerS3(val x: Double) extends AnyVal {

}

object KGPerS3 {
  implicit class Double_KGPerS3(val x: Double) extends AnyVal{
    def *(r: KGPerS3): KGPerS3 = new KGPerS3(x * r.x)
  }

  implicit class KGPerS3_Double(val x: KGPerS3) {
    def *(r: Double): KGPerS3 = new KGPerS3(r*x.x)
  }

  implicit class KGPerS3_KGPerS3PerK4(val x: KGPerS3) {
    def /(r: KGPerS3PerK4): K4 = new K4(r.x/x.x)
  }
}
