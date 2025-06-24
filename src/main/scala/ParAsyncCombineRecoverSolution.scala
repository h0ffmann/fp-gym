//M3: Parser Configuration

import common.Solution
import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import scala.util.Random
//import scala.concurrent.ExecutionContext.Implicits.global // required for Future

object ParAsyncCombineRecoverSolution
    extends Solution[List[Int], Future[List[User]]] {

  given ExecutionContext = ExecutionContext.global

  def fetchUser(id: Int): Future[User] = Future {
    Thread.sleep(Random.between(100, 200))
    if (id <= 1) {
      throw new IllegalArgumentException("Invalid ID")
    } else User()
  }

  override def solve(input: List[Int]): Future[List[User]] = {
    // Create list of Future[User] from input IDs
    val userFutures: List[Future[Option[User]]] =
      input.map { id =>
        fetchUser(id)
          .map(Some(_))
          .recover { case ex =>
            println(s"Error fetching userID $id due to ${ex.toString()}")
            None
          }
      }

    // Convert List[Future[User]] to Future[List[User]]
    Future.sequence(userFutures).map(_.flatten)
  }

  def apply(input: List[Int]): Future[List[User]] = {
    solve(input)
  }
}
