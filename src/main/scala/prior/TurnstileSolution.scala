package prior

import common.Solution
import scala.collection.mutable.{ListBuffer, Queue}

case class TurnstileInput(times: List[Int], direction: List[Int])

object TurnstileSolution extends Solution[TurnstileInput, List[Int]] {

  override def solve(input: TurnstileInput): List[Int] = {
    require(input.times == input.direction)
    List()
  }

  def apply(times: List[Int], direction: List[Int]): List[Int] = {
    solve(TurnstileInput(times, direction))
  }
}
