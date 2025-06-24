import scala.annotation.tailrec
import common.Solution
import common.Solution.*

case class TwoSumInput(nums: Array[Int], target: Int)

object TwoSumSolution extends Solution[TwoSumInput, Array[Int]] {

  override def solve(input: TwoSumInput): Array[Int] = {
    findTwoSum(input.nums.zipWithIndex.toList, Map.empty, input.target).fold(
      error => throw new NoSuchElementException(s"No solution found: $error"),
      solution => solution
    )
  }

  def apply(nums: Array[Int], target: Int): Array[Int] = {
    solve(TwoSumInput(nums, target))
  }

  @tailrec
  def findTwoSum(listWithIndices: List[(Int, Int)], seen: Map[Int,Int], target: Int): Either[SolutionError, Array[Int]] = {
    listWithIndices match {
      case Nil => Left(NoPossibleSolution)
      case (currentNum, currentIdx) :: next =>
        val complement = target - currentNum
        seen.get(complement) match {
          case Some(foundIndex) =>
            Right(List(foundIndex, currentIdx).sorted.toArray)
          case None =>
            findTwoSum(next, seen + (currentNum -> currentIdx), target)
        }
    }
  }
}