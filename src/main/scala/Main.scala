import Solution.*
import SolutionComparer.compareAlgorithms

object Main extends App {

  val solution = new DuplicateRemovalSolution[Int]

  def generateTestData(size: Int, uniqueRatio: Double = 0.3): List[Int] = {
    val uniqueCount = (size * uniqueRatio).toInt
    val unique = (1 to uniqueCount).toList
    List.fill(size)(unique(scala.util.Random.nextInt(uniqueCount))).take(size)
  }

  val testData = generateTestData(100000, 0.3) // 100k elements, 30% unique

  println("=== DuplicateRemovalSolution Performance Comparison ===")
  compareAlgorithms(
    testData,
    solution.optimal_solve, // Your custom implementation
    solution.dummy, // Built-in distinct
    "listbuffer + tailrec",
    "distinct built-in"
  )
}
