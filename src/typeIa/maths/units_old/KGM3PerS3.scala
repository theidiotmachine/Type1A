package typeIa.maths.units_old

/**
 * Created by tim on 19/09/15.
 */
class KGM3PerS3(val x: Double) extends AnyVal {

}

object KGM3PerS3 {
  implicit class KGM3PerS3_M3(val x: KGM3PerS3) /*extends AnyVal*/{
    def /(r: M3): KGPerS3 = new KGPerS3(x.x / r.x)
  }
}
