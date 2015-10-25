package fiddly

object Repro {
  sealed trait T

  sealed trait TA extends T
  sealed trait TB extends T

  trait TP[T]{
    def print: String
  }

  implicit object OPA extends TP[TA] {
    override def print: String = "a"
  }

  implicit object OPB extends TP[TB] {
    override def print: String = "b"
  }

  /*
  trait OO[X, T]{
    //type X1 = S
    def prints(implicit p: TP[X]) = "s" + p.print + " "
    //type Y1 = T
    def printt(implicit p: TP[T]) = "t" + p.print + " "
  }

  implicit object OOX extends OO[TA, TB] {

  }*/

  case class CC[X <: T, Y <: T](value: Double) extends AnyVal{
    def s(implicit p: TP[X]): String = "s" + p.print + " "
    def t(implicit p: TP[Y]): String = "t" + p.print + " "

    //def p(implicit oo: OO[X,Y]) = oo.prints + oo.printt + value.toString
  }

  def main (args: Array[String]){
    val a = CC[TA, TB](3)
    val yeah = a.s + a.t + a.value.toString
    println(yeah)


  }
}
