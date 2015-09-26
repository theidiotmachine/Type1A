package typeIa.maths

/**
 *
 */
class PerKGM3PerS2(val x: Double) extends AnyVal {

}

object PerKGM3PerS2{
  def apply(d: Double) = new PerKGM3PerS2(d)

  implicit class PerKGM3PerS2_KG(val x: PerKGM3PerS2) /*extends AnyVal*/{
    def *(r: Kilograms): M3PerS2 = new M3PerS2(x.x * r.kg)
  }
}