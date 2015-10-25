package typeIa.maths.units

import scala.language.higherKinds

/**
 * This obviously owes a lot to Jesper Nordenberg's metascala.Unit work. I wanted two things: the possibility of
 * pretty printing the values, and fractions. This meant I decided to abandon his extremely elegant Church numerals
 * (yes you can do fractions in Church numerals, but they are fiddly and I wanted to get on with it) in favour of
 * this mostly-machine generated file. You generate it from Generator (and then fix all the broken stuff that I
 * couldn't be bothered to fix. Er, sorry).
 *
 * In practice I've not seen units higher than 6, and they are for intermediate calculations; and I can only deal with
 * halves, but again I've not seen anything smaller than that. However you can't multiply or divide by a negative
 * number (use the -- operator to get round that, if you need to) and obviously you can overflow very easily. But I
 * think it compiles quicker, it's easier to use, and I kind of don't care what you think?
 */

object Reals{
  sealed trait MReal{
    type AddR[R <: MReal] <: MReal
    type SubR[R <: MReal] <: MReal
    type MulR[R <: MReal] <: MReal
    type DivR[R <: MReal] <: MReal
    type Add_M6_0 <: MReal
    type Sub_M6_0 <: MReal
    type Mul_M6_0 <: MReal
    type Div_M6_0 <: MReal
    type Add_M5_5 <: MReal
    type Sub_M5_5 <: MReal
    type Mul_M5_5 <: MReal
    type Div_M5_5 <: MReal
    type Add_M5_0 <: MReal
    type Sub_M5_0 <: MReal
    type Mul_M5_0 <: MReal
    type Div_M5_0 <: MReal
    type Add_M4_5 <: MReal
    type Sub_M4_5 <: MReal
    type Mul_M4_5 <: MReal
    type Div_M4_5 <: MReal
    type Add_M4_0 <: MReal
    type Sub_M4_0 <: MReal
    type Mul_M4_0 <: MReal
    type Div_M4_0 <: MReal
    type Add_M3_5 <: MReal
    type Sub_M3_5 <: MReal
    type Mul_M3_5 <: MReal
    type Div_M3_5 <: MReal
    type Add_M3_0 <: MReal
    type Sub_M3_0 <: MReal
    type Mul_M3_0 <: MReal
    type Div_M3_0 <: MReal
    type Add_M2_5 <: MReal
    type Sub_M2_5 <: MReal
    type Mul_M2_5 <: MReal
    type Div_M2_5 <: MReal
    type Add_M2_0 <: MReal
    type Sub_M2_0 <: MReal
    type Mul_M2_0 <: MReal
    type Div_M2_0 <: MReal
    type Add_M1_5 <: MReal
    type Sub_M1_5 <: MReal
    type Mul_M1_5 <: MReal
    type Div_M1_5 <: MReal
    type Add_M1_0 <: MReal
    type Sub_M1_0 <: MReal
    type Mul_M1_0 <: MReal
    type Div_M1_0 <: MReal
    type Add_M0_5 <: MReal
    type Sub_M0_5 <: MReal
    type Mul_M0_5 <: MReal
    type Div_M0_5 <: MReal
    type Add_0_0 <: MReal
    type Sub_0_0 <: MReal
    type Mul_0_0 <: MReal
    type Div_0_0 <: MReal
    type Add_0_5 <: MReal
    type Sub_0_5 <: MReal
    type Mul_0_5 <: MReal
    type Div_0_5 <: MReal
    type Add_1_0 <: MReal
    type Sub_1_0 <: MReal
    type Mul_1_0 <: MReal
    type Div_1_0 <: MReal
    type Add_1_5 <: MReal
    type Sub_1_5 <: MReal
    type Mul_1_5 <: MReal
    type Div_1_5 <: MReal
    type Add_2_0 <: MReal
    type Sub_2_0 <: MReal
    type Mul_2_0 <: MReal
    type Div_2_0 <: MReal
    type Add_2_5 <: MReal
    type Sub_2_5 <: MReal
    type Mul_2_5 <: MReal
    type Div_2_5 <: MReal
    type Add_3_0 <: MReal
    type Sub_3_0 <: MReal
    type Mul_3_0 <: MReal
    type Div_3_0 <: MReal
    type Add_3_5 <: MReal
    type Sub_3_5 <: MReal
    type Mul_3_5 <: MReal
    type Div_3_5 <: MReal
    type Add_4_0 <: MReal
    type Sub_4_0 <: MReal
    type Mul_4_0 <: MReal
    type Div_4_0 <: MReal
    type Add_4_5 <: MReal
    type Sub_4_5 <: MReal
    type Mul_4_5 <: MReal
    type Div_4_5 <: MReal
    type Add_5_0 <: MReal
    type Sub_5_0 <: MReal
    type Mul_5_0 <: MReal
    type Div_5_0 <: MReal
    type Add_5_5 <: MReal
    type Sub_5_5 <: MReal
    type Mul_5_5 <: MReal
    type Div_5_5 <: MReal
    type Add_6_0 <: MReal
    type Sub_6_0 <: MReal
    type Mul_6_0 <: MReal
    type Div_6_0 <: MReal
    type Neg <: MReal
  }

  sealed trait _M6_0 extends MReal{
    type AddR[R <: MReal] = R#Sub_6_0
    type SubR[R <: MReal] = R#Add_6_0
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M6_0
    type Add_0_5 = _M5_5
    type Add_1_0 = _M5_0
    type Add_1_5 = _M4_5
    type Add_2_0 = _M4_0
    type Add_2_5 = _M3_5
    type Add_3_0 = _M3_0
    type Add_3_5 = _M2_5
    type Add_4_0 = _M2_0
    type Add_4_5 = _M1_5
    type Add_5_0 = _M1_0
    type Add_5_5 = _M0_5
    type Add_6_0 = _0_0
    type Sub_0_0 = _M6_0
    type Sub_0_5 = _NaNx
    type Sub_1_0 = _NaNx
    type Sub_1_5 = _NaNx
    type Sub_2_0 = _NaNx
    type Sub_2_5 = _NaNx
    type Sub_3_0 = _NaNx
    type Sub_3_5 = _NaNx
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _M3_0
    type Mul_1_0 = _M6_0
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _M6_0
    type Div_1_5 = _M4_0
    type Div_2_0 = _M3_0
    type Div_2_5 = _NaNx
    type Div_3_0 = _M2_0
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _M1_0
    type Neg = _6_0
  }

  implicit object _M6_0 extends Printable[_M6_0] {
    override def print: String = "⁻6"
  }

  sealed trait _M5_5 extends MReal{
    type AddR[R <: MReal] = R#Sub_5_5
    type SubR[R <: MReal] = R#Add_5_5
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M5_5
    type Add_0_5 = _M5_0
    type Add_1_0 = _M4_5
    type Add_1_5 = _M4_0
    type Add_2_0 = _M3_5
    type Add_2_5 = _M3_0
    type Add_3_0 = _M2_5
    type Add_3_5 = _M2_0
    type Add_4_0 = _M1_5
    type Add_4_5 = _M1_0
    type Add_5_0 = _M0_5
    type Add_5_5 = _0_0
    type Add_6_0 = _0_5
    type Sub_0_0 = _M5_5
    type Sub_0_5 = _M6_0
    type Sub_1_0 = _NaNx
    type Sub_1_5 = _NaNx
    type Sub_2_0 = _NaNx
    type Sub_2_5 = _NaNx
    type Sub_3_0 = _NaNx
    type Sub_3_5 = _NaNx
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _M5_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _M1_0
    type Div_6_0 = _NaNx
    type Neg = _5_5
  }

  implicit object _M5_5 extends Printable[_M5_5] {
    override def print: String = "-5.5"
  }

  sealed trait _M5_0 extends MReal{
    type AddR[R <: MReal] = R#Sub_5_0
    type SubR[R <: MReal] = R#Add_5_0
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M5_0
    type Add_0_5 = _M4_5
    type Add_1_0 = _M4_0
    type Add_1_5 = _M3_5
    type Add_2_0 = _M3_0
    type Add_2_5 = _M2_5
    type Add_3_0 = _M2_0
    type Add_3_5 = _M1_5
    type Add_4_0 = _M1_0
    type Add_4_5 = _M0_5
    type Add_5_0 = _0_0
    type Add_5_5 = _0_5
    type Add_6_0 = _1_0
    type Sub_0_0 = _M5_0
    type Sub_0_5 = _M5_5
    type Sub_1_0 = _M6_0
    type Sub_1_5 = _NaNx
    type Sub_2_0 = _NaNx
    type Sub_2_5 = _NaNx
    type Sub_3_0 = _NaNx
    type Sub_3_5 = _NaNx
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _M2_5
    type Mul_1_0 = _M5_0
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _M5_0
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _M2_0
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _M1_0
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _5_0
  }

  implicit object _M5_0 extends Printable[_M5_0] {
    override def print: String = "⁻5"
  }

  sealed trait _M4_5 extends MReal{
    type AddR[R <: MReal] = R#Sub_4_5
    type SubR[R <: MReal] = R#Add_4_5
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M4_5
    type Add_0_5 = _M4_0
    type Add_1_0 = _M3_5
    type Add_1_5 = _M3_0
    type Add_2_0 = _M2_5
    type Add_2_5 = _M2_0
    type Add_3_0 = _M1_5
    type Add_3_5 = _M1_0
    type Add_4_0 = _M0_5
    type Add_4_5 = _0_0
    type Add_5_0 = _0_5
    type Add_5_5 = _1_0
    type Add_6_0 = _1_5
    type Sub_0_0 = _M4_5
    type Sub_0_5 = _M5_0
    type Sub_1_0 = _M5_5
    type Sub_1_5 = _M6_0
    type Sub_2_0 = _NaNx
    type Sub_2_5 = _NaNx
    type Sub_3_0 = _NaNx
    type Sub_3_5 = _NaNx
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _M4_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _NaNx
    type Div_1_5 = _M3_0
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _M1_0
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _4_5
  }

  implicit object _M4_5 extends Printable[_M4_5] {
    override def print: String = "-4.5"
  }

  sealed trait _M4_0 extends MReal{
    type AddR[R <: MReal] = R#Sub_4_0
    type SubR[R <: MReal] = R#Add_4_0
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M4_0
    type Add_0_5 = _M3_5
    type Add_1_0 = _M3_0
    type Add_1_5 = _M2_5
    type Add_2_0 = _M2_0
    type Add_2_5 = _M1_5
    type Add_3_0 = _M1_0
    type Add_3_5 = _M0_5
    type Add_4_0 = _0_0
    type Add_4_5 = _0_5
    type Add_5_0 = _1_0
    type Add_5_5 = _1_5
    type Add_6_0 = _2_0
    type Sub_0_0 = _M4_0
    type Sub_0_5 = _M4_5
    type Sub_1_0 = _M5_0
    type Sub_1_5 = _M5_5
    type Sub_2_0 = _M6_0
    type Sub_2_5 = _NaNx
    type Sub_3_0 = _NaNx
    type Sub_3_5 = _NaNx
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _M2_0
    type Mul_1_0 = _M4_0
    type Mul_1_5 = _M6_0
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _M4_0
    type Div_1_5 = _NaNx
    type Div_2_0 = _M2_0
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _M1_0
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _4_0
  }

  implicit object _M4_0 extends Printable[_M4_0] {
    override def print: String = "⁻⁴"
  }

  sealed trait _M3_5 extends MReal{
    type AddR[R <: MReal] = R#Sub_3_5
    type SubR[R <: MReal] = R#Add_3_5
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M3_5
    type Add_0_5 = _M3_0
    type Add_1_0 = _M2_5
    type Add_1_5 = _M2_0
    type Add_2_0 = _M1_5
    type Add_2_5 = _M1_0
    type Add_3_0 = _M0_5
    type Add_3_5 = _0_0
    type Add_4_0 = _0_5
    type Add_4_5 = _1_0
    type Add_5_0 = _1_5
    type Add_5_5 = _2_0
    type Add_6_0 = _2_5
    type Sub_0_0 = _M3_5
    type Sub_0_5 = _M4_0
    type Sub_1_0 = _M4_5
    type Sub_1_5 = _M5_0
    type Sub_2_0 = _M5_5
    type Sub_2_5 = _M6_0
    type Sub_3_0 = _NaNx
    type Sub_3_5 = _NaNx
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _M3_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _M1_0
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _3_5
  }

  implicit object _M3_5 extends Printable[_M3_5] {
    override def print: String = "-3.5"
  }

  sealed trait _M3_0 extends MReal{
    type AddR[R <: MReal] = R#Sub_3_0
    type SubR[R <: MReal] = R#Add_3_0
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M3_0
    type Add_0_5 = _M2_5
    type Add_1_0 = _M2_0
    type Add_1_5 = _M1_5
    type Add_2_0 = _M1_0
    type Add_2_5 = _M0_5
    type Add_3_0 = _0_0
    type Add_3_5 = _0_5
    type Add_4_0 = _1_0
    type Add_4_5 = _1_5
    type Add_5_0 = _2_0
    type Add_5_5 = _2_5
    type Add_6_0 = _3_0
    type Sub_0_0 = _M3_0
    type Sub_0_5 = _M3_5
    type Sub_1_0 = _M4_0
    type Sub_1_5 = _M4_5
    type Sub_2_0 = _M5_0
    type Sub_2_5 = _M5_5
    type Sub_3_0 = _M6_0
    type Sub_3_5 = _NaNx
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _M1_5
    type Mul_1_0 = _M3_0
    type Mul_1_5 = _M4_5
    type Mul_2_0 = _M6_0
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _M6_0
    type Div_1_0 = _M3_0
    type Div_1_5 = _M2_0
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _M1_0
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _3_0
  }

  implicit object _M3_0 extends Printable[_M3_0] {
    override def print: String = "⁻³"
  }

  sealed trait _M2_5 extends MReal{
    type AddR[R <: MReal] = R#Sub_2_5
    type SubR[R <: MReal] = R#Add_2_5
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M2_5
    type Add_0_5 = _M2_0
    type Add_1_0 = _M1_5
    type Add_1_5 = _M1_0
    type Add_2_0 = _M0_5
    type Add_2_5 = _0_0
    type Add_3_0 = _0_5
    type Add_3_5 = _1_0
    type Add_4_0 = _1_5
    type Add_4_5 = _2_0
    type Add_5_0 = _2_5
    type Add_5_5 = _3_0
    type Add_6_0 = _3_5
    type Sub_0_0 = _M2_5
    type Sub_0_5 = _M3_0
    type Sub_1_0 = _M3_5
    type Sub_1_5 = _M4_0
    type Sub_2_0 = _M4_5
    type Sub_2_5 = _M5_0
    type Sub_3_0 = _M5_5
    type Sub_3_5 = _M6_0
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _M2_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _M5_0
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _M5_0
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _M1_0
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _2_5
  }

  implicit object _M2_5 extends Printable[_M2_5] {
    override def print: String = "-2.5"
  }

  sealed trait _M2_0 extends MReal{
    type AddR[R <: MReal] = R#Sub_2_0
    type SubR[R <: MReal] = R#Add_2_0
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M2_0
    type Add_0_5 = _M1_5
    type Add_1_0 = _M1_0
    type Add_1_5 = _M0_5
    type Add_2_0 = _0_0
    type Add_2_5 = _0_5
    type Add_3_0 = _1_0
    type Add_3_5 = _1_5
    type Add_4_0 = _2_0
    type Add_4_5 = _2_5
    type Add_5_0 = _3_0
    type Add_5_5 = _3_5
    type Add_6_0 = _4_0
    type Sub_0_0 = _M2_0
    type Sub_0_5 = _M2_5
    type Sub_1_0 = _M3_0
    type Sub_1_5 = _M3_5
    type Sub_2_0 = _M4_0
    type Sub_2_5 = _M4_5
    type Sub_3_0 = _M5_0
    type Sub_3_5 = _M5_5
    type Sub_4_0 = _M6_0
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _M1_0
    type Mul_1_0 = _M2_0
    type Mul_1_5 = _M3_0
    type Mul_2_0 = _M4_0
    type Mul_2_5 = _M5_0
    type Mul_3_0 = _M6_0
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _M4_0
    type Div_1_0 = _M2_0
    type Div_1_5 = _NaNx
    type Div_2_0 = _M1_0
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _2_0
  }

  implicit object _M2_0 extends Printable[_M2_0] {
    override def print: String = "⁻²"
  }

  sealed trait _M1_5 extends MReal{
    type AddR[R <: MReal] = R#Sub_1_5
    type SubR[R <: MReal] = R#Add_1_5
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M1_5
    type Add_0_5 = _M1_0
    type Add_1_0 = _M0_5
    type Add_1_5 = _0_0
    type Add_2_0 = _0_5
    type Add_2_5 = _1_0
    type Add_3_0 = _1_5
    type Add_3_5 = _2_0
    type Add_4_0 = _2_5
    type Add_4_5 = _3_0
    type Add_5_0 = _3_5
    type Add_5_5 = _4_0
    type Add_6_0 = _4_5
    type Sub_0_0 = _M1_5
    type Sub_0_5 = _M2_0
    type Sub_1_0 = _M2_5
    type Sub_1_5 = _M3_0
    type Sub_2_0 = _M3_5
    type Sub_2_5 = _M4_0
    type Sub_3_0 = _M4_5
    type Sub_3_5 = _M5_0
    type Sub_4_0 = _M5_5
    type Sub_4_5 = _M6_0
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _M1_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _M3_0
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _M4_5
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _M6_0
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _M3_0
    type Div_1_0 = _NaNx
    type Div_1_5 = _M1_0
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _1_5
  }

  implicit object _M1_5 extends Printable[_M1_5] {
    override def print: String = "-1.5"
  }

  sealed trait _M1_0 extends MReal{
    type AddR[R <: MReal] = R#Sub_1_0
    type SubR[R <: MReal] = R#Add_1_0
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M1_0
    type Add_0_5 = _M0_5
    type Add_1_0 = _0_0
    type Add_1_5 = _0_5
    type Add_2_0 = _1_0
    type Add_2_5 = _1_5
    type Add_3_0 = _2_0
    type Add_3_5 = _2_5
    type Add_4_0 = _3_0
    type Add_4_5 = _3_5
    type Add_5_0 = _4_0
    type Add_5_5 = _4_5
    type Add_6_0 = _5_0
    type Sub_0_0 = _M1_0
    type Sub_0_5 = _M1_5
    type Sub_1_0 = _M2_0
    type Sub_1_5 = _M2_5
    type Sub_2_0 = _M3_0
    type Sub_2_5 = _M3_5
    type Sub_3_0 = _M4_0
    type Sub_3_5 = _M4_5
    type Sub_4_0 = _M5_0
    type Sub_4_5 = _M5_5
    type Sub_5_0 = _M6_0
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _M0_5
    type Mul_1_0 = _M1_0
    type Mul_1_5 = _M1_5
    type Mul_2_0 = _M2_0
    type Mul_2_5 = _M2_5
    type Mul_3_0 = _M3_0
    type Mul_3_5 = _M3_5
    type Mul_4_0 = _M4_0
    type Mul_4_5 = _M4_5
    type Mul_5_0 = _M5_0
    type Mul_5_5 = _M5_5
    type Mul_6_0 = _M6_0
    type Div_0_0 = _NaNx
    type Div_0_5 = _M2_0
    type Div_1_0 = _M1_0
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _1_0
  }

  implicit object _M1_0 extends Printable[_M1_0] {
    override def print: String = "⁻1"
  }

  sealed trait _M0_5 extends MReal{
    type AddR[R <: MReal] = R#Sub_0_5
    type SubR[R <: MReal] = R#Add_0_5
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _M0_5
    type Add_0_5 = _0_0
    type Add_1_0 = _0_5
    type Add_1_5 = _1_0
    type Add_2_0 = _1_5
    type Add_2_5 = _2_0
    type Add_3_0 = _2_5
    type Add_3_5 = _3_0
    type Add_4_0 = _3_5
    type Add_4_5 = _4_0
    type Add_5_0 = _4_5
    type Add_5_5 = _5_0
    type Add_6_0 = _5_5
    type Sub_0_0 = _M0_5
    type Sub_0_5 = _M1_0
    type Sub_1_0 = _M1_5
    type Sub_1_5 = _M2_0
    type Sub_2_0 = _M2_5
    type Sub_2_5 = _M3_0
    type Sub_3_0 = _M3_5
    type Sub_3_5 = _M4_0
    type Sub_4_0 = _M4_5
    type Sub_4_5 = _M5_0
    type Sub_5_0 = _M5_5
    type Sub_5_5 = _M6_0
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _M0_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _M1_0
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _M1_5
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _M2_0
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _M2_5
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _M3_0
    type Div_0_0 = _NaNx
    type Div_0_5 = _M1_0
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _0_5
  }

  implicit object _M0_5 extends Printable[_M0_5] {
    override def print: String = "-0.5"
  }

  sealed trait _0_0 extends MReal{
    type AddR[R <: MReal] = R#Add_0_0
    type SubR[R <: MReal] = R#Sub_0_0
    type MulR[R <: MReal] = R#Mul_0_0
    type DivR[R <: MReal] = R#Div_0_0
    type Add_0_0 = _0_0
    type Add_0_5 = _0_5
    type Add_1_0 = _1_0
    type Add_1_5 = _1_5
    type Add_2_0 = _2_0
    type Add_2_5 = _2_5
    type Add_3_0 = _3_0
    type Add_3_5 = _3_5
    type Add_4_0 = _4_0
    type Add_4_5 = _4_5
    type Add_5_0 = _5_0
    type Add_5_5 = _5_5
    type Add_6_0 = _6_0
    type Sub_0_0 = _0_0
    type Sub_0_5 = _M0_5
    type Sub_1_0 = _M1_0
    type Sub_1_5 = _M1_5
    type Sub_2_0 = _M2_0
    type Sub_2_5 = _M2_5
    type Sub_3_0 = _M3_0
    type Sub_3_5 = _M3_5
    type Sub_4_0 = _M4_0
    type Sub_4_5 = _M4_5
    type Sub_5_0 = _M5_0
    type Sub_5_5 = _M5_5
    type Sub_6_0 = _M6_0
    type Mul_0_0 = _0_0
    type Mul_0_5 = _0_0
    type Mul_1_0 = _0_0
    type Mul_1_5 = _0_0
    type Mul_2_0 = _0_0
    type Mul_2_5 = _0_0
    type Mul_3_0 = _0_0
    type Mul_3_5 = _0_0
    type Mul_4_0 = _0_0
    type Mul_4_5 = _0_0
    type Mul_5_0 = _0_0
    type Mul_5_5 = _0_0
    type Mul_6_0 = _0_0
    type Div_0_0 = _NaNx
    type Div_0_5 = _0_0
    type Div_1_0 = _0_0
    type Div_1_5 = _0_0
    type Div_2_0 = _0_0
    type Div_2_5 = _0_0
    type Div_3_0 = _0_0
    type Div_3_5 = _0_0
    type Div_4_0 = _0_0
    type Div_4_5 = _0_0
    type Div_5_0 = _0_0
    type Div_5_5 = _0_0
    type Div_6_0 = _0_0
    type Neg = _0_0
  }

  implicit object _0_0 extends Printable[_0_0] {
    override def print: String = "0"
  }

  sealed trait _0_5 extends MReal{
    type AddR[R <: MReal] = R#Add_0_5
    type SubR[R <: MReal] = R#Sub_0_5
    type MulR[R <: MReal] = R#Mul_0_5
    type DivR[R <: MReal] = R#Div_0_5
    type Add_0_0 = _0_5
    type Add_0_5 = _1_0
    type Add_1_0 = _1_5
    type Add_1_5 = _2_0
    type Add_2_0 = _2_5
    type Add_2_5 = _3_0
    type Add_3_0 = _3_5
    type Add_3_5 = _4_0
    type Add_4_0 = _4_5
    type Add_4_5 = _5_0
    type Add_5_0 = _5_5
    type Add_5_5 = _6_0
    type Add_6_0 = _NaNx
    type Sub_0_0 = _0_5
    type Sub_0_5 = _0_0
    type Sub_1_0 = _M0_5
    type Sub_1_5 = _M1_0
    type Sub_2_0 = _M1_5
    type Sub_2_5 = _M2_0
    type Sub_3_0 = _M2_5
    type Sub_3_5 = _M3_0
    type Sub_4_0 = _M3_5
    type Sub_4_5 = _M4_0
    type Sub_5_0 = _M4_5
    type Sub_5_5 = _M5_0
    type Sub_6_0 = _M5_5
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _0_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _1_0
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _1_5
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _2_0
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _2_5
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _3_0
    type Div_0_0 = _NaNx
    type Div_0_5 = _1_0
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M0_5
  }

  implicit object _0_5 extends Printable[_0_5] {
    override def print: String = "0.5"
  }

  sealed trait _1_0 extends MReal{
    type AddR[R <: MReal] = R#Add_1_0
    type SubR[R <: MReal] = R#Sub_1_0
    type MulR[R <: MReal] = R#Mul_1_0
    type DivR[R <: MReal] = R#Div_1_0
    type Add_0_0 = _1_0
    type Add_0_5 = _1_5
    type Add_1_0 = _2_0
    type Add_1_5 = _2_5
    type Add_2_0 = _3_0
    type Add_2_5 = _3_5
    type Add_3_0 = _4_0
    type Add_3_5 = _4_5
    type Add_4_0 = _5_0
    type Add_4_5 = _5_5
    type Add_5_0 = _6_0
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _1_0
    type Sub_0_5 = _0_5
    type Sub_1_0 = _0_0
    type Sub_1_5 = _M0_5
    type Sub_2_0 = _M1_0
    type Sub_2_5 = _M1_5
    type Sub_3_0 = _M2_0
    type Sub_3_5 = _M2_5
    type Sub_4_0 = _M3_0
    type Sub_4_5 = _M3_5
    type Sub_5_0 = _M4_0
    type Sub_5_5 = _M4_5
    type Sub_6_0 = _M5_0
    type Mul_0_0 = _0_0
    type Mul_0_5 = _0_5
    type Mul_1_0 = _1_0
    type Mul_1_5 = _1_5
    type Mul_2_0 = _2_0
    type Mul_2_5 = _2_5
    type Mul_3_0 = _3_0
    type Mul_3_5 = _3_5
    type Mul_4_0 = _4_0
    type Mul_4_5 = _4_5
    type Mul_5_0 = _5_0
    type Mul_5_5 = _5_5
    type Mul_6_0 = _6_0
    type Div_0_0 = _NaNx
    type Div_0_5 = _2_0
    type Div_1_0 = _1_0
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M1_0
  }

  implicit object _1_0 extends Printable[_1_0] {
    override def print: String = "1"
  }

  sealed trait _1_5 extends MReal{
    type AddR[R <: MReal] = R#Add_1_5
    type SubR[R <: MReal] = R#Sub_1_5
    type MulR[R <: MReal] = R#Mul_1_5
    type DivR[R <: MReal] = R#Div_1_5
    type Add_0_0 = _1_5
    type Add_0_5 = _2_0
    type Add_1_0 = _2_5
    type Add_1_5 = _3_0
    type Add_2_0 = _3_5
    type Add_2_5 = _4_0
    type Add_3_0 = _4_5
    type Add_3_5 = _5_0
    type Add_4_0 = _5_5
    type Add_4_5 = _6_0
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _1_5
    type Sub_0_5 = _1_0
    type Sub_1_0 = _0_5
    type Sub_1_5 = _0_0
    type Sub_2_0 = _M0_5
    type Sub_2_5 = _M1_0
    type Sub_3_0 = _M1_5
    type Sub_3_5 = _M2_0
    type Sub_4_0 = _M2_5
    type Sub_4_5 = _M3_0
    type Sub_5_0 = _M3_5
    type Sub_5_5 = _M4_0
    type Sub_6_0 = _M4_5
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _1_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _3_0
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _4_5
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _6_0
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _3_0
    type Div_1_0 = _NaNx
    type Div_1_5 = _1_0
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M1_5
  }

  implicit object _1_5 extends Printable[_1_5] {
    override def print: String = "1.5"
  }

  sealed trait _2_0 extends MReal{
    type AddR[R <: MReal] = R#Add_2_0
    type SubR[R <: MReal] = R#Sub_2_0
    type MulR[R <: MReal] = R#Mul_2_0
    type DivR[R <: MReal] = R#Div_2_0
    type Add_0_0 = _2_0
    type Add_0_5 = _2_5
    type Add_1_0 = _3_0
    type Add_1_5 = _3_5
    type Add_2_0 = _4_0
    type Add_2_5 = _4_5
    type Add_3_0 = _5_0
    type Add_3_5 = _5_5
    type Add_4_0 = _6_0
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _2_0
    type Sub_0_5 = _1_5
    type Sub_1_0 = _1_0
    type Sub_1_5 = _0_5
    type Sub_2_0 = _0_0
    type Sub_2_5 = _M0_5
    type Sub_3_0 = _M1_0
    type Sub_3_5 = _M1_5
    type Sub_4_0 = _M2_0
    type Sub_4_5 = _M2_5
    type Sub_5_0 = _M3_0
    type Sub_5_5 = _M3_5
    type Sub_6_0 = _M4_0
    type Mul_0_0 = _0_0
    type Mul_0_5 = _1_0
    type Mul_1_0 = _2_0
    type Mul_1_5 = _3_0
    type Mul_2_0 = _4_0
    type Mul_2_5 = _5_0
    type Mul_3_0 = _6_0
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _4_0
    type Div_1_0 = _2_0
    type Div_1_5 = _NaNx
    type Div_2_0 = _1_0
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M2_0
  }

  implicit object _2_0 extends Printable[_2_0] {
    override def print: String = "²"
  }

  sealed trait _2_5 extends MReal{
    type AddR[R <: MReal] = R#Add_2_5
    type SubR[R <: MReal] = R#Sub_2_5
    type MulR[R <: MReal] = R#Mul_2_5
    type DivR[R <: MReal] = R#Div_2_5
    type Add_0_0 = _2_5
    type Add_0_5 = _3_0
    type Add_1_0 = _3_5
    type Add_1_5 = _4_0
    type Add_2_0 = _4_5
    type Add_2_5 = _5_0
    type Add_3_0 = _5_5
    type Add_3_5 = _6_0
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _2_5
    type Sub_0_5 = _2_0
    type Sub_1_0 = _1_5
    type Sub_1_5 = _1_0
    type Sub_2_0 = _0_5
    type Sub_2_5 = _0_0
    type Sub_3_0 = _M0_5
    type Sub_3_5 = _M1_0
    type Sub_4_0 = _M1_5
    type Sub_4_5 = _M2_0
    type Sub_5_0 = _M2_5
    type Sub_5_5 = _M3_0
    type Sub_6_0 = _M3_5
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _2_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _5_0
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _5_0
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _1_0
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M2_5
  }

  implicit object _2_5 extends Printable[_2_5] {
    override def print: String = "2.5"
  }

  sealed trait _3_0 extends MReal{
    type AddR[R <: MReal] = R#Add_3_0
    type SubR[R <: MReal] = R#Sub_3_0
    type MulR[R <: MReal] = R#Mul_3_0
    type DivR[R <: MReal] = R#Div_3_0
    type Add_0_0 = _3_0
    type Add_0_5 = _3_5
    type Add_1_0 = _4_0
    type Add_1_5 = _4_5
    type Add_2_0 = _5_0
    type Add_2_5 = _5_5
    type Add_3_0 = _6_0
    type Add_3_5 = _NaNx
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _3_0
    type Sub_0_5 = _2_5
    type Sub_1_0 = _2_0
    type Sub_1_5 = _1_5
    type Sub_2_0 = _1_0
    type Sub_2_5 = _0_5
    type Sub_3_0 = _0_0
    type Sub_3_5 = _M0_5
    type Sub_4_0 = _M1_0
    type Sub_4_5 = _M1_5
    type Sub_5_0 = _M2_0
    type Sub_5_5 = _M2_5
    type Sub_6_0 = _M3_0
    type Mul_0_0 = _0_0
    type Mul_0_5 = _1_5
    type Mul_1_0 = _3_0
    type Mul_1_5 = _4_5
    type Mul_2_0 = _6_0
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _6_0
    type Div_1_0 = _3_0
    type Div_1_5 = _2_0
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _1_0
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M3_0
  }

  implicit object _3_0 extends Printable[_3_0] {
    override def print: String = "³"
  }

  sealed trait _3_5 extends MReal{
    type AddR[R <: MReal] = R#Add_3_5
    type SubR[R <: MReal] = R#Sub_3_5
    type MulR[R <: MReal] = R#Mul_3_5
    type DivR[R <: MReal] = R#Div_3_5
    type Add_0_0 = _3_5
    type Add_0_5 = _4_0
    type Add_1_0 = _4_5
    type Add_1_5 = _5_0
    type Add_2_0 = _5_5
    type Add_2_5 = _6_0
    type Add_3_0 = _NaNx
    type Add_3_5 = _NaNx
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _3_5
    type Sub_0_5 = _3_0
    type Sub_1_0 = _2_5
    type Sub_1_5 = _2_0
    type Sub_2_0 = _1_5
    type Sub_2_5 = _1_0
    type Sub_3_0 = _0_5
    type Sub_3_5 = _0_0
    type Sub_4_0 = _M0_5
    type Sub_4_5 = _M1_0
    type Sub_5_0 = _M1_5
    type Sub_5_5 = _M2_0
    type Sub_6_0 = _M2_5
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _3_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _1_0
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M3_5
  }

  implicit object _3_5 extends Printable[_3_5] {
    override def print: String = "3.5"
  }

  sealed trait _4_0 extends MReal{
    type AddR[R <: MReal] = R#Add_4_0
    type SubR[R <: MReal] = R#Sub_4_0
    type MulR[R <: MReal] = R#Mul_4_0
    type DivR[R <: MReal] = R#Div_4_0
    type Add_0_0 = _4_0
    type Add_0_5 = _4_5
    type Add_1_0 = _5_0
    type Add_1_5 = _5_5
    type Add_2_0 = _6_0
    type Add_2_5 = _NaNx
    type Add_3_0 = _NaNx
    type Add_3_5 = _NaNx
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _4_0
    type Sub_0_5 = _3_5
    type Sub_1_0 = _3_0
    type Sub_1_5 = _2_5
    type Sub_2_0 = _2_0
    type Sub_2_5 = _1_5
    type Sub_3_0 = _1_0
    type Sub_3_5 = _0_5
    type Sub_4_0 = _0_0
    type Sub_4_5 = _M0_5
    type Sub_5_0 = _M1_0
    type Sub_5_5 = _M1_5
    type Sub_6_0 = _M2_0
    type Mul_0_0 = _0_0
    type Mul_0_5 = _2_0
    type Mul_1_0 = _4_0
    type Mul_1_5 = _6_0
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _4_0
    type Div_1_5 = _NaNx
    type Div_2_0 = _2_0
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _1_0
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M4_0
  }

  implicit object _4_0 extends Printable[_4_0] {
    override def print: String = "⁴"
  }

  sealed trait _4_5 extends MReal{
    type AddR[R <: MReal] = R#Add_4_5
    type SubR[R <: MReal] = R#Sub_4_5
    type MulR[R <: MReal] = R#Mul_4_5
    type DivR[R <: MReal] = R#Div_4_5
    type Add_0_0 = _4_5
    type Add_0_5 = _5_0
    type Add_1_0 = _5_5
    type Add_1_5 = _6_0
    type Add_2_0 = _NaNx
    type Add_2_5 = _NaNx
    type Add_3_0 = _NaNx
    type Add_3_5 = _NaNx
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _4_5
    type Sub_0_5 = _4_0
    type Sub_1_0 = _3_5
    type Sub_1_5 = _3_0
    type Sub_2_0 = _2_5
    type Sub_2_5 = _2_0
    type Sub_3_0 = _1_5
    type Sub_3_5 = _1_0
    type Sub_4_0 = _0_5
    type Sub_4_5 = _0_0
    type Sub_5_0 = _M0_5
    type Sub_5_5 = _M1_0
    type Sub_6_0 = _M1_5
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _4_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _NaNx
    type Div_1_5 = _3_0
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _1_0
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M4_5
  }

  implicit object _4_5 extends Printable[_4_5] {
    override def print: String = "4.5"
  }

  sealed trait _5_0 extends MReal{
    type AddR[R <: MReal] = R#Add_5_0
    type SubR[R <: MReal] = R#Sub_5_0
    type MulR[R <: MReal] = R#Mul_5_0
    type DivR[R <: MReal] = R#Div_5_0
    type Add_0_0 = _5_0
    type Add_0_5 = _5_5
    type Add_1_0 = _6_0
    type Add_1_5 = _NaNx
    type Add_2_0 = _NaNx
    type Add_2_5 = _NaNx
    type Add_3_0 = _NaNx
    type Add_3_5 = _NaNx
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _5_0
    type Sub_0_5 = _4_5
    type Sub_1_0 = _4_0
    type Sub_1_5 = _3_5
    type Sub_2_0 = _3_0
    type Sub_2_5 = _2_5
    type Sub_3_0 = _2_0
    type Sub_3_5 = _1_5
    type Sub_4_0 = _1_0
    type Sub_4_5 = _0_5
    type Sub_5_0 = _0_0
    type Sub_5_5 = _M0_5
    type Sub_6_0 = _M1_0
    type Mul_0_0 = _0_0
    type Mul_0_5 = _2_5
    type Mul_1_0 = _5_0
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _5_0
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _2_0
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _1_0
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _M5_0
  }

  implicit object _5_0 extends Printable[_5_0] {
    override def print: String = "5"
  }

  sealed trait _5_5 extends MReal{
    type AddR[R <: MReal] = R#Add_5_5
    type SubR[R <: MReal] = R#Sub_5_5
    type MulR[R <: MReal] = R#Mul_5_5
    type DivR[R <: MReal] = R#Div_5_5
    type Add_0_0 = _5_5
    type Add_0_5 = _6_0
    type Add_1_0 = _NaNx
    type Add_1_5 = _NaNx
    type Add_2_0 = _NaNx
    type Add_2_5 = _NaNx
    type Add_3_0 = _NaNx
    type Add_3_5 = _NaNx
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _5_5
    type Sub_0_5 = _5_0
    type Sub_1_0 = _4_5
    type Sub_1_5 = _4_0
    type Sub_2_0 = _3_5
    type Sub_2_5 = _3_0
    type Sub_3_0 = _2_5
    type Sub_3_5 = _2_0
    type Sub_4_0 = _1_5
    type Sub_4_5 = _1_0
    type Sub_5_0 = _0_5
    type Sub_5_5 = _0_0
    type Sub_6_0 = _M0_5
    type Mul_0_0 = _0_0
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _5_5
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _1_0
    type Div_6_0 = _NaNx
    type Neg = _M5_5
  }

  implicit object _5_5 extends Printable[_5_5] {
    override def print: String = "5.5"
  }

  sealed trait _6_0 extends MReal{
    type AddR[R <: MReal] = R#Add_6_0
    type SubR[R <: MReal] = R#Sub_6_0
    type MulR[R <: MReal] = R#Mul_6_0
    type DivR[R <: MReal] = R#Div_6_0
    type Add_0_0 = _6_0
    type Add_0_5 = _NaNx
    type Add_1_0 = _NaNx
    type Add_1_5 = _NaNx
    type Add_2_0 = _NaNx
    type Add_2_5 = _NaNx
    type Add_3_0 = _NaNx
    type Add_3_5 = _NaNx
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _6_0
    type Sub_0_5 = _5_5
    type Sub_1_0 = _5_0
    type Sub_1_5 = _4_5
    type Sub_2_0 = _4_0
    type Sub_2_5 = _3_5
    type Sub_3_0 = _3_0
    type Sub_3_5 = _2_5
    type Sub_4_0 = _2_0
    type Sub_4_5 = _1_5
    type Sub_5_0 = _1_0
    type Sub_5_5 = _0_5
    type Sub_6_0 = _0_0
    type Mul_0_0 = _0_0
    type Mul_0_5 = _3_0
    type Mul_1_0 = _6_0
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _6_0
    type Div_1_5 = _4_0
    type Div_2_0 = _3_0
    type Div_2_5 = _NaNx
    type Div_3_0 = _2_0
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _1_0
    type Neg = _M6_0
  }

  implicit object _6_0 extends Printable[_6_0] {
    override def print: String = "6"
  }

  sealed trait _NaNx extends MReal{
    type AddR[_] = _NaNx
    type SubR[_] = _NaNx
    type MulR[_] = _NaNx
    type DivR[_] = _NaNx
    type Add_0_0 = _NaNx
    type Add_0_5 = _NaNx
    type Add_1_0 = _NaNx
    type Add_1_5 = _NaNx
    type Add_2_0 = _NaNx
    type Add_2_5 = _NaNx
    type Add_3_0 = _NaNx
    type Add_3_5 = _NaNx
    type Add_4_0 = _NaNx
    type Add_4_5 = _NaNx
    type Add_5_0 = _NaNx
    type Add_5_5 = _NaNx
    type Add_6_0 = _NaNx
    type Sub_0_0 = _NaNx
    type Sub_0_5 = _NaNx
    type Sub_1_0 = _NaNx
    type Sub_1_5 = _NaNx
    type Sub_2_0 = _NaNx
    type Sub_2_5 = _NaNx
    type Sub_3_0 = _NaNx
    type Sub_3_5 = _NaNx
    type Sub_4_0 = _NaNx
    type Sub_4_5 = _NaNx
    type Sub_5_0 = _NaNx
    type Sub_5_5 = _NaNx
    type Sub_6_0 = _NaNx
    type Mul_0_0 = _NaNx
    type Mul_0_5 = _NaNx
    type Mul_1_0 = _NaNx
    type Mul_1_5 = _NaNx
    type Mul_2_0 = _NaNx
    type Mul_2_5 = _NaNx
    type Mul_3_0 = _NaNx
    type Mul_3_5 = _NaNx
    type Mul_4_0 = _NaNx
    type Mul_4_5 = _NaNx
    type Mul_5_0 = _NaNx
    type Mul_5_5 = _NaNx
    type Mul_6_0 = _NaNx
    type Div_0_0 = _NaNx
    type Div_0_5 = _NaNx
    type Div_1_0 = _NaNx
    type Div_1_5 = _NaNx
    type Div_2_0 = _NaNx
    type Div_2_5 = _NaNx
    type Div_3_0 = _NaNx
    type Div_3_5 = _NaNx
    type Div_4_0 = _NaNx
    type Div_4_5 = _NaNx
    type Div_5_0 = _NaNx
    type Div_5_5 = _NaNx
    type Div_6_0 = _NaNx
    type Neg = _NaNx
  }

  implicit object _NaNx extends Printable[_NaNx] {
    override def print: String = "?"
  }

  type +[L<:MReal, R<:MReal] = R#AddR[L]
  type -[L<:MReal, R<:MReal] = R#SubR[L]
  type *[L<:MReal, R<:MReal] = R#MulR[L]
  type /[L<:MReal, R<:MReal] = R#DivR[L]
  type --[L<:MReal] = L#Neg
}


