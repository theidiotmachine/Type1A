package typeIa.maths.units_old

/**
 * Distance in m, represented as a double for fiddly maths.
 *
 * This, like all the other classes in here, heavily uses implicits because type erasure stops me from being able to
 * have multiple implementations of the same function taking and returning different value types, because of type
 * erasure. So, if I were to have
 *
 * def *(r: MetresDouble): MD2 = new MD2(m*r.m)
 * def *(r: Double): MetresDouble = new MetresDouble(m.m*r)
 *
 * both would compile down to a function called 'mult' (or whatever) taking one double and returning a double, causing
 * a Java error. This is a teeny bit irritating because of course I'm hoping that function never gets called.
 *
 * So, we have implicit classes. Because we can't have value classes that wrap other value classes many of these
 * implicit classes are not themselves value classes, which means unoptimised I will pick up an allocation. I'm hoping
 * escape analysis will pick that up. I see comments that dotty might fix this.
 */
class Metres(val x: Double) extends AnyVal {
  def `²`: M2 = new M2(x*x)
  def `³`: M3 = new M3(x*x*x)
  def *(r: Metres): M2 = new M2(x*r.x)
  def √(): M1_2 = new M1_2(math.sqrt(x))
  def invSqrt: PerM1_2 = new PerM1_2(math.pow(x, -0.5))

  def +(r: Metres): Metres = new Metres(x+r.x)
  def -(r: Metres): Metres = new Metres(x-r.x)
  def /(r: Metres): Double = x/r.x
  def unary_-():Metres = new Metres(-x)
}

object Metres {
  def apply(m: Long) = new Metres(m.toDouble)
  def apply(m: Double) = new Metres(m)

  implicit class Double_Metres(val x: Double) extends AnyVal{
    def *(r: Metres): Metres = new Metres(x* r.x)
  }

  implicit class Metres_Double(val x: Metres) /*extends AnyVal*/{
    def *(r: Double): Metres = new Metres(x.x * r)
    def /(r: Double): Metres = new Metres(x.x / r)
  }

  /*
  def au(au: Double): Metres = {
    new Metres(au * 149597870700.0)
  }
  */

}

class M2(val x: Double) extends AnyVal {
  def *(r: Double): M2 = new M2(x * r)
  def /(r: M2): Double = x/r.x
  def -(r: M2): M2 = new M2(x-r.x)
  def unary_-():M2 = new M2(-x)
}

object M2{
  implicit class Double_M2(val x: Double) extends AnyVal{
    def *(r: M2): M2 = new M2(x* r.x)
  }

  implicit class M2_KGPerS3PerK4(val x: M2){
    def *(r: KGPerS3PerK4): KGM2PerS3PerK4 = new KGM2PerS3PerK4(x.x * r.x)
  }
}

class M3(val x: Double) extends AnyVal{

}

class M1_2(val x: Double) extends AnyVal{

}

class PerM1_2(val x: Double) extends AnyVal{

}

