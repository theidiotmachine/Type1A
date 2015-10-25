package typeIa.maths.units_old

/**
 * Created by tim on 24/09/15.
 */
class PerKGS3K4(val x: Double) extends AnyVal {

}

object PerKGS3K4{
  implicit class PerKGS3K4_KGPerS(val x: PerKGS3K4) {
    def *(r: KGPerS): S2K4 = new S2K4(x.x * r.x)
  }
}
