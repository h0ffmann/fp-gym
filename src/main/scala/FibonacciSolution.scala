//E6: Fibonacci

import common.Solution
import scala.annotation.tailrec

object FibonacciSolution extends Solution[Int, BigInt] {

  override def solve(input: Int): BigInt = {
    @tailrec
    def fibHelper(
        remaining: Int,
        fibPrev: BigInt,
        fibCurrent: BigInt
    ): BigInt = {
      remaining match {
        case 0 => fibPrev
        case _ => fibHelper(remaining - 1, fibCurrent, fibPrev + fibCurrent)
      }
    }

    require(input >= 0, "Fibonacci is not defined for negative numbers")
    fibHelper(input, 0, 1)
  }

  def apply(input: Int): BigInt = {
    solve(input)
  }
}
