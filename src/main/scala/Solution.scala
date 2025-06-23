object Solution {
  sealed trait SolutionError
  case object NoPossibleSolution extends SolutionError
  case object DivisionZeroDivisor extends SolutionError
}

trait Solution[Input, Output] {
  def solve(input: Input): Output
}