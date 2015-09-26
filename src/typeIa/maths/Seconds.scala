package typeIa.maths

/**
 * Seconds
 */
class Seconds(val x: Double) extends AnyVal {

}

/**
 * aka s-1
 * @param x
 */
class Hertz(val x: Double) extends AnyVal{
  def `²`: PerS2 = new PerS2(x*x)
}

class PerS2(val x: Double) extends AnyVal {
  def `²`: PerS4 = new PerS4(x*x)
  def `√`: Hertz = new Hertz(math.sqrt(x))
}

class PerS4 (val x: Double) extends AnyVal {

}