package typeIa.maths.units

/**
 * Generator for the real numbers
 *
 */
object Generator {
  val magicNaNx = 100
  val maxi = 6.0
  val mini = -6.0

  def intRender(i: Double): String =
    if(i > maxi || i < mini || !(i.isWhole() || (math.abs(i) % 1) == 0.5))
      "_NaNx"
    else
      "_" +
        (if(i < 0.0)
          "M" + (-i).toString.replace('.', '_')
        else
          i.toString.replace('.', '_') )

  def powRender(i: Double): String = {
    i match {
      case -6 => "⁻6"
      case -5 => "⁻5"
      case -4 => "⁻⁴"
      case -3 => "⁻³"
      case -2 => "⁻²"
      case -1 => "⁻1"
      case 0 => "0"
      case 1 => "1"
      case 2 => "²"
      case 3 => "³"
      case 4 => "⁴"
      case 5 => "5"
      case 6 => "6"
      case `magicNaNx` => "?"
      case _ => i.toString
    }
  }

  def impF(i: Double, from: Double, innerIndentString: String, f: (Double, Double) => Double, op: String): String = {
    from.to(maxi, 0.5).foldLeft("")((acc, j)=>{
      val k = f(i, j)
      acc + innerIndentString + "type " + op + intRender(j) + " = " + intRender(k) + "\n"
    })
  }


  def fillBody(i: Double, innerIndentString: String): String = {
    val ops = if(i == magicNaNx){
      innerIndentString + "type AddR[_] = _NaNx\n" +
        innerIndentString + "type SubR[_] = _NaNx\n" +
        innerIndentString + "type MulR[_] = _NaNx\n" +
        innerIndentString + "type DivR[_] = _NaNx\n"
    } else {
      innerIndentString + "type AddR[R <: MReal] = R#" + (if (i < 0) "Sub" + intRender(-i) else "Add" + intRender(i)) + "\n" +
        innerIndentString + "type SubR[R <: MReal] = R#" + (if (i < 0) "Add" + intRender(-i) else "Sub" + intRender(i)) + "\n" +
        innerIndentString + "type MulR[" + (if (i < 0) "_] = _NaNx" else "R <: MReal] = R#Mul" + intRender(i)) + "\n" +
        innerIndentString + "type DivR[" + (if (i < 0) "_] = _NaNx" else "R <: MReal] = R#Div" + intRender(i)) + "\n"
    }
    val imps = impF(i, 0, innerIndentString, (x,y)=> x + y, "Add") +
      impF(i, 0, innerIndentString, (x,y)=> x - y, "Sub") +
      impF(i, 0, innerIndentString, (x,y)=> {if(x != magicNaNx) x * y else magicNaNx}, "Mul") +
      impF(i, 0, innerIndentString, (x,y)=>{if(y != 0 && x%y == 0) x / y else magicNaNx}, "Div") +
      innerIndentString + "type Neg = " + intRender(-i) + "\n"
    ops + imps
  }

  def genTrait(i: Double, outerIndentString: String, innerIndentString: String): String = {
    val out = outerIndentString + "sealed trait " + intRender(i) +
      " extends MReal{\n" +
      fillBody(i, innerIndentString) +
      outerIndentString + "}\n"
    out
  }

  def genObject(i: Double, outerIndentString: String, innerIndentString: String): String = {
    val out = outerIndentString + "implicit object " + intRender(i) + " extends Printable[" + intRender(i) + "] {\n" +
      innerIndentString + "override def print: String = \"" + powRender(i) + "\"\n" +
      outerIndentString + "}\n"
    out
  }

  def main (args: Array[String]){
    val header = "package fiddly\n\nimport scala.language.higherKinds\n\nobject Reals {"
    println(header)

    val outerIndent = 1
    val outerIndentString = "  " * outerIndent
    val innerIndentString = "  " * (outerIndent + 1)

    val traitTxt = "sealed trait MReal{\n" +
      "    type AddR[R <: MReal] <: MReal\n" +
      "    type SubR[R <: MReal] <: MReal\n" +
      "    type MulR[R <: MReal] <: MReal\n" +
      "    type DivR[R <: MReal] <: MReal\n" +
      "    type Neg <: MReal\n"

    val traitBodyTxts = for(x <- mini.to(maxi, 0.5); op <- Array("Add", "Sub", "Mul", "Div"))
      yield innerIndentString + "type " + op + intRender(x) + " <: MReal\n"


    /*
      "    type Neg <: MReal\n\n" +
      "    type Sub4 <: MReal\n" +
      "    type Sub3 <: MReal\n" +
      "    type Sub2 <: MReal\n" +
      "    type Sub1 <: MReal\n" +
      "    type Sub0 <: MReal\n" +
      "    type Add0 <: MReal\n" +
      "    type Add1 <: MReal\n" +
      "    type Add2 <: MReal\n" +
      "    type Add3 <: MReal\n" +
      "    type Add4 <: MReal\n\n" +
      "    type Div4 <: MReal\n" +
      "    type Div3 <: MReal\n" +
      "    type Div2 <: MReal\n" +
      "    type Div1 <: MReal\n" +
      "    type Div0 <: MReal\n" +
      "    type Mul0 <: MReal\n" +
      "    type Mul1 <: MReal\n" +
      "    type Mul2 <: MReal\n" +
      "    type Mul3 <: MReal\n" +
      "    type Mul4 <: MReal\n" +
      "  }\n"
      */

    println(traitTxt + traitBodyTxts.mkString("") + outerIndentString + "}\n")


    for(i <- mini.to(maxi, 0.5)){
      val out = genTrait(i, outerIndentString, innerIndentString)
      println(out)
      println(genObject(i, outerIndentString, innerIndentString))
    }

    println(genTrait(magicNaNx, outerIndentString, innerIndentString))
    println(genObject(magicNaNx, outerIndentString, innerIndentString))

    val ops = outerIndentString + "type +[L<:MReal, R<:MReal] = R#AddR[L]\n" +
      outerIndentString + "type -[L<:MReal, R<:MReal] = R#SubR[L]\n" +
      outerIndentString + "type *[L<:MReal, R<:MReal] = R#MulR[L]\n" +
      outerIndentString + "type /[L<:MReal, R<:MReal] = R#DivR[L]\n"
    print(ops)

    println("}\n")
  }
}
