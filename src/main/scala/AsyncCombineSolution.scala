//M3: Parser Configuration

import common.Solution
import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
//import scala.concurrent.ExecutionContext.Implicits.global // required for Future

case class Permission()
case class User()
object AsyncCombineSolution extends Solution[Int, Future[Permission]] {

  given ExecutionContext = ExecutionContext.global

  def fetchUser(id: Int): Future[User] = ???
  def fetchPermission(user: User): Future[Permission] = ???

  override def solve(input: Int): Future[Permission] = {
    for {
      user <- fetchUser(input)
      perms <- fetchPermission(user)
    } yield perms
  }

  def apply(input: Int): Future[Permission] = {
    solve(input)
  }
}
