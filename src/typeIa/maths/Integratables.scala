package typeIa.maths

import typeIa.maths.units.Units.{Kilograms, Metres}

/**
 * Created by tim on 07/10/15.
 */
object Integratables {
  trait IntegrationArguments[S, T] {
    def stepSize(a: S, b: S, numSteps: Double): S
    def minus(a: S, b: S): S
    def half(a: S): S
    def acc(l: T, r: T): T
    def zero(): T
    def nextStep(a: S, stepSize: S, i: Double): (S, S)
  }

  object IntegrationArguments{
    implicit object IntegrationArguments_Metres_Kilograms extends IntegrationArguments[Metres, Kilograms] {
      override def stepSize(a: Metres, b: Metres, numSteps: Double): Metres = (a-b)/numSteps
      override def half(a: Metres): Metres = a * 0.5
      override def acc(l: Kilograms, r: Kilograms): Kilograms =
        l + r
      override def minus(a: Metres, b: Metres): Metres = a-b
      override def zero(): Kilograms = new Kilograms(0)
      override def nextStep(a: Metres, stepSize: Metres, i: Double): (Metres, Metres) = {
        (a + (stepSize * i), a + (stepSize * (i+1.0)))
      }
    }
  }
}
