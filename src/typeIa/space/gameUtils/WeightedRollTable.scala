package typeIa.space.gameUtils

import scala.annotation.tailrec
import scala.util.Random

/**
 * A table for rolling non-even things
 */
class WeightedRollTable[A <: WeightedRollable](elems: Array[A]) {

  //@tailrec
  private def genTable(elems: List[A], runningProb: Double, totesProbs: Double): List[(Double, Double, A)] = {
    val head = elems.head
    val tail = elems.tail

    //note setting the final prob to 1.0 to avoid floating point errors
    if(tail.isEmpty)
      List((runningProb, 1.0, head))
    else {
      val thisProb = head.probability / totesProbs
      val newRunningProb = runningProb + thisProb
      (runningProb, newRunningProb, head) +: genTable(tail, newRunningProb, totesProbs)
    }
  }

  private def initTable(elems: Array[A]): Array[(Double, Double, A)] = {
    val totesProbs = elems.foldLeft(0.0)((x, y) => x + y.probability)
    genTable(elems.toList, 0.0, totesProbs).toArray
  }

  private val table: Array[(Double, Double, A)] = initTable(elems)

  def get(d: Double): A = {
    //yes, this should be a bsearch
    table.find(a => {a._1 <= d && d < a._2}).get._3
  }

  def rollDeterministic(seed: Long): A = {
    val r = new Random(seed)
    val roll = r.nextDouble()
    get(roll)
  }

  def rollDeterministic(r: Random): A = {
    val roll = r.nextDouble()
    get(roll)
  }

}
