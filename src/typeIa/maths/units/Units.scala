package typeIa.maths.units

import typeIa.maths.Constants
import typeIa.maths.units.Reals._

import scala.language.implicitConversions

/**
 * This is obviously a heavily modified version of Jesper Nordenberg's metascala.Units class (hence the licence). You
 * will find the following differences:
 *
 * 1 - AnyVal. Yay!
 * 2 - Reals rather than integers. I had one formula that had a local value to the half; so here we are
 * 3 - Lots of lovely operators. I found the more my code looked like the maths I was reproducing, the easier it was
 * to check. It's a bit sad that the root operators are not unary.
 * 4 - Just six SI units. I actually have not used Moles or Candelas yet and I am considering removing them. Generally
 * the fewer args you have, the easier it is to read.
 * 5 - Measures in terms of the units, not what they measure. Because I am dealing in Metres, Au and Parsecs, I found it
 * vital to be specific, so I've changed all the names. I also have some helper functions for creating various values
 * from those units.
 * 6 - Lot's of weirdo unit short cuts
 *
 */
object Units{
  /**
   * Used for evidence
   */
  trait Q[M <: MReal, KG <: MReal, S <: MReal, K <: MReal, Mol <: MReal, CD <: MReal]

  //yay, anyval
  case class Quantity[M <: MReal, KG <: MReal, S <: MReal, K <: MReal, Mol <: MReal, CD <: MReal](value: Double) extends AnyVal {
    type This = Quantity[M, KG, S, K, Mol, CD]

    def +(m: This) = Quantity[M, KG, S, K, Mol, CD](value + m.value)

    def -(m: This) = Quantity[M, KG, S, K, Mol, CD](value - m.value)

    def *[M2 <: MReal, KG2 <: MReal, S2 <: MReal, K2 <: MReal, Mol2 <: MReal, CD2 <: MReal](m: Quantity[M2, KG2, S2, K2, Mol2, CD2]) =
      Quantity[M + M2, KG + KG2, S + S2, K + K2, Mol + Mol2, CD + CD2](value * m.value)

    def /[M2 <: MReal, KG2 <: MReal, S2 <: MReal, K2 <: MReal, Mol2 <: MReal, CD2 <: MReal](
                                                                                       m: Quantity[M2, KG2, S2, K2, Mol2, CD2]) =
      Quantity[M - M2, KG - KG2, S - S2, K - K2, Mol - Mol2, CD - CD2](value / m.value)

    def apply(v: Double) = Quantity[M, KG, S, K, Mol, CD](v * value)

    def `²` = Quantity[M * _2_0, KG * _2_0, S * _2_0, K * _2_0, Mol * _2_0, CD * _2_0](value * value)
    def √  = Quantity[M / _2_0, KG / _2_0, S / _2_0, K / _2_0, Mol / _2_0, CD / _2_0](math.sqrt(value))
    def `¹⸍²`  = Quantity[M / _2_0, KG / _2_0, S / _2_0, K / _2_0, Mol / _2_0, CD / _2_0](math.sqrt(value))
    def `¯¹⸍²`  = Quantity[--[M *_0_5], --[KG * _0_5], --[S * _0_5], --[K*_0_5], --[Mol*_0_5], --[CD*_0_5]](1/math.sqrt(value))
    def `³` = Quantity[M * _3_0, KG * _3_0, S * _3_0, K * _3_0, Mol * _3_0, CD * _3_0](value * value * value)
    def `³⸍²`  = Quantity[M * _1_5, KG * _1_5, S * _1_5, K * _1_5, Mol * _1_5, CD * _1_5](math.sqrt(value))
    def ∛ = Quantity[M / _3_0, KG / _3_0, S / _3_0, K / _3_0, Mol / _3_0, CD / _3_0](math.cbrt(value))
    def `⁴` = Quantity[M * _4_0, KG * _4_0, S * _4_0, K * _4_0, Mol * _4_0, CD * _4_0](value*value*value*value)
    def ∜ = Quantity[M / _4_0, KG / _4_0, S / _4_0, K / _4_0, Mol / _4_0, CD / _4_0](math.pow(value, 0.25))

    def unary_- = Quantity[M, KG, S, K, Mol, CD](-value)

    def mString(implicit p: Printable[M]): String = "m" + p.print

    override def toString: String = {
      value.toString
    }

    /**
     * pow function. We only provide this for scalars. If you want to do powers, use the operators above
     * @param r What to raise to
     * @param p Implicit evidence to force you to be a scalar
     * @return What you get
     */
    def ^(r: Double)(implicit p: Q[M, KG, S, K, Mol, CD] =:= Q[_0_0, _0_0, _0_0, _0_0, _0_0, _0_0]) = new Scalar(math.pow(value, r))
  }

  //this allows us to type kg(2)
  implicit def measure(v: Double): Quantity[_0_0, _0_0, _0_0, _0_0, _0_0, _0_0] = Quantity[_0_0, _0_0, _0_0, _0_0, _0_0, _0_0](v)
  implicit def measure(v: Int): Quantity[_0_0, _0_0, _0_0, _0_0, _0_0, _0_0] = Quantity[_0_0, _0_0, _0_0, _0_0, _0_0, _0_0](v.toDouble)
  implicit def scalar(v: Quantity[_0_0, _0_0, _0_0, _0_0, _0_0, _0_0]): Double = v.value

  val Metres = Quantity[_1_0, _0_0, _0_0, _0_0, _0_0, _0_0](1)
  val Kilograms = Quantity[_0_0, _1_0, _0_0, _0_0, _0_0, _0_0](1)
  val Seconds = Quantity[_0_0, _0_0, _1_0, _0_0, _0_0, _0_0](1)
  val DegreesKelvin = Quantity[_0_0, _0_0, _0_0, _1_0, _0_0, _0_0](1)
  val Moles = Quantity[_0_0, _0_0, _0_0, _0_0, _1_0, _0_0](1)
  val Candelas = Quantity[_0_0, _0_0, _0_0, _0_0, _0_0, _1_0](1)
  val Watts = Quantity[_2_0, _1_0, _M3_0, _0_0, _0_0, _0_0](1)//kg m^2 s^-3
  val KGPerS = Quantity[_0_0, _1_0, _M1_0, _0_0, _0_0, _0_0](1)
  val KGPerS3PerK4 = Quantity[_0_0, _1_0, _M3_0, _M4_0, _0_0, _0_0](1)
  val PerKGM3PerS2 = Quantity[_3_0, _M1_0, _M2_0, _0_0, _0_0, _0_0](1)

  type Metres = Quantity[_1_0, _0_0, _0_0, _0_0, _0_0, _0_0]
  type Kilograms = Quantity[_0_0, _1_0, _0_0, _0_0, _0_0, _0_0]
  type Seconds = Quantity[_0_0, _0_0, _1_0, _0_0, _0_0, _0_0]
  type DegreesKelvin = Quantity[_0_0, _0_0, _0_0, _1_0, _0_0, _0_0]
  type Moles = Quantity[_0_0, _0_0, _0_0, _0_0, _1_0, _0_0]
  type Candelas = Quantity[_0_0, _0_0, _0_0, _0_0, _0_0, _1_0]
  type Watts = Quantity[_2_0, _1_0, _M3_0, _0_0, _0_0, _0_0]
  type KGPerS = Quantity[_0_0, _1_0, _M1_0, _0_0, _0_0, _0_0]
  type KGPerM2 = Quantity[_M2_0, _1_0, _0_0, _0_0, _0_0, _0_0]
  type KGPerM3 = Quantity[_M3_0, _1_0, _0_0, _0_0, _0_0, _0_0]
  type PerKGM2 = Quantity[_2_0, _M1_0, _0_0, _0_0, _0_0, _0_0]
  type Scalar = Quantity[_0_0, _0_0, _0_0, _0_0, _0_0, _0_0]
  
  def au(au: Double): Metres = {
    new Metres(au * 149597870700.0)
  }

  def solarMassPerYear(x: Double): KGPerS = {
    x * Constants.SolarMass / Constants.SeccondsPerYear
  }
}
