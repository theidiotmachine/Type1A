package typeIa.maths

/**
 *
 */
class M3PerS2(val x: Double) extends AnyVal {

}

object M3PerS2{
  def apply(d: Double) = new M3PerS2(d)

  implicit class M3PerS2_KGPerS(val x: M3PerS2) /*extends AnyVal*/{
    def *(r: KGPerS): KGM3PerS3 = new KGM3PerS3(x.x * r.x)
  }

  implicit class M3PerS2_M3(val x: M3PerS2) {
    def /(r: M3): PerS2 = new PerS2(x.x/r.x)
  }
}
