//E5: Factorial

import common.Solution
import scala.annotation.tailrec

object FactorialSolution extends Solution[Int, BigInt] {

  override def solve(input: Int): BigInt = {
    require(input >= 0)
    @tailrec
    def factorialHelper(num: Int, acc: BigInt): BigInt = {
        if (num <= 1) {
            acc
        } else {
            factorialHelper(num - 1, num * acc)
        }

    }

    factorialHelper(input, 1)
  }
  

  def apply(input: Int): BigInt = {
    solve(input)
  }
}
