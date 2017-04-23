package typeIa.maths.units

import typeIa.maths.units.Units.Metres

/**
  * Created by tim on 13/04/17.
  */
final case class AU(value: Double) extends AnyVal {
  @inline def +(m: AU): AU = AU(value + m.value)
  @inline def *(m: Double): AU = AU(value * m)

  override def toString: String = value.toString + " au"
}

object AU {
  implicit def toM(au: AU): Metres = Metres(au.value * 149597870700.0)
  //def apply(m: Metres): AU = AU(m.value / 149597870700.0)
  def au(m: Metres): AU = AU(m.value / 149597870700.0)
}
