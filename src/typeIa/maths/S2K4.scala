package typeIa.maths

/**
 * Created by tim on 24/09/15.
 */
class S2K4(val x: Double) extends AnyVal{

}

object S2K4 {
  implicit class S2K4_PerS(val x: S2K4) {
    def *(r: Hertz): SK4 = new SK4(x.x * r.x)
  }

  implicit class S2K4_PerS2(val x: S2K4) {
    def *(r: PerS2): K4 = new K4(x.x * r.x)
  }

  implicit class S2K4_PerS4(val x: S2K4) {
    def *(r: PerS4): PerS2K4 = new PerS2K4(x.x * r.x)
  }
}
