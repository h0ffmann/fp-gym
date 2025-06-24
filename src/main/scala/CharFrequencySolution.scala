//E1: Character Frequency Counter
import common.Solution

object CharFrequencySolution extends Solution[String, Map[Char, Int]] {

  override def solve(input: String): Map[Char, Int] = {
    //input.groupBy(identity(_)).map(x => x._1 -> x._2.length())
    input.groupMapReduce(identity)(_ => 1)(_ + _)
  }
  

  def apply(input: String): Map[Char, Int] = {
    solve(input)
  }
}

