//M3: Parser Configuration

import common.Solution
import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
//import scala.concurrent.ExecutionContext.Implicits.global // required for Future

object ParAsyncCombineSolution extends Solution[List[Int], Future[List[User]]] {
    
  given ExecutionContext = ExecutionContext.global

  def fetchUser(id: Int): Future[User] = ???

  override def solve(input: List[Int]): Future[List[User]] = {
    // Create list of Future[User] from input IDs
    val userFutures: List[Future[User]] = input.map(fetchUser)
    
    // Convert List[Future[User]] to Future[List[User]]
    Future.sequence(userFutures)
  }

  def apply(input: List[Int]): Future[List[User]] = {
    solve(input)
  }
}
