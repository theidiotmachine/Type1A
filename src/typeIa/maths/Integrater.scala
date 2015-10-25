package typeIa.maths

import typeIa.maths.Integratables.IntegrationArguments

/**
 * Created by tim on 07/10/15.
 */
object Integrater {
  //[r1-r2] == domain

  def integrate[S, T](r1: S, r2: S, integrand: (S, S)=>T)(implicit ev: IntegrationArguments[S, T]): T = {
    val numSteps = 20
    val stepSize = ev.stepSize(r1, r2, numSteps)

    var out = ev.zero()
    var step = 0
    while(step < numSteps) {
      val (a, b) = ev.nextStep(r1, stepSize, step)
      out = ev.acc(out, integrand(a, b))
      step += 1
    }
    out
  }
}
