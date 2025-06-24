import Solution.*
import SolutionComparer.compareAlgorithms
import scala.util.Success
import scala.util.Failure
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.*

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

  println("="*50)

  given ExecutionContext = scala.concurrent.ExecutionContext.global
  val users = ParAsyncCombineRecoverSolution(List(1,2,3,4,5,-50,6,7,8,-1,-3,9,10))

  users.onComplete{
    case Success(value) => println(s"Completed ${value.length}")
    case Failure(exception) => println("oops")
  }
  Await.result(users, 10.seconds)
}
