import common.Solution
//import scala.annotation.tailrec


object QuickSortSolution extends Solution[Array[Int], Array[Int]] {

  override def solve(input: Array[Int]): Array[Int] = {
    input.toList match {
      case Nil => input
      case head :: next => 
        val left = next.filter(_ < head)
        val right = next.filter(_ >= head)
        solve(left.toArray) ++ Array(head) ++ solve(right.toArray)
    }
  }
  

  def apply(nums: Array[Int]): Array[Int] = {
    solve(nums)
  }
}