package typeIa.maths

/**
 * Created by tim on 08/10/15.
 */
object Numbers {
  trait NumberLike[T, U, V] {
    def plus(x: T, y: T): T
    def multiply(x: T, y: T): T
  }
}
