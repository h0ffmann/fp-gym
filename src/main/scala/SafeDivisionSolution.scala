//M1: Safe division w Either

import common.Solution
import common.Solution.*
import scala.annotation.tailrec

case class SafeDivisionInput(dividend: Double, divisor: Double)
type SafeDivisionOutput = Either[SolutionError, Double]

object SafeDivisionSolution extends Solution[SafeDivisionInput, SafeDivisionOutput] {

  override def solve(input: SafeDivisionInput): SafeDivisionOutput = {
    if(input.divisor == 0){
        Left(DivisionZeroDivisor)
    } else {
        Right(input.dividend / input.divisor)
    }
  }

  def apply(dividend: Double, divisor: Double): SafeDivisionOutput = {
    solve(SafeDivisionInput(dividend, divisor))
  }
}
