package typeIa.maths.units

/**
 * Created by tim on 14/10/15.
 */

trait Printable[T]{
  def print: String
}

object Printable{
  def m2[T](implicit p: Printable[T]): String = "m" + p.print
}

