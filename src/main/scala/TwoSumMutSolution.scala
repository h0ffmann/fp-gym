import Solution.*
//import TwoSumSolution.TwoSumInput
import scala.util.boundary, boundary.break

object TwoSumMutSolution extends Solution[TwoSumInput, Array[Int]] {

  override def solve(input: TwoSumInput): Array[Int] = {
    boundary{
      val seen = scala.collection.mutable.Map[Int,Int]()

      for ((num, i) <- input.nums.zipWithIndex) { 
        val complement = input.target - num
        if (seen.contains(complement)){
          break(Array(List(seen(complement), i).sorted.toArray*))
        }
        seen(num) = i
      }

      throw new NoSuchElementException("No solution found.") 
    }
  }
  

  def apply(nums: Array[Int], target: Int): Array[Int] = {
    solve(TwoSumInput(nums, target))
  }
}