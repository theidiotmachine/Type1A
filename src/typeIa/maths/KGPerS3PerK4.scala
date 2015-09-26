package typeIa.maths

/**
 * Created by tim on 19/09/15.
 */
class KGPerS3PerK4(val x: Double) extends AnyVal {

}

object KGPerS3PerK4{
  implicit class Double_KGPerS3PerK4(val x: Double) extends AnyVal{
    def *(r: KGPerS3PerK4): KGPerS3PerK4 = new KGPerS3PerK4(x * r.x)
    def /(r: KGPerS3PerK4): PerKGS3K4 = new PerKGS3K4(x / r.x)
  }

  implicit class KGPerS3PerK4_KGPerS(val x: KGPerS3PerK4) {
    def *(r: KGPerS): KG2PerS4PerK4 = new KG2PerS4PerK4(x.x * r.x)
  }

  implicit class KGPerS3PerK4_M2(val x: KGPerS3PerK4) {
    def *(r: M2): KGM2PerS3PerK4 = new KGM2PerS3PerK4(x.x * r.x)
  }
}
